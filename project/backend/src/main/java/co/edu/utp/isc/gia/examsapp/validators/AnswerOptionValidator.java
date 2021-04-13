/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
import java.io.IOException;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author CJ
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AnswerOptionValidator {

    private AnswerOptionDto answerOption;
    private String exceptions = ""; 
    private QuestionValidator questionValidator;

    public void isNull() throws Exception {
        if (this.answerOption == null) {
            throw new IOException("AnswerOption object is null\n");
        }
    }

    public void validateId() throws Exception {
        if (this.answerOption.getId() == null) {
            exceptions += "AnswerOption id is null\n";
            return;
        }
        
        if (this.answerOption.getId() <= 0) {
            exceptions += "AnswerOption id is invalid\n";
        }
    }

    public void validateDescription() throws Exception {
        if (this.answerOption.getDescription() == null) {
            exceptions += "AnswerOption description is null\n";
            return;
        }
        if (Pattern.matches("", this.answerOption.getDescription())) {
            exceptions += "AnswerOption description is empty\n";
        }
    }

    public void validateCorrectAnswer() throws Exception {
        if (this.answerOption.getCorrectAnswer() == null) {
            exceptions += "AnswerOption correctAnswer is null\n";
        }
    }

    public void validateWeight() throws Exception {
        if (this.answerOption.getWeight() == null) {
            exceptions += "AnswerOption weight is null\n";
            return;
        }
        
        if (this.answerOption.getWeight() <= 0) {
            exceptions += "AnswerOption weight is invalid\n";
        }
    }

    public void validateQuestion() throws Exception {
        if (this.answerOption.getQuestion() == null) {
            exceptions += "AnswerOption question is null\n";
            return;
        }
        
        this.questionValidator = new QuestionValidator();
        this.questionValidator.setQuestion(this.answerOption.getQuestion());
        this.questionValidator.performValidations();
        String questionExceptions = this.questionValidator.getExceptions();
        
        if (questionExceptions.length() > 0) {
            this.exceptions += questionExceptions;
        }
    }

    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) {
            this.validateId();
        }
        if (!attribute.equals("description")) {
            this.validateDescription();
        }
        if (!attribute.equals("correctAnswer")) {
            this.validateCorrectAnswer();
        }
        if (!attribute.equals("weight")) {
            this.validateWeight();
        }
        if (!attribute.equals("question")) {
            this.validateQuestion();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateDescription();
        this.validateCorrectAnswer();
        this.validateWeight();
        this.validateQuestion();
    }
}
