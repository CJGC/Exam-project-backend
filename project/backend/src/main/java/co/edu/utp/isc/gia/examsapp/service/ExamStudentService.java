/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.service;

import co.edu.utp.isc.gia.examsapp.data.entity.ExamStudent;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamStudentDto;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import co.edu.utp.isc.gia.examsapp.data.repository.ExamStudentRepository;
import co.edu.utp.isc.gia.examsapp.validators.ExamStudentValidator;
import java.io.IOException;

/**
 *
 * @author CJ
 */
@Service
public class ExamStudentService {
    
    private final ExamStudentRepository examStudentRepository;
    private final ModelMapper modelMapper;
    private final ExamStudentValidator examStudentValidator;
    
    public ExamStudentService(
            ExamStudentRepository examStudentRepository, 
            ModelMapper modelMapper,
            ExamStudentValidator examStudentValidator)
    {
        this.examStudentRepository = examStudentRepository;
        this.modelMapper = modelMapper;
        this.examStudentValidator = examStudentValidator;
    }
    
    public ExamStudentDto save(ExamStudentDto examStudentDto) throws Exception {        
        try {
            this.examStudentValidator.setExamStudent(examStudentDto);
            this.examStudentValidator.setExceptions("");
            this.examStudentValidator.performValidationsExcept("id");
            String examStudentExceptions = this.examStudentValidator.getExceptions();
            
            if ( examStudentExceptions.length() > 0) {
                throw new IOException(examStudentExceptions);
            }
            
            ExamStudent examStudent = modelMapper.map(examStudentDto, ExamStudent.class);
            examStudent = examStudentRepository.save(examStudent);
            
            if (examStudent != null) {
                return modelMapper.map(examStudent, ExamStudentDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public List<ExamStudentDto> listAll() throws Exception {
        ArrayList<ExamStudent> examStudents = new ArrayList<>();
        examStudentRepository.findAll().forEach(examStudents::add);
        
        List<ExamStudentDto> examStudentsDto = new ArrayList<>();
        examStudents.forEach(examStudent -> {
            examStudentsDto.add(modelMapper.map(examStudent, ExamStudentDto.class));
        });
        return examStudentsDto;
    }
    
    public ExamStudentDto findOne(Long id) throws Exception {
        try {
            ExamStudent examStudent = examStudentRepository.findById(id).get();
            
            if (examStudent != null) {
                return modelMapper.map(examStudent, ExamStudentDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<ExamStudentDto> findByExam(Long examId) throws Exception {
        try {
            List<ExamStudentDto> examStudentsDto = new ArrayList<>(); 
            examStudentRepository.findByExamId(examId).forEach(examStudent -> {
                examStudentsDto.add(modelMapper.map(examStudent, ExamStudentDto.class));
            });
            return examStudentsDto;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
 
    public ExamStudentDto findByStudent(Long studentId) throws Exception {
        try {
            ExamStudent examStudent = examStudentRepository.findByStudentId(studentId);
            
            if(examStudent != null) {
                return modelMapper.map(examStudent, ExamStudentDto.class);
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ExamStudentDto update(ExamStudentDto examStudentDto) throws Exception {
        try {
            this.examStudentValidator.setExamStudent(examStudentDto);
            this.examStudentValidator.setExceptions("");
            this.examStudentValidator.performValidations();
            String examStudentExceptions = this.examStudentValidator.getExceptions();
            
            if (examStudentExceptions.length() > 0) {
                throw new IOException(examStudentExceptions);
            }
            ExamStudent examStudent = modelMapper.map(examStudentDto, 
                    ExamStudent.class);
            examStudent = examStudentRepository.save(examStudent);
            
            if (examStudent != null) {
                return modelMapper.map(examStudent, ExamStudentDto.class);                
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
            examStudentRepository.deleteById(id);
            return "ExamStudent deleted sucessfully";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
