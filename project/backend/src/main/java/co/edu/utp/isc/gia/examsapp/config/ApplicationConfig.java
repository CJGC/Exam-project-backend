package co.edu.utp.isc.gia.examsapp.config;


import co.edu.utp.isc.gia.examsapp.validators.AnswerOptionValidator;
import co.edu.utp.isc.gia.examsapp.validators.ExamStudentValidator;
import co.edu.utp.isc.gia.examsapp.validators.ExamValidator;
import co.edu.utp.isc.gia.examsapp.validators.OpenResponseValidator;
import co.edu.utp.isc.gia.examsapp.validators.ProfessorValidator;
import co.edu.utp.isc.gia.examsapp.validators.QuestionValidator;
import co.edu.utp.isc.gia.examsapp.validators.SelectedResponseValidator;
import co.edu.utp.isc.gia.examsapp.validators.StudentValidator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CJ
 */
@Configuration
public class ApplicationConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public ProfessorValidator professorValidator() {
        return new ProfessorValidator();
    }
    
    @Bean
    public ExamValidator examValidator() {
        return new ExamValidator();
    }
    
    @Bean
    public QuestionValidator questionValidator() {
        return new QuestionValidator();
    }
    
    @Bean
    public StudentValidator studentValidator() {
        return new StudentValidator();
    }
    
    @Bean
    public ExamStudentValidator examStudentValidator() {
        return new ExamStudentValidator();
    }
    
    @Bean
    public OpenResponseValidator OpenResponseValidator() {
        return new OpenResponseValidator();
    }
    
    @Bean
    public SelectedResponseValidator SelectedResponseValidator() {
        return new SelectedResponseValidator();
    }
    
    @Bean
    public AnswerOptionValidator AnswerOptionValidator() {
        return new AnswerOptionValidator();
    }
}
