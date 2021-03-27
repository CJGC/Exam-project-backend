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
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christhian Gomez
 */
@Service
public class AnwerOptionService {
    private final AnswerOptionRepository answerOptionRepository;
    private final ModelMapper modelMapper;
    private final AnswerOptionValidator answerOptionValidator;
    
    public AnwerOptionService(
            AnswerOptionRepository answerOptionRepository,
            ModelMapper modelMapper,
            AnswerOptionValidator answerOptionValidator) {
        this.answerOptionRepository = answerOptionRepository;
        this.modelMapper = modelMapper;
        this.answerOptionValidator = answerOptionValidator;
    }
    
    public AnswerOptionDto save(AnswerOptionDto ansOpt) throws Exception {        
        try {
            this.answerOptionValidator.setAnswerOption(ansOpt);
            this.answerOptionValidator.performValidationsExcept("id");
            AnswerOption auxAnsOpt = modelMapper.map(ansOpt ,AnswerOption.class);
            auxAnsOpt = answerOptionRepository.save(auxAnsOpt);
            return modelMapper.map(auxAnsOpt, AnswerOptionDto.class);
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
            answerOptionRepository.findByQuestionId(id).forEach( ansOpt -> {
                outAnswerOpts.add(modelMapper.map(ansOpt, AnswerOptionDto.class));
            });
            return outAnswerOpts;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public AnswerOptionDto update(AnswerOptionDto ansOpt) throws Exception {
        try {
            this.answerOptionValidator.setAnswerOption(ansOpt);
            this.answerOptionValidator.performValidations();
            AnswerOption auxAnsOpt = answerOptionRepository.save(modelMapper.map(ansOpt, 
                    AnswerOption.class));
            return modelMapper.map(auxAnsOpt, AnswerOptionDto.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public AnswerOptionDto delete(Long id) throws Exception {
        
        try {
            AnswerOptionDto ansOpt = modelMapper.map(answerOptionRepository.findById(id).get(), 
                    AnswerOptionDto.class);
            answerOptionRepository.deleteById(id);
            return ansOpt;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }  
}
