/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.SelectedResponseService;
import co.edu.utp.isc.gia.examsapp.web.dto.SelectedResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CJ
 */
@RestController
@RequestMapping("selectedResponse")
@CrossOrigin(origins="*")
public class SelectedResponseController {
    
    private final SelectedResponseService selectedResponseService;

    public SelectedResponseController(SelectedResponseService selectedResponseService) {
        this.selectedResponseService = selectedResponseService;
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SelectedResponseDto selectedResponse) throws Exception {
        try {
            selectedResponse = selectedResponseService.save(selectedResponse);
            return new ResponseEntity<>(selectedResponse, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        List<SelectedResponseDto> selectedResponses = selectedResponseService.listAll();
        return new ResponseEntity<>(selectedResponses, HttpStatus.OK);
    }
    
    @GetMapping("/byexamstudentandquestion")
    public ResponseEntity<?> findByExamStudentAndQuestion(
            @RequestParam("examStudentId") Long examStudentId,
            @RequestParam("ansOptId") Long ansOptId) throws Exception {
        try {
            SelectedResponseDto selectedResponse;
            selectedResponse = selectedResponseService.findByExamStudentAndAnsOpt(
                    examStudentId, ansOptId);
            if (selectedResponse != null) {
                return new ResponseEntity<>(selectedResponse, HttpStatus.OK);    
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        SelectedResponseDto selectedResponse = selectedResponseService.findOne(id);
        if (selectedResponse == null) return new ResponseEntity<> ( 
                "SelectedResponse doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(selectedResponse, HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody SelectedResponseDto selectedResponse) 
            throws Exception {
        SelectedResponseDto prof;
        try {
            prof = selectedResponseService.update(selectedResponse);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(prof, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        SelectedResponseDto selectedResponse = selectedResponseService.delete(id);
        if (selectedResponse == null) return new ResponseEntity<>(
                "SelectedResponse doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(selectedResponse, HttpStatus.OK);
    }
    
}
