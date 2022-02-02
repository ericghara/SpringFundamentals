package org.ericghara;

import org.ericghara.entities.Product;
import org.ericghara.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureTestEntityManager
@SpringBootTest
public class IntegrationTests {

    @Autowired private TestEntityManager entityManager;
    @Autowired private ProductRepository productRepository;
    @Autowired private MockMvc mockMvc;



    public static final Arguments[] PRODUCTS_INIT = { Arguments.of("A",0.99D), Arguments.of("B", 1.99D), Arguments.of("C", 2.98D), Arguments.of("D", 3.99)};

    @BeforeEach
    public void setup() {
        Stream.of(PRODUCTS_INIT).forEach( (a) -> {
            Object[] args = a.get();
            entityManager.persist(new Product( (String) args[0], (double) args[1] ) );
        });
    }

    @Test
    @Transactional
    public void getProductExistsTest() throws Exception {
        mockMvc.perform(get("/product/{name}","A") )
                .andExpect(status().isOk() )
                .andExpect(jsonPath("$.id").isNumber() )
                .andExpect(jsonPath("$.name").value("A") )
                .andExpect(jsonPath("$.price").value(0.99) );
    }

    @Test
    @Transactional
    public void getProductNotExistsTest() throws Exception {
        mockMvc.perform(get("/product/{name}", "X") )
                .andExpect(status().is4xxClientError() )
                .andExpect( (r -> assertEquals(0, r.getResponse().getContentLength() ) ) );
    }
}
