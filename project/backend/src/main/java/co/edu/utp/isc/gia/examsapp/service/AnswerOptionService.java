/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.service;

import co.edu.utp.isc.gia.examsapp.data.entity.AnswerOption;
import co.edu.utp.isc.gia.examsapp.data.repository.AnswerOptionRepository;
import co.edu.utp.isc.gia.examsapp.validators.AnswerOptionValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
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
public class AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;
    private final ModelMapper modelMapper;
    private final AnswerOptionValidator answerOptionValidator;
    
    public AnswerOptionService(
            AnswerOptionRepository answerOptionRepository,
            ModelMapper modelMapper,
            AnswerOptionValidator answerOptionValidator) {
        this.answerOptionRepository = answerOptionRepository;
        this.modelMapper = modelMapper;
        this.answerOptionValidator = answerOptionValidator;
    }
    
    public AnswerOptionDto save(AnswerOptionDto ansOptDto) throws Exception {        
        try {
            this.answerOptionValidator.setAnswerOption(ansOptDto);
            this.answerOptionValidator.setExceptions("");
            this.answerOptionValidator.performValidationsExcept("id");
            String ansOptExceptions = this.answerOptionValidator.getExceptions();
            
            if (ansOptExceptions.length() > 0) {
                throw new IOException(ansOptExceptions);
            }
            
            AnswerOption ansOpt = modelMapper.map(ansOptDto ,AnswerOption.class);
            ansOpt = answerOptionRepository.save(ansOpt);
            
            if (ansOpt != null) {
                return modelMapper.map(ansOpt, AnswerOptionDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
     
     public List<AnswerOptionDto> listAll() throws Exception {
        ArrayList<AnswerOption> questions = new ArrayList<>();
        answerOptionRepository.findAll().forEach(questions::add);
        
        List<AnswerOptionDto> answerOptionsDto = new ArrayList<>();
        questions.forEach(ansOpt -> {
            answerOptionsDto.add(modelMapper.map(ansOpt, AnswerOptionDto.class));
        });
        return answerOptionsDto;
    }
    
    public List<AnswerOptionDto> findByQuestion(Long id) throws Exception {
        try {
            List<AnswerOptionDto> outAnswerOpts = new ArrayList<>();
            List<AnswerOption> asnwerOptions = answerOptionRepository.findByQuestionId(id);
            if (asnwerOptions != null) {
                asnwerOptions.forEach( ansOpt -> {
                    outAnswerOpts.add(modelMapper.map(ansOpt, AnswerOptionDto.class));   
                });
                return outAnswerOpts;
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public AnswerOptionDto update(AnswerOptionDto ansOptDto) throws Exception {
        try {
            this.answerOptionValidator.setAnswerOption(ansOptDto);
            this.answerOptionValidator.setExceptions("");
            this.answerOptionValidator.performValidations();
            String ansOptExceptions = this.answerOptionValidator.getExceptions();
            
            if (ansOptExceptions.length() > 0) {
                throw new IOException(ansOptExceptions);
            }
            
            AnswerOption ansOpt = modelMapper.map(ansOptDto, AnswerOption.class);
            ansOpt = answerOptionRepository.save(ansOpt);
            
            if (ansOpt != null) {
                return modelMapper.map(ansOpt, AnswerOptionDto.class);                
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
            answerOptionRepository.deleteById(id);
            return "AnswerOption deleted successfully";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }  
}
