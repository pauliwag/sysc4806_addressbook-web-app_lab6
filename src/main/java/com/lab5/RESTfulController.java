package com.lab5;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTfulController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyInfoRepository buddyInfoRepository;

    public RESTfulController(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @PostMapping("/postAddressBook")
    public AddressBook postAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        return addressBook;
    }

    @PostMapping("/postBuddy/{addressBookId}/{name}/{address}/{phoneNumber}")
    public BuddyInfo postBuddy(@PathVariable long addressBookId, @PathVariable String name, @PathVariable String address, @PathVariable String phoneNumber) {
        BuddyInfo buddy = new BuddyInfo(address, name, phoneNumber);
        AddressBook addressBook = addressBookRepository.findById(addressBookId);
        addressBook.getBuddies().add(buddy);
        addressBookRepository.save(addressBook);
        return buddy;
    }

    @DeleteMapping("/deleteBuddy/{addressBookId}/{buddyInfoId}")
    public BuddyInfo deleteBuddy(@PathVariable long addressBookId, @PathVariable long buddyInfoId) {
        AddressBook addressBook = addressBookRepository.findById(addressBookId);
        BuddyInfo buddy = buddyInfoRepository.findById(buddyInfoId);
        addressBook.getBuddies().remove(buddy);
        buddyInfoRepository.delete(buddy);
        return buddy;
    }

}
