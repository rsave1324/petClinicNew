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
        Owner owner = petClinicFixture.createOwner()
                .getOwner();

        PetType petType = petClinicFixture.createPetType()
                .getPetType();

        Pet pet = petClinicFixture.createPet(owner, petType).getPet();
        Long petId = pet.getId();

        //WHEN
        Response response = petClient.deletePetById(petId);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
