/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.SelectedResponseDto;
import java.io.IOException;
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
public class SelectedResponseValidator {

    private SelectedResponseDto selectedResponse;
    private String exceptions = "";
    private ExamStudentValidator examStudentValidator;
    private AnswerOptionValidator ansOptValidator;
    
    public void isNull() throws Exception {
        if (this.selectedResponse == null) {
            throw new IOException("SelectedResponse object is null\n");
        }
    }

    public void validateId() throws Exception {
        if (this.selectedResponse.getId() == null) {
            exceptions += "SelectedResponse id is null\n";
        }
    }

    public void validateValoration() throws Exception {
        if (this.selectedResponse.getValoration() == null) {
            exceptions += "SelectedResponse valoration is null\n";
        }
    }

    public void validateExamStudent() throws Exception {
        if (this.selectedResponse.getExamStudent() == null) {
            exceptions += "SelectedResponse examenStudent is null\n";
            return;
        }
        
        this.examStudentValidator = new ExamStudentValidator();
        this.examStudentValidator.setExamStudent(this.selectedResponse.getExamStudent());
        this.examStudentValidator.performValidations();
        String examStudentExceptions = this.examStudentValidator.getExceptions();
        
        if (examStudentExceptions.length() > 0) {
            this.exceptions += examStudentExceptions;
        }
    }

    public void validateAnswerOption() throws Exception {
        if (this.selectedResponse.getAnswerOption() == null) {
            exceptions += "SelectedResponse answerOption is null\n";
            return;
        }
        
        this.ansOptValidator = new AnswerOptionValidator();
        this.ansOptValidator.setAnswerOption(this.selectedResponse.getAnswerOption());
        this.ansOptValidator.performValidations();
        String ansOptExceptions = this.ansOptValidator.getExceptions();
        
        if (ansOptExceptions.length() > 0) {
            this.exceptions += ansOptExceptions;
        }
    }

    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) {
            this.validateId();
        }
        if (!attribute.equals("answerOption")) {
            this.validateAnswerOption();
        }
        if (!attribute.equals("valoration")) {
            this.validateValoration();
        }
        if (!attribute.equals("examstudent")) {
            this.validateExamStudent();
        }
        if (!attribute.equals("answerOption")) {
            this.validateAnswerOption();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateValoration();
        this.validateExamStudent();
        this.validateAnswerOption();
        this.validateAnswerOption();
    }
}
