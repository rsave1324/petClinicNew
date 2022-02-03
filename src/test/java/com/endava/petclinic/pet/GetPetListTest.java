package com.endava.petclinic.pet;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class GetPetListTest extends TestBaseClass {

    @Test
    public void shouldGetPetList() {
        Owner owner = petClinicFixture.createOwner()
                .getOwner();

        PetType petType = petClinicFixture.createPetType()
                .getPetType();

        Pet pet = petClinicFixture.createPet(owner, petType)
                .getPet();
        Long petId = pet.getId();

        //WHEN
        Response response = petClient.getPetList();

        //THEN
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id == %s}.name", withArgs(petId), is(pet.getName()));
    }
}
