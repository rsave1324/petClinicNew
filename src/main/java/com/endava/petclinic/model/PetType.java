package com.endava.petclinic.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PetType {

    @EqualsAndHashCode.Exclude
    private Long id;
    @NonNull
    private String name;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }

    }
}

