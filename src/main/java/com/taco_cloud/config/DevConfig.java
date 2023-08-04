package com.taco_cloud.config;

import com.taco_cloud.data.IngredientRepository;
import com.taco_cloud.data.TacoRepository;
import com.taco_cloud.domain.Ingredient;
import com.taco_cloud.domain.Taco;
import com.taco_cloud.domain.TacoOrder;
import com.taco_cloud.user.UserApp;
import com.taco_cloud.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DevConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            IngredientRepository ingredientRepository,
            UserRepo userRepo,
            TacoRepository tacoRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
            Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
            Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);

            ingredientRepository.saveAll(List.of(
                            flourTortilla,
                            cornTortilla,
                            groundBeef,
                            carnitas,
                            tomatoes,
                            lettuce,
                            cheddar,
                            jack,
                            salsa,
                            sourCream
                    )
            );
            userRepo.saveAll(List.of(
                    UserApp.builder().username("user1")
                            .fullname("user1")
                            .state("state1")
                            .city("city1")
                            .phoneNumber("111")
                            .zip("zip1")
                            .street("street1")
                            .password(passwordEncoder.encode("user123"))
                            .build()
            ));

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepository.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepository.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepository.save(taco3);

//            TacoOrder submitted = TacoOrder.builder()
//                    .deliveryName("Craig Walls")
//                    .deliveryStreet("1234 7th Street")
//                    .deliveryCity("Somewhere")
//                    .deliveryState("Who knows")
//                    .deliveryZip("zipzap")
//                    .ccNumber("Who can guess")
//                    .ccExpiration("Some day")
//                    .ccCVV("See-vee-vee")
//                    .tacos(Arrays.asList(Taco(name=Awesome Sauce, ingredients=[
//                    Ingredient(id=FLTO, name=Flour Tortilla, type=WRAP), Ingredient(id=GRBF,
//                    name=Ground Beef, type=PROTEIN), Ingredient(id=CHED, name=Cheddar,
//                    type=CHEESE), Ingredient(id=TMTO, name=Diced Tomatoes, type=VEGGIES),
//                    Ingredient(id=SLSA, name=Salsa, type=SAUCE), Ingredient(id=SRCR,
//                    name=Sour Cream, type=SAUCE)]), Taco(name=Quesoriffic, ingredients=
//                    [Ingredient(id=FLTO, name=Flour Tortilla, type=WRAP), Ingredient(id=CHED,
//                    name=Cheddar, type=CHEESE), Ingredient(id=JACK, name=Monterrey Jack,
//                    type=CHEESE), Ingredient(id=TMTO, name=Diced Tomatoes, type=VEGGIES),
//                    Ingredient(id=SRCR,name=Sour Cream, type=SAUCE)])))
        };
    }
}
