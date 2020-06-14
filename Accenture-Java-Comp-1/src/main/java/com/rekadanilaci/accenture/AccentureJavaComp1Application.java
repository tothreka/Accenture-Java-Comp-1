package com.rekadanilaci.accenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The goal of the project is to create a Backend that can serve as part of
 * a complex application used for managing the occupation of the office
 * after the COVID pandemic.
 *
 * The employees must be able to send their reservations to the
 * application and check the status of their reservations.
 *
 * The application will decide, who can enter the office, thus it must be
 * integrated with the existing check-in system.
 */

@SpringBootApplication
public class AccentureJavaComp1Application {

    public static void main(String[] args) {
        SpringApplication.run(AccentureJavaComp1Application.class, args);
    }

}
