package com.example.app_labs.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PetNameTypeBreed(@NotEmpty @NotNull
                               String name,
                               @NotEmpty @NotNull
                               String animalType,
                               @NotEmpty @NotNull
                               String breed) {
}
