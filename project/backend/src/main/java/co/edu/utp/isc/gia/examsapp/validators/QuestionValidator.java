/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.OpenQuestionDto;
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

    private OpenQuestionDto question;
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
        }
    }

    public void validateQuestionType() throws Exception {
        if (this.question.getType() == null) {
            exceptions += "Question type is null\n";
            return;
        }
        if (Pattern.matches("", this.question.getType())) {
            throw new Exception("Question questionType is empty\n");
        }
    }

    public void validateDescription() throws Exception {
        if (this.question.getDescription() == null) {
            exceptions += "question description is null\n";
            return;
        }
        if (Pattern.matches("", this.question.getDescription())) {
            throw new Exception("question description is empty\n");
        }
    }

    public void validateExam() throws Exception {
        if (this.question.getExam() == null) {
            exceptions += "question exam is null\n";
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

    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) {
            this.validateId();
        }
        if (!attribute.equals("questiontype")) {
            this.validateQuestionType();
        }
        if (!attribute.equals("description")) {
            this.validateDescription();
        }
        if (!attribute.equals("exam")) {
            this.validateExam();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateQuestionType();
        this.validateDescription();
        this.validateExam();
    }
}
