/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.AnwerOptionService;
import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
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
@RequestMapping("ansopt")
@CrossOrigin(origins="*")
public class AnswerOptionController {
    
    private final AnwerOptionService answerOptionService;
    
    AnswerOptionController(AnwerOptionService answerOptionService) {
        this.answerOptionService = answerOptionService;
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody AnswerOptionDto ansOpt) throws Exception {
        try {
            ansOpt = answerOptionService.save(ansOpt);
            return new ResponseEntity<>(ansOpt, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> listAll() throws Exception {
        try {
            List<AnswerOptionDto> ansOpts = answerOptionService.listAll();
            return new ResponseEntity<>(ansOpts, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{questId}")
    public ResponseEntity<?> findbyQuestion(@PathVariable("questId") Long questId) throws Exception {
        try {
            List<AnswerOptionDto> ansOpts = answerOptionService.findByQuestion(questId);
            return new ResponseEntity<>(ansOpts, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
   @PutMapping
    public ResponseEntity<?> update(@RequestBody AnswerOptionDto ansOpt) 
            throws Exception {
        AnswerOptionDto _exam;
        try {
            _exam = answerOptionService.update(ansOpt);
            return new ResponseEntity<>(_exam, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        try {
            AnswerOptionDto ansOpt = answerOptionService.delete(id);
            return new ResponseEntity<>(ansOpt, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }   
}
