package com.jpmc.review06092022.controller;

import com.jpmc.review06092022.models.Tutorial;
import com.jpmc.review06092022.repo.ITutorialRepo;
import com.jpmc.review06092022.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    ITutorialRepo tutorialRepo;

    @Autowired
    TutorialService tutorialService;

    // get all tutorials
    @GetMapping("/all")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        try {
            List<Tutorial> tList = tutorialService.findAllTutorials();
            if (!tList.isEmpty()) {
                return new ResponseEntity<>(tList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // get tutorial by id
    @GetMapping("/find/{tid}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("tid") long tutorialId) {
        try {
            Tutorial tutorial;
            if (tutorialRepo.existsById(tutorialId)) {
                Tutorial retrievedTutorial = tutorialService.findTutorialById(tutorialId);
                return new ResponseEntity<>(retrievedTutorial, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // get tutorial by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Tutorial>> getTutorialByTitle(@PathVariable("title") String tt) {
        try {
            List<Tutorial> tutorialList = tutorialService.findTutorialByContaining(tt);
            if (!tutorialList.isEmpty()) {
                return new ResponseEntity<>(tutorialList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // get tutorial by published
    // http://localhost:8080/api/find/tutorial?published=true
    @GetMapping("/find/tutorial")
    public ResponseEntity<List<Tutorial>> getAllTutorialsByPublished(@RequestParam("published") boolean isPublished) {
        return new ResponseEntity<>(tutorialRepo.findByPublished(isPublished), HttpStatus.OK);
    }


    // create new tutorial
    @PostMapping("/add")
    public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial newTutorial) {
//        newTutorial.setTitle(newTutorial.getTitle().toLowerCase());
        return new ResponseEntity<>(tutorialService.createTutorial(newTutorial), HttpStatus.CREATED);
    }


    // update tutorial
    @PutMapping("/update/{tid}")
    public ResponseEntity<Tutorial> modifyTutorial(@PathVariable("tid") long tutorialId, @RequestBody Tutorial newTutorial) {
        return new ResponseEntity<>(tutorialService.updateTutorial(newTutorial), HttpStatus.OK);
    }


    // delete tutorial
    // .../delete/1
    @DeleteMapping("/delete/{tid}")
    public ResponseEntity<HttpStatus> removeTutorialById(@PathVariable("tid") long tutorialId) {
        tutorialService.deleteTutorialById(tutorialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
