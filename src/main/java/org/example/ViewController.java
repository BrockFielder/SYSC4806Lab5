package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

@Controller
public class ViewController {
    @Autowired
    private AddressBookRepository AddressRepo;

    @GetMapping("/")
    public String home() {
        if (AddressRepo.count() == 0) {
            AddressBook newBook = new AddressBook();


            BuddyInfo buddy1 = new BuddyInfo("Alice", 42, "Ottawa, ON", "957 Bank St,");
            BuddyInfo buddy2 = new BuddyInfo("John", 40, "Perth, ON", "Main St.");
            BuddyInfo buddy3 = new BuddyInfo("Peter", 42, "Maniwaki, PQ", "56 Rue Des Oblats");

            newBook.addBuddy(buddy1);
            newBook.addBuddy(buddy2);
            newBook.addBuddy(buddy3);
            AddressRepo.save(newBook);
        }
        AddressBook firstBook = AddressRepo.findAll().iterator().next();
        return "redirect:/addressbook/" + firstBook.getId();
    }

    @GetMapping("/error")
    public String error() {
        if (AddressRepo.count() == 0) {
            AddressBook newBook = new AddressBook();


            BuddyInfo buddy1 = new BuddyInfo("Alice", 42, "Ottawa, ON", "957 Bank St,");
            BuddyInfo buddy2 = new BuddyInfo("John", 40, "Perth, ON", "Main St.");
            BuddyInfo buddy3 = new BuddyInfo("Peter", 42, "Maniwaki, PQ", "56 Rue Des Oblats");

            newBook.addBuddy(buddy1);
            newBook.addBuddy(buddy2);
            newBook.addBuddy(buddy3);
            AddressRepo.save(newBook);
        }
        AddressBook firstBook = AddressRepo.findAll().iterator().next();
        return "redirect:/addressbook/" + firstBook.getId();
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
        return "Welcome to my Web App\n this page is a placeholder";
    }

    @GetMapping("/proof")
    @ResponseBody
    public String proof() {
        return "This url works";
    }
}

