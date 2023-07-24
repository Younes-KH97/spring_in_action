package com.taco_cloud.config;

import com.taco_cloud.data.IngredientRepository;
import com.taco_cloud.domain.Ingredient;
import com.taco_cloud.user.UserApp;
import com.taco_cloud.user.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class ApplicationConfig {

    private final UserRepo repository;

    public ApplicationConfig(UserRepo repository) {
        this.repository = repository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(
            IngredientRepository ingredientRepository,
            UserRepo userRepo
            ) {
        return args -> {
            ingredientRepository.saveAll(List.of(
                            Ingredient.builder().id("FLTO").name("Flour Tortilla").type(Ingredient.Type.WRAP).build(),
                            Ingredient.builder().id("COTO").name("Corn Tortilla").type(Ingredient.Type.WRAP).build(),
                            Ingredient.builder().id("GRBF").name("Ground Beef").type(Ingredient.Type.PROTEIN).build(),
                            Ingredient.builder().id("CARN").name("Carnitas").type(Ingredient.Type.PROTEIN).build(),
                            Ingredient.builder().id("TMTO").name("Diced Tomatoes").type(Ingredient.Type.VEGGIES).build(),
                            Ingredient.builder().id("LETC").name("Lettuce").type(Ingredient.Type.VEGGIES).build(),
                            Ingredient.builder().id("CHED").name("Cheddar").type(Ingredient.Type.CHEESE).build(),
                            Ingredient.builder().id("JACK").name("Monterrey Jack").type(Ingredient.Type.CHEESE).build(),
                            Ingredient.builder().id("SLSA").name("Salsa").type(Ingredient.Type.SAUCE).build()
                    )
            );
            userRepo.saveAll(List.of(
                    UserApp.builder().username("user_1")
                                     .fullname("user_1")
                                     .state("state_1")
                                     .city("city_1")
                                     .phoneNumber("111")
                                     .zip("zip_1")
                                     .street("street_1")
                                     .password(passwordEncoder().encode("user_123"))
                            .build()
            ));
        };
    }
}
