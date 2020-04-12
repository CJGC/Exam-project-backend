/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;


import co.edu.utp.isc.gia.examsapp.web.dto.abstractdto.QuestionDto;
import java.util.regex.Pattern;

/**
 *
 * @author CJ
 */
public class QuestionValidator {
    private QuestionDto question;

    public QuestionDto getquestion() {
        return question;
    }

    public void setquestion(QuestionDto question) {
        this.question = question;
    }
    
    public void isNull() throws Exception {
        if (this.question == null)
            throw new Exception("question object is null");
     }
    
    public void validateId() throws Exception {
        if (this.question.getId() == null)
            throw new Exception("question's id is null");
    }

    public void validateQuestionType() throws Exception {
        if (this.question.getQuestionType() == null)
            throw new Exception("question type is null");
        if (Pattern.matches("", this.question.getQuestionType()))
            throw new Exception ("question type is empty");
    }
    
    public void validateDescription() throws Exception {
        if (this.question.getDescription() == null)
            throw new Exception("question's description is null");
        if (Pattern.matches("", this.question.getDescription()))
            throw new Exception ("question description is empty");
    }
    
    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) this.validateId();
        if (!attribute.equals("questiontype")) this.validateQuestionType();
        if (!attribute.equals("description")) this.validateDescription();
    }
    
    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateQuestionType();
        this.validateDescription();
    }
}