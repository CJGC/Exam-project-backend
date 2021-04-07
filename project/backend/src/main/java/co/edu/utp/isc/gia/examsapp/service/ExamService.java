/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.service;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.repository.ExamRepository;
import co.edu.utp.isc.gia.examsapp.validators.ExamValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christhian Gomez
 */
@Service
public class ExamService {
    
    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;
    private final ExamValidator examValidator;
    
    public ExamService(
            ExamRepository examRepository,
            ModelMapper modelMapper,
            ExamValidator examValidator) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
        this.examValidator = examValidator;
    }
    
    public ExamDto save(ExamDto examDto) throws Exception {        
        try {
            this.examValidator.setExam(examDto);
                        this.examValidator.setExceptions("");
            this.examValidator.performValidationsExcept("id");
            String examExceptions = this.examValidator.getExceptions();
            
            if (examExceptions.length() > 0) {
                throw new IOException(examExceptions);
            }
            
            Exam exam = modelMapper.map(examDto ,Exam.class);
            exam = examRepository.save(exam);
            
            if (exam != null) {
                return modelMapper.map(exam, ExamDto.class);                
            } else {
                return null;
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
     public List<ExamDto> listAll() throws Exception {
        ArrayList<Exam> exams = new ArrayList<>();
        examRepository.findAll().forEach(exams::add);
        
        List<ExamDto> examsDto = new ArrayList<>();
        exams.forEach(exam -> {
            examsDto.add(modelMapper.map(exam, ExamDto.class));
        });
        return examsDto;
    }
    
    public List<ExamDto> findByProfessor(Long id) throws Exception {
        try {
            List<ExamDto> exams = new ArrayList<>();
            examRepository.findByProfessorId(id).forEach( exam -> {
                exams.add(modelMapper.map(exam, ExamDto.class));
            });
            return exams;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ExamDto findByLink(String link) throws Exception {
        try {
            Exam exam = examRepository.findByLink(link);
            
            if (exam != null) {
                return modelMapper.map(exam, ExamDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ExamDto update(ExamDto examDto) throws Exception {
        try {
            this.examValidator.setExam(examDto);
            this.examValidator.setExceptions("");
            this.examValidator.performValidations();
            String examExceptions = this.examValidator.getExceptions();
            
            if (examExceptions.length() > 0) {
                throw new IOException(examExceptions);
            }
                    
            Exam exam = examRepository.save(modelMapper.map(examDto, 
                    Exam.class));
            
            if (exam != null) {
                return modelMapper.map(exam, ExamDto.class);                
            } else {
                return null;
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public String delete(Long id) throws Exception {
        
        try {
            examRepository.deleteById(id);
            return "Exam deleted successfully";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
