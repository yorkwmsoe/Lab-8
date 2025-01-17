package com.unicorn.location;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.jr.ob.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnicornPostLocationHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Logger logger = LoggerFactory.getLogger(UnicornPostLocationHandler.class);

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        logger.info("Received a request here!");
        try {
            UnicornLocation unicornLocation = JSON.std.beanFrom(UnicornLocation.class, input.getBody());

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody("Received unicorn location: " + JSON.std.asString(unicornLocation));
        } catch (Exception e) {
            logger.error("Error while processing the request", e);
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Error processing the request");
        }
    }
}
