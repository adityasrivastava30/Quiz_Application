package com.aditya.quiz_service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    private int numQ;
    private String title;
    private String categoryName;
}
