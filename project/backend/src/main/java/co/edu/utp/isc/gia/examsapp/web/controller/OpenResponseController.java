/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.OpenResponseService;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenResponseDto;
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
@RequestMapping("openResponse")
@CrossOrigin(origins="*")
public class OpenResponseController {
    
    private final OpenResponseService openResponseService;

    public OpenResponseController(OpenResponseService openResponseService) {
        this.openResponseService = openResponseService;
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody OpenResponseDto openResponse) throws Exception {
        try {
            openResponse = openResponseService.save(openResponse);
            return new ResponseEntity<>(openResponse, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        List<OpenResponseDto> openResponses = openResponseService.listAll();
        return new ResponseEntity<>(openResponses, HttpStatus.OK);
    }
    
    @GetMapping("/byexamstudentandquestion")
    public ResponseEntity<?> findByExamStudentAndQuestion(
            @RequestParam("examStudentId") Long examStudentId,
            @RequestParam("questionId") Long questionId) throws Exception {
        try {
            OpenResponseDto examStudent = 
                    openResponseService.findByExamStudentAndQuestion(examStudentId, 
                            questionId);
            return new ResponseEntity<>(examStudent, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        OpenResponseDto openResponse = openResponseService.findOne(id);
        if (openResponse == null) return new ResponseEntity<> ( 
                "OpenResponse doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(openResponse, HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody OpenResponseDto openResponse) 
            throws Exception {
        OpenResponseDto prof;
        try {
            prof = openResponseService.update(openResponse);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(prof, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        OpenResponseDto openResponse = openResponseService.delete(id);
        if (openResponse == null) return new ResponseEntity<>(
                "OpenResponse doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(openResponse, HttpStatus.OK);
    }
    
}
