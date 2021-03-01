/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.ExamService;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
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
@RequestMapping("exam")
@CrossOrigin(origins="*")
public class ExamController {
    
    private final ExamService examService;
    
    ExamController(ExamService examService) {
        this.examService = examService;
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ExamDto exam) throws Exception {
        try {
            exam = examService.save(exam);
            return new ResponseEntity<>(exam, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> listAll() throws Exception {
        List<ExamDto> exams = examService.listAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        ExamDto exam = examService.findOne(id);
        if (exam == null) return new ResponseEntity<> ( 
                "Exam doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }
    
   @PutMapping
    public ResponseEntity<?> update(@RequestBody ExamDto exam) 
            throws Exception {
        ExamDto _exam;
        try {
            _exam = examService.update(exam);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(_exam, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        ExamDto exam = examService.delete(id);
        if (exam == null) return new ResponseEntity<>(
                "Exam doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }
}
