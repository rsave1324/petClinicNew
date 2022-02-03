package com.endava.petclinic.petType;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class GetPetTypeListTest extends TestBaseClass {

    @Test
    public void shouldGetOwnerList() {
        //GIVEN
        PetType petType = petClinicFixture.createPetType()
                .getPetType();
        Long petTypeId = petType.getId();

        //WHEN
        Response response = petTypeClient.getPetTypeList();

        //THEN
        //validez foecare field in parte
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id == %s}.name", withArgs(petTypeId), is(petType.getName()));
    }
}
