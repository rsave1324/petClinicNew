package com.endava.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Pet {

    @EqualsAndHashCode.Exclude
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String birthDate;
    @NonNull
    private Owner owner;
    @NonNull
    private PetType type;

    @Override
    public String toString() {   //afiseaza obiectul sub forma de json
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}