/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

//import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import java.util.regex.Pattern;

/**
 *
 * @author CJ
 */
public class ProfessorValidator {
    private ProfessorDto professor;
//    private final ExamValidator examValidator;
    
/*    public ProfessorValidator() {
        this.examValidator = new ExamValidator();
    }
 */   
    public ProfessorDto getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDto professor) {
        this.professor = professor;
    }
    
    public void isNull() throws Exception {
        if (this.professor == null)
            throw new Exception("Professor object is null");
     }
    
    public void validateId() throws Exception {
        if (this.professor.getId() == null)
            throw new Exception("Professor's id is null");
    }

    public void validateIdentificationCard() throws Exception {
        if (this.professor.getIdentificationCard()== null)
            throw new Exception("Professor's identification card is null");
        if (Pattern.matches("", this.professor.getIdentificationCard()))
            throw new Exception ("Professor's identification card is empty");
        if (!Pattern.matches("[0-9]+", this.professor.getIdentificationCard()))
            throw new Exception("Professor's identification card is not a number");
    }
    
    public void validateName() throws Exception {
        if (this.professor.getName()== null)
            throw new Exception("Professor's name is null");
        if (Pattern.matches("", this.professor.getName()))
            throw new Exception ("Professor's name is empty");
    }

    public void validateLastname() throws Exception {
        if (this.professor.getLastname()== null)
            throw new Exception("Professor's lastname is null");
        if (Pattern.matches("", this.professor.getLastname()))
            throw new Exception ("Professor's lastname is empty");
    }
    
    public void validateEmail() throws Exception {
        if (this.professor.getEmail()== null)
            throw new Exception("Professor's email is null");
        if (Pattern.matches("", this.professor.getEmail()))
            throw new Exception ("Professor's email is empty");
        if (!Pattern.matches("[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+",
                this.professor.getEmail()))
            throw new Exception ("Professor's email is invalid");
    }
    
    public void validateUsername() throws Exception {
        if (this.professor.getUsername() == null)
            throw new Exception("Professor's username is null");
        if (Pattern.matches("", this.professor.getUsername()))
            throw new Exception ("Professor's username is empty");
    }
    
    public void validatePassword() throws Exception {
        if (this.professor.getPassword() == null)
            throw new Exception("Professor's password is null");
        if (Pattern.matches("", this.professor.getPassword()))
            throw new Exception ("Professor's password is empty");
        if (!Pattern.matches("([a-zA-Z]){8,16}", 
                this.professor.getPassword()))
            throw new Exception ("Professor's password is invalid");
    }

/*    
    public void validateExams() throws Exception {
        if (this.professor.getExams()== null) return;
        for (ExamDto ex : this.professor.getExams()) {
            this.examValidator.setExam(ex);
            this.examValidator.performValidationsExcept("id");
        }
    }
 */
    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) this.validateId();
        if (!attribute.equals("identificationCard")) this.validateIdentificationCard();
        if (!attribute.equals("name")) this.validateName();
        if (!attribute.equals("lastname")) this.validateLastname();
        if (!attribute.equals("email")) this.validateEmail();
        if (!attribute.equals("username")) this.validateUsername();
        if (!attribute.equals("password")) this.validatePassword();
        //if (!attribute.equals("exams")) this.validateExams();
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
        //this.validateExams();
    }
}
