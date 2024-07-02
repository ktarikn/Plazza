package org.example;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.*;

import java.time.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackageClasses = ComponentScanMarker.class)

public class PlazaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlazaApplication.class, args);

    }
}
