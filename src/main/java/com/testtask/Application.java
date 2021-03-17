package com.testtask;

import com.testtask.services.CommandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final CommandService commandService;

    public Application(CommandService commandService) {
        this.commandService = commandService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String command = reader.readLine();
            System.out.println(commandService.getAnswer(command));
        }
    }
}
