/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.OpenQuestionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.abstractdto.QuestionDto;
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
public class QuestionValidator {

    private QuestionDto question;
    private String exceptions = "";
    private ExamValidator examValidator;

    public void isNull() throws Exception {
        if (this.question == null) {
            throw new IOException("Question object is null\n");
        }
    }

    public void validateId() throws Exception {
        if (this.question.getId() == null) {
            exceptions += "Question id is null\n";
            return;
        }
        
        if (this.question.getId() <= 0) {
            exceptions += "Question id is invalid\n";            
        }
    }

    public void validateQuestionType() throws Exception {
        if (this.question.getType() == null) {
            exceptions += "Question type is null\n";
            return;
        }
        
        if (Pattern.matches("", this.question.getType())) {
            exceptions += "Question type is empty\n";
            return;
        }
        
        if (!Pattern.matches("op|mu|mm", this.question.getType())) {
            exceptions += "Question type is invalid\n";
        }
    }

    public void validateWeight() throws Exception {
        if (this.question.getId() == null) {
            exceptions += "Question weight is null\n";
            return;
        }
 
        if (this.question.getId() <= 0) {
            exceptions += "Question weight is invalid\n";
        }
    }
    
    public void validateDescription() throws Exception {
        if (this.question.getDescription() == null) {
            exceptions += "Question description is null\n";
            return;
        }
        
        if (Pattern.matches("", this.question.getDescription())) {
            exceptions += "Question description is empty\n";
        }
    }

    public void validateExam() throws Exception {
        if (this.question.getExam() == null) {
            exceptions += "Question exam is null\n";
            return;
        }
        
        this.examValidator = new ExamValidator();
        this.examValidator.setExam(this.question.getExam());
        this.examValidator.performValidations();
        String examExceptions = this.examValidator.getExceptions();
        
        if (examExceptions.length() > 0) {
            this.exceptions += examExceptions;
        }
    }

    public void validateQuestionImage() throws Exception {
        if (this.question.getQuestionImage() == null) {
            exceptions += "Question image is null\n";
        }
    }
    
    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) {
            this.validateId();
        }
        if (!attribute.equals("questiontype")) {
            this.validateQuestionType();
        }
        if (!attribute.equals("weight")) {
            this.validateWeight();
        }
        if (!attribute.equals("description")) {
            this.validateDescription();
        }
        if (!attribute.equals("questionImage")) {
            this.validateQuestionImage();
        }
        if (!attribute.equals("exam")) {
            this.validateExam();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateQuestionType();
        this.validateWeight();
        this.validateDescription();
        this.validateQuestionImage();
        this.validateExam();
    }
}
