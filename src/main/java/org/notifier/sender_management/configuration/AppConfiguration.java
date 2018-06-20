package org.notifier.sender_management.configuration;

import org.notifier.sender_management.configuration.property.InteractionProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@ComponentScan({"org.notifier"})
@EnableConfigurationProperties({InteractionProperties.class})
public class AppConfiguration {
    private Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

    @Bean(name = "appRestClient")
    public RestTemplate getRestClient() {
        RestTemplate restClient = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        // Add one interceptor like in your example, except using anonymous class.
        restClient.setInterceptors(Collections.singletonList((request, body, execution) -> {

            logger.debug("Intercepting...");
            return execution.execute(request, body);
        }));

        return restClient;
    }
}
