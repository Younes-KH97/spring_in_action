package com.taco_cloud.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
