package com.example.apachecameldemo.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApplicationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        errorHandler(deadLetterChannel("mock:errorHandler"));

        onException(HttpOperationFailedException.class)
                .logContinued("${exception}")
                .to("mock:errorHandler");

        /*from("timer://myTimer?period={{timer.period}}")
                .routeId("my app route")
                .setBody(simple("Hello world at ${header.firedTime}"))
                //.setBody(simple("select * from country?dataSource=dataSource"))
                .to("stream:out");*/
        /*from("timer:myTimer?period=5000")
                .log("Route started")
                .to("mock:anotherRouter");*/
        /**
         * from("timer:scheduler?period=120000")
         *             .log("Scheduled job!")
         *             .to("direct:httpRoute");
         */

        /**
         * from("direct:httpRoute")
         *             .log("Http Route started")
         *             .setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.GET)
         *             .to("https://api.bittrex.com/api/v1.1/public/getcurrencies")
         *             .log("Response : ${body}");
         */
        from("timer:scheduler?period=120000")
                .log("Scheduled job!")
                .to("direct:httpRoute");

        from("direct:httpRoute")
                .log("Http Route started")
                .setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.GET)
                .to("https://api.bittrex.com/api/v1.1/public/getcurrencies")
                .log("Response : ${body}");

        /*from("timer:myTimer?period=5000")
                .log("Running SQL query at ${header.firedTime}")
                .setBody(simple("select * from country"))
                .to("jdbc:dataSource")
                .split().simple("${body}")
                //.log("process row ${body}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Map<String, Object> row = exchange.getIn().getBody(Map.class);
                        row.get("ID").toString();
                        row.get("NAME").toString();
                        System.out.println("Processing: " + row);
                    }
                })
                .to("mock:result");*/
    }
}
