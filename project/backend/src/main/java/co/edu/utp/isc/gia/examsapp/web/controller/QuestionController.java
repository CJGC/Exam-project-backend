/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.QuestionService;
import co.edu.utp.isc.gia.examsapp.web.dto.abstractdto.QuestionDto;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Christhian Gomez
 */
@RestController
@RequestMapping("question")
@CrossOrigin(origins="*")
public class QuestionController {
    
    private final QuestionService questionService;
    
    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody QuestionDto question) throws Exception {
        try {
            question = questionService.save(question);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> listAll() throws Exception {
        List<QuestionDto> exams = questionService.listAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        QuestionDto question = questionService.findOne(id);
        if (question == null) return new ResponseEntity<> ( 
                "Exam doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    
   @PutMapping
    public ResponseEntity<?> update(@RequestBody QuestionDto question) 
            throws Exception {
        QuestionDto _exam;
        try {
            _exam = questionService.update(question);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_exam, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        QuestionDto question = questionService.delete(id);
        if (question == null) return new ResponseEntity<>(
                "Exam doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }   
}
