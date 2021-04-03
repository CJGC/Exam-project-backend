/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.service;

import co.edu.utp.isc.gia.examsapp.data.entity.OpenResponse;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import co.edu.utp.isc.gia.examsapp.data.repository.OpenResponseRepository;
import co.edu.utp.isc.gia.examsapp.validators.OpenResponseValidator;

/**
 *
 * @author CJ
 */
@Service
public class OpenResponseService {
    
    private final OpenResponseRepository openResponseRepository;
    private final ModelMapper modelMapper;
    private final OpenResponseValidator openResponseValidator;
    
    public OpenResponseService(
            OpenResponseRepository openResponseRepository, 
            ModelMapper modelMapper,
            OpenResponseValidator openResponseValidator)
    {
        this.openResponseRepository = openResponseRepository;
        this.modelMapper = modelMapper;
        this.openResponseValidator = openResponseValidator;
    }
    
    public OpenResponseDto save(OpenResponseDto openResponse) throws Exception {        
        try {
            this.openResponseValidator.setopenResponse(openResponse);
            this.openResponseValidator.performValidationsExcept("id");
            OpenResponse auxStud = modelMapper.map(openResponse, OpenResponse.class);
            auxStud = openResponseRepository.save(auxStud);
            return modelMapper.map(auxStud, OpenResponseDto.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public List<OpenResponseDto> listAll() throws Exception {
        ArrayList<OpenResponse> openResponses = new ArrayList<>();
        openResponseRepository.findAll().forEach(openResponses::add);
        
        List<OpenResponseDto> openResponsesDto = new ArrayList<>();
        openResponses.forEach(openResponse -> {
            openResponsesDto.add(modelMapper.map(openResponse, OpenResponseDto.class));
        });
        return openResponsesDto;
    }
    
    public OpenResponseDto findOne(Long id) throws Exception {
        try {
            return modelMapper.map(openResponseRepository.findById(id).get(), 
                OpenResponseDto.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public OpenResponseDto findByExamStudentAndQuestion(
            Long examStudentId, 
            Long questionId) throws Exception {
        try {
            return modelMapper.map(
                    openResponseRepository.findByExamStudentIdAndQuestionId(
                            examStudentId, questionId), OpenResponseDto.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public OpenResponseDto update(OpenResponseDto openResponse) throws Exception {
        try {
            this.openResponseValidator.setopenResponse(openResponse);
            this.openResponseValidator.performValidations();
            OpenResponse auxStud = openResponseRepository.save(modelMapper.map(openResponse, 
                    OpenResponse.class));
            return modelMapper.map(auxStud, OpenResponseDto.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public OpenResponseDto delete(Long id) throws Exception {
        
        try {
            OpenResponseDto openResponse = modelMapper.map(openResponseRepository.findById(id).get(), 
                    OpenResponseDto.class);
            openResponseRepository.deleteById(id);
            return openResponse;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
