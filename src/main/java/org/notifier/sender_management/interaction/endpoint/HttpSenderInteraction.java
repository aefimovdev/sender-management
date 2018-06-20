package org.notifier.sender_management.interaction.endpoint;

import org.notifier.sender_management.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("httpSenderInteraction")
public class HttpSenderInteraction implements ISenderInteraction {
    private final Logger logger = LoggerFactory.getLogger(HttpSenderInteraction.class);

    private final RestTemplate appRestClient;

    public HttpSenderInteraction(RestTemplate appRestClient) {
        this.appRestClient = appRestClient;
    }

    @Override
    public RegistrationRsDto register(RegistrationRqDto message) {
        HttpEntity<RegistrationRqDto> entity = new HttpEntity<>(message);
        ResponseEntity<RegistrationRsDto> response =
                this.appRestClient.exchange("http://localhost:8080/sender/register/", HttpMethod.POST,
                        entity, RegistrationRsDto.class);
        HttpStatus statusCode = response.getStatusCode();
        statusCode.is2xxSuccessful();
        return response.getBody();
    }

    @Override
    public Boolean changeState(EnableMessagingRqDto message) {
        HttpEntity<EnableMessagingRqDto> entity = new HttpEntity<>(message);
        ResponseEntity<EnableMessagingRsDto> response =
                this.appRestClient.exchange("http://localhost:8080/sender/state/", HttpMethod.PUT,
                        entity, EnableMessagingRsDto.class);

        HttpStatus statusCode = response.getStatusCode();
        statusCode.is2xxSuccessful();
        return statusCode.is2xxSuccessful();
    }

    @Override
    public RequestStateRsDto requestState(RequestStateRqDto message) {
        HttpEntity<RequestStateRqDto> entity = new HttpEntity<>(message);
        ResponseEntity<RequestStateRsDto> response =
                this.appRestClient.exchange("http://localhost:8080/sender/state/", HttpMethod.GET,
                        entity, RequestStateRsDto.class);

        HttpStatus statusCode = response.getStatusCode();
        statusCode.is2xxSuccessful();
        return response.getBody();
    }
}
