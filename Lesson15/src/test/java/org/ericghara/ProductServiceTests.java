package org.ericghara;

import org.ericghara.repositories.ProductRepository;
import org.ericghara.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


@SpringBootTest
public class ProductServiceTests {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void noPricesReturnedTest() {
        given(productRepository.allProductPrices()).willReturn(Collections.emptyList() );
        int res = productService.numProductsWithPriceBetween(0D,100D);
        assertEquals(0, res);
    }

    @Test
    public void morePricesFoundTest() {
        given(productRepository.allProductPrices() ).willReturn(List.of(new Double[]{1D, 2D, 3D, 99.99D, 101D}) );
        int res = productService.numProductsWithPriceBetween(0D, 100D);
        assertEquals(4, res);
    }

    @Test
    public void equalLowHiPriceTest() {
        given(productRepository.allProductPrices() ).willReturn(List.of(new Double[]{5D, 4.99D, 5.01D}) );
        int res = productService.numProductsWithPriceBetween(5D, 5D); // example test, but a little iffy with double vs BigDecimal
        assertEquals(0, res);
    }







}
