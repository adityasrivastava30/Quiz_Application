package com.aditya.question_service.Dao;


import com.aditya.question_service.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question , Integer> {

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :Category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer>findRandomQuestionByCategory(@RequestParam("Category") String Category, @RequestParam("numQ") int numQ);

    List<Question> findByCategory(String category);

}
