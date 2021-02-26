package com.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Paul Roode
 */
@Controller
public class BuddyInfoController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @PostMapping(value = "addressbook/{id}/buddy")
    public String addBuddyInfo(@PathVariable long id, @RequestParam(name = "name") String name, @RequestParam(name = "address") String address,
                               @RequestParam(name = "phone-num") String phoneNum, Model model) {
        AddressBook addressBook = addressBookRepository.findById(id);
        addressBook.addBuddy(new BuddyInfo(name, address, phoneNum));
        addressBookRepository.save(addressBook);
        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", addressBook.getBuddies());
        return "view_addressbook";
    }

    @PostMapping(value = "/addressbook/{id}/buddy", produces = "application/json")
    @ResponseBody
    public AddressBook addBuddyInfo(@PathVariable long id, @RequestParam(name = "name") String name,
                                    @RequestParam(name = "address") String address, @RequestParam(name = "phone-num") String phoneNum) {
        AddressBook addressBook = addressBookRepository.findById(id);
        addressBook.addBuddy(new BuddyInfo(name, address, phoneNum));
        addressBookRepository.save(addressBook);
        return addressBook;
    }

    @DeleteMapping(value = "addressbook/{id}/buddy")
    public String removeBuddyInfo(@PathVariable long id, @RequestParam(name = "name") String name, Model model) {
        List<BuddyInfo> buddyInfos = buddyInfoRepository.findByName(name);
        BuddyInfo buddyInfo = buddyInfos.isEmpty() ? null : buddyInfos.get(0);
        AddressBook addressBook = addressBookRepository.findById(id);
        addressBook.removeBuddy(buddyInfo);
        addressBookRepository.save(addressBook);
        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", addressBook.getBuddies());
        return "view_addressbook";
    }

    @DeleteMapping(value = "/addressbook/{id}/buddy", produces = "application/json")
    @ResponseBody
    public AddressBook removeBuddyInfo(@PathVariable long id, @RequestParam(name = "name") String name) {
        List<BuddyInfo> buddyInfos = buddyInfoRepository.findByName(name);
        BuddyInfo buddyInfo = buddyInfos.isEmpty() ? null : buddyInfos.get(0);
        AddressBook addressBook = addressBookRepository.findById(id);
        addressBook.removeBuddy(buddyInfo);
        addressBookRepository.save(addressBook);
        return addressBook;
    }

}
