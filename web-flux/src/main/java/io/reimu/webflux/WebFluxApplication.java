package io.reimu.webflux;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class WebFluxApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebFluxApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }

}
