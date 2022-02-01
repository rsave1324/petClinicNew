package com.endava.petclinic.pet;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeletePetTest extends TestBaseClass {

    @Test
    public void shouldDeletePet() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();
        Response responseOwner = ownerClient.createOwner(owner);
        responseOwner.then().statusCode(HttpStatus.SC_CREATED);
        long id = responseOwner.body().jsonPath().getLong("id");
        owner.setId(id);

        PetType petType = new PetType();
        petType.setId(3L);
        Pet pet =testDataProvider.getPet(owner, petType);
        Response createPetResponse = petClient.createPet(pet);
        createPetResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long petId = createPetResponse.body().jsonPath().getLong("id");

        //WHEN
        Response response = petClient.deletePetById(petId);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
