/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;


//import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenQuestionDto;
import java.util.regex.Pattern;

/**
 *
 * @author CJ
 */
public class QuestionValidator {
    private OpenQuestionDto question;
    
    public QuestionValidator() {
    }
    
    public OpenQuestionDto getquestion() {
        return question;
    }

    public void setquestion(OpenQuestionDto question) {
        this.question = question;
    }
    
    public void isNull() throws Exception {
        if (this.question == null)
            throw new Exception("Question object is null");
     }
    
    public void validateId() throws Exception {
        if (this.question.getId() == null)
            throw new Exception("Question id is null");
    }

    public void validateQuestionType() throws Exception {
        if (this.question.getType()== null)
            throw new Exception("Question type is null");
        if (Pattern.matches("", this.question.getType()))
            throw new Exception ("Question questionType is empty");
    }
    
    public void validateDescription() throws Exception {
        if (this.question.getDescription() == null)
            throw new Exception("question description is null");
        if (Pattern.matches("", this.question.getDescription()))
            throw new Exception ("question description is empty");
    }
    
    public void validateExam() throws Exception { 
        if (this.question.getExam() == null)
            throw new Exception("question exam is null");
    }
    
    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) this.validateId();
        if (!attribute.equals("questiontype")) this.validateQuestionType();
        if (!attribute.equals("description")) this.validateDescription();
        if (!attribute.equals("exam")) this.validateExam();
    }
    
    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateQuestionType();
        this.validateDescription();
        this.validateExam();
    }
}
