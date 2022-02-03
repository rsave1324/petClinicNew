package com.endava.petclinic.pet;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class CreatePetTest extends TestBaseClass {

    @Test
    public void shouldCreatePetGivenValidData() {
        //GIVEN
        Owner owner = petClinicFixture.createOwner()
                .getOwner();

        PetType petType = petClinicFixture.createPetType()
                .getPetType();

        Pet pet =testDataProvider.getPet(owner, petType);

        //WHEN
        Response response = petClient.createPet(pet);

        //THEN
        response.then().statusCode(HttpStatus.SC_CREATED);

        Long id = response.body().jsonPath().getLong("id");
    }

    @Test
    public void shouldFailCreatePetGivenEmptyName() {
        //GIVEN
        Owner owner = petClinicFixture.createOwner()
                .getOwner();

        PetType petType = petClinicFixture.createPetType()
                .getPetType();

        Pet pet =testDataProvider.getPet(owner, petType);
        pet.setName("");

        //WHEN
        Response response = petClient.createPet(pet);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
