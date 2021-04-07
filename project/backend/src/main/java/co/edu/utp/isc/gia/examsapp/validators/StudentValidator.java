/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.StudentDto;
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
public class StudentValidator {

    private StudentDto student;
    private String exceptions = "";

    public void isNull() throws Exception {
        if (this.student == null) {
            throw new IOException("Student object is null\n");
        }
    }

    public void validateId() throws Exception {
        if (this.student.getId() == null) {
            exceptions += "Student's id is null\n";
        }
    }

    public void validateIdentificationCard() throws Exception {
        if (this.student.getIdentificationCard() == null) {
            exceptions += "Student's identification card is null\n";
            return;
        }
        if (Pattern.matches("", this.student.getIdentificationCard())) {
           exceptions += "Student's identification card is empty\n";
           return;
        }
        if (!Pattern.matches("[0-9]+", this.student.getIdentificationCard())) {
           exceptions += "Student's identification card is invalid\n";
        }
    }

    public void validateName() throws Exception {
        if (this.student.getName() == null) {
            exceptions += "Student's name is null\n";
            return;
        }
        if (Pattern.matches("", this.student.getName())) {
           exceptions += "Student's name is empty\n";
        }
    }

    public void validateLastname() throws Exception {
        if (this.student.getLastname() == null) {
            exceptions += "Student's lastname is null\n";
            return;
        }
        if (Pattern.matches("", this.student.getLastname())) {
           exceptions += "Student's lastname is empty\n";
        }
    }

    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) {
            this.validateId();
        }
        if (!attribute.equals("identificationCard")) {
            this.validateIdentificationCard();
        }
        if (!attribute.equals("name")) {
            this.validateName();
        }
        if (!attribute.equals("lastname")) {
            this.validateLastname();
        }
    }

    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateIdentificationCard();
        this.validateName();
        this.validateLastname();

    }
}
