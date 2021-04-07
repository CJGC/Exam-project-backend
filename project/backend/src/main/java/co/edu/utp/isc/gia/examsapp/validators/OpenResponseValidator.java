/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.OpenResponseDto;
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
public class OpenResponseValidator {

    private OpenResponseDto openResponse;
    private String exceptions = "";
    private ExamStudentValidator examStudentValidator;
    private QuestionValidator questionValidator;
    
    public void isNull() throws Exception {
        if (this.openResponse == null) {
            throw new IOException("OpenResponse object is null\n");
        }
    }

    public void validateId() throws Exception {
        if (this.openResponse.getId() == null) {
            exceptions += "OpenResponse id is null\n";
        }
    }

    public void validateContent() throws Exception {
        if (this.openResponse.getContent() == null) {
            exceptions += "OpenResponse content is null\n";
            return;
        }
        if (Pattern.matches("", this.openResponse.getContent())) {
            exceptions += "OpenResponse content is empty\n";
        }
    }

    public void validateValoration() throws Exception {
        if (this.openResponse.getValoration() == null) {
            exceptions += "OpenResponse valoration is null\n";
        }
    }

    public void validateExamStudent() throws Exception {
        if (this.openResponse.getExamStudent() == null) {
            exceptions += "OpenResponse examen student is null\n";
            return;
        }
        
        this.examStudentValidator = new ExamStudentValidator();
        this.examStudentValidator.setExamStudent(this.openResponse.getExamStudent());
        this.examStudentValidator.performValidations();
        String examStudentExceptions = this.examStudentValidator.getExceptions();
        
        if (examStudentExceptions.length() > 0) {
            this.exceptions += examStudentExceptions;
        }
    }

    public void validateQuestion() throws Exception {
        if (this.openResponse.getExamStudent() == null) {
            exceptions += "OpenResponse question is null\n";
            return;
        }
        
        this.questionValidator = new QuestionValidator();
        this.questionValidator.setQuestion(this.openResponse.getQuestion());
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
        if (!attribute.equals("content")) {
            this.validateContent();
        }
        if (!attribute.equals("valoration")) {
            this.validateValoration();
        }
        if (!attribute.equals("examstudent")) {
            this.validateExamStudent();
        }
        if (!attribute.equals("question")) {
            this.validateQuestion();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateContent();
        this.validateValoration();
        this.validateExamStudent();
        this.validateQuestion();
    }
}
