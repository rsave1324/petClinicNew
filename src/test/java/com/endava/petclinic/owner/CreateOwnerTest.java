package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOwnerTest extends TestBaseClass {

    @Test
    public void shouldCreateOwnerGivenValidData() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id", is(notNullValue()));

        Long id = response.body().jsonPath().getLong("id");

        Owner actualOwnerInDB = db.getOwnerById(id);
        assertThat(actualOwnerInDB, is(owner));
    }

    @Test
    public void shouldFailCreateOwnerGivenEmptyFirstName() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();
        owner.setFirstName("");

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailCreateOwnerGivenFewDigitsPhone() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(0, 0));

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailCreateOwnerGivenManyDigitsPhone() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(11, 100));

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
