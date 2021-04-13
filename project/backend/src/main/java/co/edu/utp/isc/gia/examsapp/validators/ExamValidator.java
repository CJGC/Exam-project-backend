/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
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
public class ExamValidator {

    private ExamDto exam;
    private String exceptions = "";
    private ProfessorValidator professoValidator;

    public void isNull() throws Exception {
        if (this.exam == null) {
            throw new IOException("Exam object is null\n");
        }
    }

    public void validateId() throws Exception {
        if (this.exam.getId() == null) {
            exceptions += "Exam id is null\n";
            return;
        }

        if (this.exam.getId() <= 0) {
            exceptions += "Exam id is invalid\n";
        }
    }

    public void validateName() throws Exception {
        if (this.exam.getName() == null) {
            exceptions += "Exam name is null\n";
            return;
        }
        if (Pattern.matches("", this.exam.getName())) {
            exceptions += "Exam name is empty\n";
        }
    }

    public void validateLink() throws Exception {
        if (this.exam.getLink() == null) {
            exceptions += "Exam link is null\n";
            return;
        }
        if (Pattern.matches("", this.exam.getLink())) {
            exceptions += "Exam link is empty\n";
        }
    }

    public void validateMaxGrade() throws Exception {
        if (this.exam.getMaxGrade() == null) {
            exceptions += "Exam maxgrade is null\n";
            return;
        }

        if (this.exam.getMaxGrade() <= 0) {
            exceptions += "Exam maxgrade is invalid\n";
        }
    }

    public void validateDescription() throws Exception {
        if (this.exam.getDescription() == null) {
            exceptions += "Exam description is null\n";
            return;
        }
        if (Pattern.matches("", this.exam.getDescription())) {
            exceptions += "Exam description is empty\n";
        }
    }

    public void validateDurability() throws Exception {
        if (this.exam.getDurability() == null) {
            exceptions += "Exam durability is null\n";
            return;
        }

        if (this.exam.getDurability() <= 0) {
            exceptions += "Exam durability is invalid\n";
        }
    }

    public void validateProfessor() throws Exception {
        if (this.exam.getProfessor() == null) {
            exceptions += "Exam professor is null\n";
            return;
        }

        this.professoValidator = new ProfessorValidator();
        this.professoValidator.setProfessor(this.exam.getProfessor());
        this.professoValidator.performValidations();
        String professorExceptions = this.professoValidator.getExceptions();
        
        if (professorExceptions.length() > 0) {
            this.exceptions += professorExceptions;
        }
    }

    ;
    
    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) {
            this.validateId();
        }
        if (!attribute.equals("name")) {
            this.validateName();
        }
        if (!attribute.equals("link")) {
            this.validateLink();
        }
        if (!attribute.equals("maxgrade")) {
            this.validateMaxGrade();
        }
        if (!attribute.equals("description")) {
            this.validateDescription();
        }
        if (!attribute.equals("examtime")) {
            this.validateDurability();
        }
        if (!attribute.equals("professor")) {
            this.validateProfessor();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateName();
        this.validateLink();
        this.validateMaxGrade();
        this.validateDescription();
        this.validateDurability();
        this.validateProfessor();
    }
}
