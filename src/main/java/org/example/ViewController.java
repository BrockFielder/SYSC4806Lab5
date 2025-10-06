package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {
    @Autowired
    private AddressBookRepository AddressRepo;

    @GetMapping("/")
    public String home() {


        return "Welcome to my website the process is working, this page is a placeholder";
    }


    @GetMapping("/addressbook/{id}")
    public String viewAddressBook(@PathVariable Long id, Model model) {


        AddressBook book = AddressRepo.findById(id).orElse(null);
        if (book == null) {

            model.addAttribute("bookId", id);
            model.addAttribute("buddies", java.util.List.of());
            return "addressbook";}

        model.addAttribute("bookId", id);
        model.addAttribute("buddies", book != null ? book.getBuddyList() : java.util.List.of());
        return "addressbook";
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK";
    }

    @GetMapping("/proof")
    @ResponseBody
    public String proof() {
        return "This url works";
    }
}

