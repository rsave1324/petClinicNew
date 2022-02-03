package com.endava.petclinic.visit;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import com.endava.petclinic.model.Visit;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class GetVisitList extends TestBaseClass {

    @Test
    public void shouldGetVisitList() {
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
        Response response = visitClient.getVisitList();

        //THEN
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id == %s}.description", withArgs(visitId), is(visit.getDescription()));
    }
}
