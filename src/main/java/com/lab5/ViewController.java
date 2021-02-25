package com.lab5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final AddressBookRepository addressBookRepository;

    public ViewController(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("addressBooks", addressBookRepository.findAll());
        return "index";
    }

}
