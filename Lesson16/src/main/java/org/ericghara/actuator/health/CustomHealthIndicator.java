package org.ericghara.actuator.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    MetricsEndpoint metricsEndpoint;

    @Override
    public Health health() {
        String key = "jvm.memory.usage.after.gc";
        double pct = metricsEndpoint.metric(key, null)
                                    .getMeasurements()
                                    .get(0)
                                    .getValue();
        if (pct < .003 ) {
            return Health.up()
                    .withDetail(key, pct)
                    .build();
        }
        return Health.down()
                     .withDetail(key, pct)
                     .build();
    }
}
