package com.rekadanilaci.Accenture;

import com.rekadanilaci.Accenture.domain.Office;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccentureJavaComp1Application {

    public static void main(String[] args) { SpringApplication.run(AccentureJavaComp1Application.class, args);
        Office office = new Office(250, 10);
    }

}
