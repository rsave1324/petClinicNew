package com.endava.petclinic.visit;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import com.endava.petclinic.model.Visit;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteVisitTest extends TestBaseClass {

    @Test
    public void shouldDeleteVisit() {
        //GIVEN
        Owner owner = petClinicFixture.createOwner()
                .getOwner();

        PetType petType = petClinicFixture.createPetType()
                .getPetType();

        Pet pet = petClinicFixture.createPet(owner, petType)
                .getPet();

        Visit visit = petClinicFixture.createVisit(pet)
                .getVisit();
        Long visitId = visit.getId();

        //WHEN
        Response response = visitClient.deleteVisitById(visitId);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}