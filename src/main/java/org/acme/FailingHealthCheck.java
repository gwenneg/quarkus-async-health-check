package org.acme;

import io.smallrye.health.api.AsyncHealthCheck;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@ApplicationScoped
@Liveness
public class FailingHealthCheck implements AsyncHealthCheck {

    @Inject
    Mutiny.Session session;

    @Override
    public Uni<HealthCheckResponse> call() {
        return session.createNativeQuery("SELECT 1")
                .getSingleResult()
                .replaceWith(TRUE)
                .onFailure().recoverWithItem(FALSE)
                .onItem().transform(dbState ->
                        HealthCheckResponse.named("Health check").status(dbState).build()
                );
    }
}
