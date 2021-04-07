/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.ExamStudentDto;
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
public class ExamStudentValidator {

    private ExamStudentDto examStudent;
    private String exceptions = "";
    private StudentValidator studentValidator;
    private ExamValidator examValidator;

    public void isNull() throws Exception {
        if (this.examStudent == null) {
            throw new IOException("ExamStudent object is null\n");
        }
    }

    public void validateId() throws Exception {
        if (this.examStudent.getId() == null) {
            exceptions += "ExamStudent id is null\n";
        }
    }

    public void validateDefinitiveGrade() throws Exception {
        if (this.examStudent.getDefinitiveGrade() == null) {
            exceptions += "ExamStudent definitive grade is null\n";
        }
    }

    public void validateStudent() throws Exception {
        if (this.examStudent.getStudent() == null) {
            exceptions += "ExamStudent student is null\n";
            return;
        }
        
        this.studentValidator = new StudentValidator();
        this.studentValidator.setStudent(this.examStudent.getStudent());
        this.studentValidator.performValidations();
        String studentExceptions = this.studentValidator.getExceptions();
        
        if (studentExceptions.length() > 0) {
            this.exceptions += studentExceptions;
        }
    }

    public void validateExam() throws Exception {
        if (this.examStudent.getExam() == null) {
            exceptions += "ExamStudent exam is null\n";
            return;
        }
        
        this.examValidator = new ExamValidator();
        this.examValidator.setExam(this.examStudent.getExam());
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
        if (!attribute.equals("definitiveGrade")) {
            this.validateDefinitiveGrade();
        }
        if (!attribute.equals("student")) {
            this.validateStudent();
        }
        if (!attribute.equals("exam")) {
            this.validateExam();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateDefinitiveGrade();
        this.validateStudent();
        this.validateExam();
    }
}
