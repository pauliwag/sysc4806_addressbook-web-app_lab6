package com.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Paul Roode
 */
@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping(value = "/addressbook/{id}")
    public String getAddressBook(@PathVariable long id, Model model) {
        AddressBook addressBook = addressBookRepository.findById(id);
        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", addressBook.getBuddies());
        return "view_addressbook";
    }

    @GetMapping(value = "/addressbook/{id}", produces = "application/json")
    @ResponseBody
    public AddressBook getAddressBook(@PathVariable long id) {
        return addressBookRepository.findById(id);
    }

    @GetMapping("/addressbook")
    public String viewAddressBook(@RequestParam("id") long id) {
        return "redirect:/addressbook/" + id;
    }

    @PostMapping(value = "/addressbook")
    public String createAddressBook(Model model) {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        model.addAttribute("addressBook", addressBook);
        return "create_addressbook";
    }

    @PostMapping(value = "/addressbook", produces = "application/json")
    @ResponseBody
    public AddressBook createAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        return addressBook;
    }

    @GetMapping("/add_buddyinfo")
    public String addBuddy(@RequestParam("id") long id, Model model) {
        model.addAttribute("id", id);
        return "add_buddyinfo";
    }

}