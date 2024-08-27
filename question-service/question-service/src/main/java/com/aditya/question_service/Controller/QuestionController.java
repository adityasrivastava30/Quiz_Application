package com.aditya.question_service.Controller;


import ch.qos.logback.core.model.INamedModel;
import com.aditya.question_service.Model.Question;
import com.aditya.question_service.Model.QuestionWrapper;
import com.aditya.question_service.Model.Response;
import com.aditya.question_service.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")

public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{Category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String Category)
    {
        return questionService.getByCategory(Category);
    }
    @PostMapping("add")
    public ResponseEntity<String> AddQuestions(@RequestBody Question question)
    {
        return questionService.AddQuestion(question);
    }
    @PutMapping("updateQuestion/{id}")
    public ResponseEntity<Question> updateQuestion( @PathVariable Integer id , @RequestBody Question question)
    {
        return questionService.updateQuestion(id,question);
    }
    @DeleteMapping("removeQuestion/{id}")
    public ResponseEntity<String> DeleteQuestion(@PathVariable Integer id)
    {
        return questionService.DeleteQuestion(id);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Integer>>generateQuestionForQuiz(@RequestParam String Category , @RequestParam Integer numQuestion)
    {
        return questionService.getQuestionForQuiz(Category, numQuestion);
    }
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer>questionIds)
    {
        return questionService.getQuestionsFromId(questionIds);
    }
    @GetMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }
    // generate
    // getQuestion (question id)
    //getScore
}

