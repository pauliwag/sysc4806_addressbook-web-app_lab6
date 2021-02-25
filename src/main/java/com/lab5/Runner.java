package com.lab5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Runner {

    private static final Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {

        return args -> {

            // save a couple buddies
            BuddyInfo johnDoe = new BuddyInfo("John Doe", "101 John Doe Lane", "177-Doe-John");
            BuddyInfo janeDoe = new BuddyInfo("Jane Doe", "202 Jane Doe Lane", "277-Doe-Jane");
            buddyInfoRepository.save(johnDoe);
            buddyInfoRepository.save(janeDoe);

            // add the buddies to an address book and save it
            AddressBook addressBook = new AddressBook();
            addressBookRepository.save(addressBook);
            addressBook.getBuddies().add(johnDoe);
            addressBook.getBuddies().add(janeDoe);
            addressBookRepository.save(addressBook);

            // fetch all buddies
            log.info("Buddies found with findAll():");
            log.info("-------------------------------");
            for (BuddyInfo buddy : buddyInfoRepository.findAll()) {
                log.info(buddy.toString());
            }
            log.info("");

            // fetch an individual buddy by ID
            BuddyInfo buddy = buddyInfoRepository.findById(2L);
            log.info("Buddies found with findById(2L):");
            log.info("--------------------------------");
            log.info(buddy.toString());
            log.info("");

            // fetch buddies by last name
            log.info("Buddies found with findByName('Jane Doe'):");
            log.info("--------------------------------------------");
            buddyInfoRepository.findByName("Jane Doe").forEach(jane -> log.info(jane.toString()));
            log.info("");

        };

    }

}