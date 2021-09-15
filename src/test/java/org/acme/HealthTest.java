package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(TestLifecycleManager.class)
public class HealthTest {

    @Test
    public void testHealthCheck() {
        given()
          .when().get("/q/health")
          .then().statusCode(200);
    }
}