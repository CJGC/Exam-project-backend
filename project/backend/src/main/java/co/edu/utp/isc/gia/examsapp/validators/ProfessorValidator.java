/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

//import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
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
@Setter @Getter
@NoArgsConstructor
public class ProfessorValidator {
    private ProfessorDto professor;
    private String exceptions = "";
            
    public void isNull() throws Exception {
        if (this.professor == null) {
            throw new IOException("Professor object is null\n");
        }
     }
    
    public void validateId() throws Exception {
        if (this.professor.getId() == null) {
            exceptions += "Professor's id is null\n";
            return;
        }
        
        if (this.professor.getId() <= 0)
            exceptions += "Professor's id is invalid\n";
    }

    public void validateIdentificationCard() throws Exception {
        if (this.professor.getIdentificationCard()== null) {
            exceptions += "Professor's identification card is null\n";
            return;
        }
        if (Pattern.matches("", this.professor.getIdentificationCard())) {
            exceptions += "Professor's identification card is empty\n";
            return;
        }
        if (!Pattern.matches("[0-9]+", this.professor.getIdentificationCard()))
            exceptions += "Professor's identification card is invalid\n";
    }
    
    public void validateName() throws Exception {
        if (this.professor.getName()== null) {
            exceptions += "Professor's name is null\n";
            return;
        }
            
        if (Pattern.matches("", this.professor.getName())) {
            exceptions += "Professor's name is empty\n";
        }
 
    }

    public void validateLastname() throws Exception {
        if (this.professor.getLastname()== null) {
            exceptions += "Professor's lastname is null\n";
            return;
        }
         
        if (Pattern.matches("", this.professor.getLastname())) {
            exceptions += "Professor's lastname is empty\n";
        }
        
    }
    
    public void validateEmail() throws Exception {
        if (this.professor.getEmail()== null) {
            exceptions += "Professor's email is null\n";
            return;
        }
            
        if (Pattern.matches("", this.professor.getEmail())) {
            exceptions += "Professor's email is empty\n";
            return;
        }
        
        if (!Pattern.matches("[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+",
                this.professor.getEmail()))
            exceptions += "Professor's email is invalid\n";
    }
    
    public void validateUsername() throws Exception {
        if (this.professor.getUsername() == null) {
            exceptions += "Professor's username is null\n";
            return;
        }
            
        if (Pattern.matches("", this.professor.getUsername())) {
            exceptions +=  "Professor's username is empty\n";
            return;
        }
        
        if (Pattern.matches("^(^[^0-9])([\\w a-z A-Z 0-9][^@#])$", this.professor.getUsername())) {
            exceptions += "Professor's nickname is invalid\n";
        }
    }
    
    public void validatePassword() throws Exception {
        if (this.professor.getPassword() == null) {
            exceptions += "Professor's password is null\n";
            return;
        }
            
        if (Pattern.matches("", this.professor.getPassword())) {
            exceptions += "Professor's password is empty\n";
            return;
        }
        if (!Pattern.matches("([a-zA-Z]){8,16}", 
                this.professor.getPassword()))
            exceptions += "Professor's password is invalid\n";
    }

    public void performValidationsExcept(String attribute) throws Exception{
        this.isNull();
        if (!attribute.equals("id")) this.validateId();
        if (!attribute.equals("identificationCard")) this.validateIdentificationCard();
        if (!attribute.equals("name")) this.validateName();
        if (!attribute.equals("lastname")) this.validateLastname();
        if (!attribute.equals("email")) this.validateEmail();
        if (!attribute.equals("username")) this.validateUsername();
        if (!attribute.equals("password")) this.validatePassword();
    }
    
    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateIdentificationCard();
        this.validateName();
        this.validateLastname();
        this.validateEmail();
        this.validateUsername();
        this.validatePassword();
    }
}
