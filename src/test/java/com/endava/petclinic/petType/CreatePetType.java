package com.endava.petclinic.petType;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreatePetType extends TestBaseClass {

    @Test
    public void shouldCreatePetTypeGivenValidData() {
        //GIVEN
        PetType petType = testDataProvider.getPetType();

        //WHEN
        Response response = petTypeClient.createPetType(petType);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", is(notNullValue()));

        Long id = response.body().jsonPath().getLong("id");

        PetType actualPetTypeInDB = db.getPetTypeById(id);
        assertThat(actualPetTypeInDB, is(petType));
    }
}
