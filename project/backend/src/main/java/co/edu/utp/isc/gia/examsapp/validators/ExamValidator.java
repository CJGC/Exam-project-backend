/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.validators;

import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import java.util.regex.Pattern;

/**
 *
 * @author CJ
 */
public class ExamValidator {
    private ExamDto exam;
    
    public ExamDto getexam() {
        return exam;
    }

    public void setExam(ExamDto exam) {
        this.exam = exam;
    }
    
    public void isNull() throws Exception {
        if (this.exam == null)
            throw new Exception("Exam object is null");
     }
    
    public void validateId() throws Exception {
        if (this.exam.getId() == null)
            throw new Exception("Exam id is null");
    }
    
    public void validateName() throws Exception {
        if (this.exam.getName()== null)
            throw new Exception("Exam name is null");
        if (Pattern.matches("", this.exam.getName()))
            throw new Exception ("Exam name is empty");
    }
    
    public void validateLink() throws Exception {
        if (this.exam.getLink()== null)
            throw new Exception("Exam link is null");
        if (Pattern.matches("", this.exam.getLink()))
            throw new Exception ("Exam link is empty");
    }

    public void validateMaxGrade() throws Exception {
        if (this.exam.getMaxGrade() == null)
            throw new Exception("Exam maxgrade is null");
    }
    
    public void validateDescription() throws Exception {
        if (this.exam.getDescription() == null)
            throw new Exception("Exam description is null");
        if (Pattern.matches("", this.exam.getDescription()))
            throw new Exception ("Exam description is empty");
    }
    
    public void validateDurability() throws Exception {
        if (this.exam.getDurability()== null)
            throw new Exception("Exam durability is null");
    }
    
    public void validateProfessor() throws Exception {
        if (this.exam.getProfessor() == null)
            throw new Exception("Exam professor is null");
    };
    
    public void performValidationsExcept(String attribute) throws Exception {
        this.isNull();
        if (!attribute.equals("id")) this.validateId();
        if (!attribute.equals("name")) this.validateName();
        if (!attribute.equals("link")) this.validateLink();
        if (!attribute.equals("maxgrade")) this.validateMaxGrade();
        if (!attribute.equals("description")) this.validateDescription();
        if (!attribute.equals("examtime")) this.validateDurability();
        if (!attribute.equals("professor")) validateProfessor();
    }
    
    public void performValidations() throws Exception {
        this.isNull();
        this.validateId();
        this.validateName();
        this.validateLink();
        this.validateMaxGrade();
        this.validateDescription();
        this.validateDurability();
        validateProfessor();
    }
}
