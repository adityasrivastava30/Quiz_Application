package com.aditya.quiz_service.Controller;

import com.aditya.quiz_service.Model.QuestionWrapper;
import com.aditya.quiz_service.Model.QuizDto;
import com.aditya.quiz_service.Model.Response;
import com.aditya.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody QuizDto quizDto)
    {
        System.out.println(quizDto);
        return quizService.createQuiz(quizDto.getTitle(),quizDto.getCategoryName(),quizDto.getNumQ());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable int id)
    {
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response>responses)
    {
        return quizService.calculateResult(id, responses);
    }

}
