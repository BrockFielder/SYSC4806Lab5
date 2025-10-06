/*package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuddyController {

    @Autowired
    private BuddyRepository BuddyRepository;

    @PostMapping("/api/buddies")
    public BuddyInfo createBuddy (@PathVariable Long id, @RequestBody BuddyInfo BuddyInfo){
        return BuddyRepository.save(BuddyInfo);
    }

    @GetMapping("/api/buddies/{id}")
    public BuddyInfo getBuddy(@PathVariable Long id){
        return BuddyRepository.findById(id).orElse(null);
    }

    @PutMapping("/api/buddies/{id}")
    public BuddyInfo updateBuddy (@PathVariable Long id, @RequestBody BuddyInfo BuddyInfo) {
        return BuddyRepository.save(BuddyInfo);
    }

    @DeleteMapping("/api/buddies/{id}")
    public void deleteBuddy (@PathVariable Long id) {
        BuddyInfo buddy = BuddyRepository.findById(id).orElse(null);
        if (buddy != null) {
            BuddyRepository.deleteById(id);
        }
    }
}
*/