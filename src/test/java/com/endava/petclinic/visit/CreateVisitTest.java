package com.endava.petclinic.visit;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import com.endava.petclinic.model.Visit;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class CreateVisitTest extends TestBaseClass {

    @Test
    public void shouldCreateVisitGivenValidData() {
        //GIVEN
        Owner owner = petClinicFixture.createOwner()
                .getOwner();

        PetType petType = petClinicFixture.createPetType()
                .getPetType();

        Pet pet = petClinicFixture.createPet(owner, petType)
                .getPet();

        Visit visit =testDataProvider.getVisit(pet);

        //WHEN
        Response response = visitClient.createVisit(visit);

        //THEN
        response.then().statusCode(HttpStatus.SC_CREATED);
    }
}
