package com.aditya.question_service.Service;


import com.aditya.question_service.Dao.QuestionDao;
import com.aditya.question_service.Model.Question;
import com.aditya.question_service.Model.QuestionWrapper;
import com.aditya.question_service.Model.Response;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class  QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private Question ques;


    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getByCategory(String Category) {

        try{
            return new ResponseEntity<>(questionDao.findByCategory(Category),HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> AddQuestion(Question question) {

        questionDao.save(question);
        try {
            return new ResponseEntity<>("Success" , HttpStatus.CREATED);
        }
        catch (Exception e)
        {
             e.printStackTrace();
        }
        return new ResponseEntity<>("BAD REQUEST" , HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<Question> updateQuestion(Integer id, Question question) {

        ques=questionDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Question not found"));
        ques.setQuestionTitle(question.getQuestionTitle());
        ques.setOption1(question.getOption1());
        ques.setOption2(question.getOption2());
        ques.setOption3(question.getOption3());
        ques.setOption4(question.getOption4());
        ques.setRightAnswer(question.getRightAnswer());
        ques.setDifficultyLevel(question.getDifficultyLevel());
        ques.setCategory(question.getCategory());
        questionDao.save(ques);
        try {
            return new ResponseEntity<>(question,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(ques,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> DeleteQuestion(int id)
    {
        questionDao.deleteById(id);
        try {
            return new ResponseEntity<String>("Success" , HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("NOT FOUND" , HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String Category, Integer numQuestion) {
        List<Integer>questions=questionDao.findRandomQuestionByCategory(Category,numQuestion);
        return new ResponseEntity<>(questions , HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for(Integer id: questionIds)
        {
            Question q=questionDao.findById(id).get();
            questions.add(q);
        }
        for(Question question : questions)
        {
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int result=0;
        for(Response response: responses)
        {
            Question question=questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())) {
                result++;
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
