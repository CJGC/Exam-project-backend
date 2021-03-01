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
    
    public ExamDto save(ExamDto exam) throws Exception {        
        try {
            this.examValidator.setExam(exam);
            this.examValidator.performValidationsExcept("id");
            Exam auxExam = modelMapper.map(exam ,Exam.class);
            auxExam = examRepository.save(auxExam);
            return modelMapper.map(auxExam, ExamDto.class);
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
    
    public ExamDto findOne(Long id) throws Exception {
        try {
            return modelMapper.map(examRepository.findById(id).get(), 
                ExamDto.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ExamDto update(ExamDto exam) throws Exception {
        try {
            this.examValidator.setExam(exam);
            this.examValidator.performValidations();
            Exam auxExam = examRepository.save(modelMapper.map(exam, 
                    Exam.class));
            return modelMapper.map(auxExam, ExamDto.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public ExamDto delete(Long id) throws Exception {
        
        try {
            ExamDto exam = modelMapper.map(examRepository.findById(id).get(), 
                    ExamDto.class);
            examRepository.deleteById(id);
            return exam;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
