package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteOwnerTest extends TestBaseClass {

    @Test
    public void shouldDeleteOwner() {
        //GIVEN
        Owner owner = petClinicFixture.createOwner()
                .getOwner();
        Long ownerId = owner.getId();

        //WHEN
        Response response1 = ownerClient.deleteOwnerById(ownerId);

        //THEN
        response1.then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
