package com.example;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

/**
 * Add your first API methods in this class, or you may create another class. In that case, please
 * update your web.xml accordingly.
 **/
@Api(
        name = "jobs",
        version = "v1"
)
public class YourFirstAPI {

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, name = "helloWorld", path = "hello")
    public HelloWorld helloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setText("Hello World");
        return helloWorld;
    }
}
