package com.jpmc.review06092022.services;

import com.jpmc.review06092022.models.Tutorial;
import com.jpmc.review06092022.repo.ITutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TutorialService {

    @Autowired
    ITutorialRepo tutorialRepo;

    // find all tutorials
    public List<Tutorial> findAllTutorials() {
        return tutorialRepo.findAll();
    }


    // find tutorial by id
    public Tutorial findTutorialById(long id) {
        return tutorialRepo.findById(id).get();
    }


    // find tutorial by published
    public List<Tutorial> findTutorialByPublished(boolean isPublished) {
        return tutorialRepo.findByPublished(isPublished);
    }


    // find tutorial by containing
    public List<Tutorial> findTutorialByContaining(String titleText) {
        return tutorialRepo.findByTitleContainingIgnoreCase(titleText);
    }


    // create
    public Tutorial createTutorial(Tutorial newT) {
        return tutorialRepo.save(newT);
    }


    // update
    public Tutorial updateTutorial(Tutorial tt) {
        Tutorial newT = new Tutorial();

        return tutorialRepo.save(tt);
    }


    // delete
    public void deleteTutorialById(long id) {
//        tutorialRepo.deleteById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
        try {
            tutorialRepo.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}