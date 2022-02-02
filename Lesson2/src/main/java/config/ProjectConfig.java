package config;

import beans.Cat;
import beans.Owner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "beans")
public class ProjectConfig {

    @Bean
    @Qualifier("Tom")
    public Cat cat1() {
        Cat c = new Cat();
        c.setName("Leo");
        return c;
    }

    @Bean
    @Qualifier("Leo")
    public Cat cat2() {
        Cat c = new Cat();
        c.setName("Tom");
        return c;
    }

    @Bean
    public Owner owner() {
        return new Owner();
    }
}
