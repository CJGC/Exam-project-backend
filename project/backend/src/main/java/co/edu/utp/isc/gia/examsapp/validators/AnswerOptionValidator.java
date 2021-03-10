/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
import java.util.regex.Pattern;

/**
 *
 * @author CJ
 */
public class AnswerOptionValidator {
    private AnswerOptionDto answerOption;
    
    public AnswerOptionValidator() {  }

    public AnswerOptionDto getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(AnswerOptionDto answerOption) {
        this.answerOption = answerOption;
    }
    
    public void isNull() throws Exception {
        if (this.answerOption == null)
            throw new Exception("AnswerOption object is null");
     }
    
    public void validateId() throws Exception {
        if (this.answerOption.getId() == null)
            throw new Exception("AnswerOption id is null");
    }

    public void validateDescription() throws Exception {
        if (this.answerOption.getDescription() == null)
            throw new Exception("AnswerOption description is null");
        if (Pattern.matches("", this.answerOption.getDescription()))
            throw new Exception ("AnswerOption description is empty");
    }
    
    public void validateCorrectAnswer() throws Exception {
        if (this.answerOption.getCorrectAnswer() == null)
            throw new Exception("AnswerOption correctAnswer is null");
    }
    
    public void validateWeight() throws Exception {
        if (this.answerOption.getWeight() == null)
            throw new Exception("AnswerOption weight is null");
    }
    
    public void validateQuestion() throws Exception {
        if (this.answerOption.getQuestion() == null)
            throw new Exception("AnswerOption question is null");
    }
    
    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) this.validateId();
        if (!attribute.equals("description")) this.validateDescription();
        if (!attribute.equals("correctAnswer")) this.validateCorrectAnswer();
        if (!attribute.equals("weight")) this.validateWeight();
        if (!attribute.equals("question")) this.validateQuestion();
    }
    
    public void performValidations() throws Exception {
        this.validateId();
        this.validateDescription();
        this.validateCorrectAnswer();
        this.validateWeight();
        this.validateQuestion();
    }
}
