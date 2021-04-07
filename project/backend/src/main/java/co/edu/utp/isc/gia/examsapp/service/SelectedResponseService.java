/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.service;

import co.edu.utp.isc.gia.examsapp.data.entity.SelectedResponse;
import co.edu.utp.isc.gia.examsapp.web.dto.SelectedResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import co.edu.utp.isc.gia.examsapp.data.repository.SelectedResponseRepository;
import co.edu.utp.isc.gia.examsapp.validators.SelectedResponseValidator;
import java.io.IOException;

/**
 *
 * @author CJ
 */
@Service
public class SelectedResponseService {
    
    private final SelectedResponseRepository selectedResponseRepository;
    private final ModelMapper modelMapper;
    private final SelectedResponseValidator selectedResponseValidator;
    
    public SelectedResponseService(
            SelectedResponseRepository selectedResponseRepository, 
            ModelMapper modelMapper,
            SelectedResponseValidator selectedResponseValidator)
    {
        this.selectedResponseRepository = selectedResponseRepository;
        this.modelMapper = modelMapper;
        this.selectedResponseValidator = selectedResponseValidator;
    }
    
    public SelectedResponseDto save(SelectedResponseDto selectedResponseDto) throws Exception {        
        try {
            this.selectedResponseValidator.setSelectedResponse(selectedResponseDto);
            this.selectedResponseValidator.performValidationsExcept("id");
            this.selectedResponseValidator.setExceptions("");
            String selectedResponseExceptions = this.selectedResponseValidator.getExceptions();
            
            if (selectedResponseExceptions.length() > 0) {
                throw new IOException(selectedResponseExceptions);
            }
            
            SelectedResponse selectedResponse = modelMapper.map(selectedResponseDto, SelectedResponse.class); 
            selectedResponse = selectedResponseRepository.save(selectedResponse);
            
            if (selectedResponse != null) {
                return modelMapper.map(selectedResponse, SelectedResponseDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public List<SelectedResponseDto> listAll() throws Exception {
        ArrayList<SelectedResponse> selectedResponses = new ArrayList<>();
        selectedResponseRepository.findAll().forEach(selectedResponses::add);
        
        List<SelectedResponseDto> selectedResponsesDto = new ArrayList<>();
        selectedResponses.forEach(selectedResponse -> {
            selectedResponsesDto.add(modelMapper.map(selectedResponse, 
                    SelectedResponseDto.class));
        });
        return selectedResponsesDto;
    }
    
    public SelectedResponseDto findOne(Long id) throws Exception {
        try {
            SelectedResponse selectedResponse = selectedResponseRepository.findById(id).get();
            
            if (selectedResponse != null) {
                return modelMapper.map(selectedResponse, SelectedResponseDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public SelectedResponseDto findByExamStudentAndAnsOpt(Long examStudentId, 
            Long ansOptId) throws Exception {
        try {
            SelectedResponse selectedResponse;
            selectedResponse = selectedResponseRepository.
                    findByExamStudentIdAndAnswerOptionId(examStudentId, ansOptId);
            
            if (selectedResponse != null) { 
                return modelMapper.map(selectedResponse, SelectedResponseDto.class);
            }
            else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public SelectedResponseDto update(SelectedResponseDto selectedResponseDto) throws Exception {
        try {
            this.selectedResponseValidator.setSelectedResponse(selectedResponseDto);
            this.selectedResponseValidator.setExceptions("");
            this.selectedResponseValidator.performValidations();
            String selectedResponseExceptions = this.selectedResponseValidator.getExceptions();
            
            if (selectedResponseExceptions.length() > 0) {
                throw new IOException(selectedResponseExceptions);
            }
            
            SelectedResponse selectedResponse = selectedResponseRepository.save(
                    modelMapper.map(selectedResponseDto, SelectedResponse.class));
            
            if (selectedResponse != null) {
                return modelMapper.map(selectedResponse, SelectedResponseDto.class);
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
            selectedResponseRepository.deleteById(id);
            return "Selected response deleted successfully";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
