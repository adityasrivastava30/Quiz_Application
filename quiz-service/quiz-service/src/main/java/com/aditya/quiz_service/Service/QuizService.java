package com.aditya.quiz_service.Service;

import com.aditya.quiz_service.Dao.QuizDao;
import com.aditya.quiz_service.Model.QuestionWrapper;
import com.aditya.quiz_service.Model.Quiz;
import com.aditya.quiz_service.Model.QuizDto;
import com.aditya.quiz_service.Model.Response;
import com.aditya.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String title , String Category , int numQ)
    {
        List<Integer>questions= quizInterface.generateQuestionForQuiz(Category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setQuestionIds(questions);
        quiz.setTitle(title);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success" , HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz= quizDao.findById(id);
        List<Integer> questionIds=quiz.get().getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionFromId(questionIds);
        return questions;

    }

   public ResponseEntity<Integer> calculateResult(Integer id, List<Response>responses) {

       ResponseEntity<Integer> score= quizInterface.getScore(responses);
       return  score;
    }
}
