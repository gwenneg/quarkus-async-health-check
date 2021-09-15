package org.acme;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

public class TestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    PostgreSQLContainer<?> postgreSQLContainer;

    @Override
    public Map<String, String> start() {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres");
        postgreSQLContainer.start();
        Map<String, String> props = new HashMap<>();
        props.put("quarkus.datasource.reactive.url", postgreSQLContainer.getJdbcUrl().replace("jdbc:", ""));
        return props;
    }

    @Override
    public void stop() {
        postgreSQLContainer.stop();
    }
}
