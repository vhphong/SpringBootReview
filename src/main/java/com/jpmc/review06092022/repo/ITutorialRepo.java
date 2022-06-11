package com.jpmc.review06092022.repo;

import com.jpmc.review06092022.models.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITutorialRepo extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);

    List<Tutorial> findByTitleContainingIgnoreCase(String title);
}

