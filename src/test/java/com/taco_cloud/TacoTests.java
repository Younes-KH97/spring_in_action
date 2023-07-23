package com.taco_cloud;

import com.taco_cloud.data.TacoRepository;
import com.taco_cloud.domain.Ingredient;
import com.taco_cloud.domain.Taco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TacoTests {
    @Autowired
    private TacoRepository tacoRepository;

    @Test
    private void addTaco(){

    }
}

