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
        Owner owner = testDataProvider.getOwner();
        Response responseOwner = ownerClient.createOwner(owner);
        responseOwner.then().statusCode(HttpStatus.SC_CREATED);
        long id = responseOwner.body().jsonPath().getLong("id");
        owner.setId(id);

        PetType petType = new PetType();
        petType.setId(1L);
        Pet pet =testDataProvider.getPet(owner, petType);

        //WHEN
        Response response = petClient.createPet(pet);

        //THEN
        response.then().statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void shouldFailCreatePetGivenEmptyName() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();
        Response responseOwner = ownerClient.createOwner(owner);
        responseOwner.then().statusCode(HttpStatus.SC_CREATED);
        long id = responseOwner.body().jsonPath().getLong("id");
        owner.setId(id);
        PetType petType = new PetType();
        petType.setId(3L);
        Pet pet =testDataProvider.getPet(owner, petType);
        pet.setName("");

        //WHEN
        Response response = petClient.createPet(pet);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
