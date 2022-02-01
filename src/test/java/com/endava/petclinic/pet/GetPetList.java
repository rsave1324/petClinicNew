package com.endava.petclinic.pet;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetPetList extends TestBaseClass {

    @Test
    public void shouldGetPetList(){
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
        Response response = petClient.getPetList();

        //THEN
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id == %s}.name",withArgs(petId), is(pet.getName()));
    }
}
