package com.redhat.support;

import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;

@ApplicationScoped
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        from("platform-http:/platform-http/exception")
            .throwException(new WebApplicationException("Throwing webapp exception!"));

        // curl -X GET http://localhost:8080/platform-http/exception5 returns custom Not Found correctly (based on
        // the precedence)
        // If you remove or comment the NotFoundExceptionMapper class, the WebAppExceptionMapper exception will be used

        // curl -X GET http://localhost:8080/platform-http/exception should throw the custom web app exception based
        // on https://github.com/quarkusio/quarkus/pull/32619/files#diff-adde572432b396eee403697105ea49d00312d7c33b3c881ad7174ae3f8ba7503R38,
        // but it throws the Runtime exception defined above
    }
}
