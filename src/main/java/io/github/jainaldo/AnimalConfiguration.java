package io.github.jainaldo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean
    public Animal gato() {
        return new Animal() {
            @Override
            public void falar() {
                System.out.println("Miau");
            }
        };
    }

    @Bean
    public Animal cachorro() {
        return  new Animal() {
            @Override
            public void falar() {
                System.out.println("AU Au");
            }
        };
    }
}
