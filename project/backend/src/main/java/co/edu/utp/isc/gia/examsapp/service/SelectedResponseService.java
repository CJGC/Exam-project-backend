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
    
    public SelectedResponseDto save(SelectedResponseDto selectedResponse) throws Exception {        
        try {
            this.selectedResponseValidator.setselectedResponse(selectedResponse);
            this.selectedResponseValidator.performValidationsExcept("id");
            SelectedResponse auxSelectedResp = modelMapper.map(selectedResponse, 
                    SelectedResponse.class);
            auxSelectedResp = selectedResponseRepository.save(auxSelectedResp);
            return modelMapper.map(auxSelectedResp, SelectedResponseDto.class);
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
            return modelMapper.map(selectedResponseRepository.findById(id).get(), 
                SelectedResponseDto.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public SelectedResponseDto findByExamStudentAndAnsOpt(
            Long examStudentId, 
            Long ansOptId) throws Exception {
        try {
            SelectedResponse selectedResponse;
            selectedResponse = selectedResponseRepository.findByExamStudentIdAndAnswerOptionId(examStudentId, ansOptId);
            
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
    
    public SelectedResponseDto update(SelectedResponseDto selectedResponse) throws Exception {
        try {
            this.selectedResponseValidator.setselectedResponse(selectedResponse);
            this.selectedResponseValidator.performValidations();
            SelectedResponse auxSelectedResp = selectedResponseRepository.save(
                    modelMapper.map(selectedResponse, 
                    SelectedResponse.class));
            return modelMapper.map(auxSelectedResp, SelectedResponseDto.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public SelectedResponseDto delete(Long id) throws Exception {
        
        try {
            SelectedResponseDto selectedResponse = modelMapper.map(
                    selectedResponseRepository.findById(id).get(), 
                    SelectedResponseDto.class);
            selectedResponseRepository.deleteById(id);
            return selectedResponse;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
