package com.taco_cloud.config;

import com.taco_cloud.data.IngredientRepository;
import com.taco_cloud.domain.Ingredient;
import com.taco_cloud.domain.Ingredient.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class IngredientConfig {
    @Bean
    CommandLineRunner commandLineRunner(IngredientRepository ingredientRepository) {
        return args -> {
        ingredientRepository.saveAll(List.of(
                Ingredient.builder().id("FLTO").name("Flour Tortilla").type(Type.WRAP).build(),
                Ingredient.builder().id("COTO").name("Corn Tortilla").type(Type.WRAP).build(),
                Ingredient.builder().id("GRBF").name("Ground Beef").type(Type.PROTEIN).build(),
                Ingredient.builder().id("CARN").name("Carnitas").type(Type.PROTEIN).build(),
                Ingredient.builder().id("TMTO").name("Diced Tomatoes").type(Type.VEGGIES).build(),
                Ingredient.builder().id("LETC").name("Lettuce").type(Type.VEGGIES).build(),
                Ingredient.builder().id("CHED").name("Cheddar").type(Type.CHEESE).build(),
                Ingredient.builder().id("JACK").name("Monterrey Jack").type(Type.CHEESE).build(),
                Ingredient.builder().id("SLSA").name("Salsa").type(Type.SAUCE).build()
                )
            );
        };
    }
}
