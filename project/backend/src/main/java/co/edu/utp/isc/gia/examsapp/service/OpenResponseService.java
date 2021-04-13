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
import java.io.IOException;

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
    
    public OpenResponseDto save(OpenResponseDto openResponseDto) throws Exception {        
        try {
            this.openResponseValidator.setOpenResponse(openResponseDto);
            this.openResponseValidator.setExceptions("");
            this.openResponseValidator.performValidationsExcept("id");
            String openResponseExceptions = this.openResponseValidator.getExceptions();
            
            if (openResponseExceptions.length() > 0) {
                throw new IOException(openResponseExceptions);
            }
            
            OpenResponse openResponse = modelMapper.map(openResponseDto, OpenResponse.class);
            openResponse = openResponseRepository.save(openResponse);
            
            if (openResponse != null) {
                return modelMapper.map(openResponse, OpenResponseDto.class);                
            } else {
                return null;
            }

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
            OpenResponse openResponse = openResponseRepository.findById(id).get();
            if (openResponse != null) {
                return modelMapper.map(openResponse, OpenResponseDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public OpenResponseDto findByExamStudentAndQuestion(Long examStudentId, 
            Long questionId) throws Exception {
        try {
            OpenResponse openResponse = 
                    openResponseRepository.findByExamStudentIdAndQuestionId(examStudentId, questionId);
            
            if (openResponse != null) {
                return modelMapper.map(openResponse, OpenResponseDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public OpenResponseDto update(OpenResponseDto openResponseDto) throws Exception {
        try {
            this.openResponseValidator.setOpenResponse(openResponseDto);
            this.openResponseValidator.setExceptions("");
            this.openResponseValidator.performValidations();
            String openResponseExceptions = this.openResponseValidator.getExceptions();
            
            if (openResponseExceptions.length() > 0) {
                throw new IOException(openResponseExceptions);
            }
            
            OpenResponse openResponse = modelMapper.map(openResponseDto, OpenResponse.class);
            openResponse = openResponseRepository.save(openResponse);
            
            if (openResponse != null) {
                return modelMapper.map(openResponse, OpenResponseDto.class);                
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
            openResponseRepository.deleteById(id);
            return "OpenResponse deleted successfully";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
