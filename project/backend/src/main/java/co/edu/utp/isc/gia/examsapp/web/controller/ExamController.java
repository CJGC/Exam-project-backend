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
import org.springframework.web.bind.annotation.RequestParam;
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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> listAll() throws Exception {
        try {
            List<ExamDto> exams = examService.listAll();
            return new ResponseEntity<>(exams, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("/byprofessor")
    public ResponseEntity<?> findByProfessor(@RequestParam("id") Long professorId) throws Exception {
        try {
            List<ExamDto> exams = examService.findByProfessor(professorId);
            return new ResponseEntity<>(exams, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("/bylink")
    public ResponseEntity<?> findByLink(@RequestParam("id") String link) throws Exception {
        try {
            ExamDto exam = examService.findByLink(link);
            return new ResponseEntity<>(exam, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
   @PutMapping
    public ResponseEntity<?> update(@RequestBody ExamDto exam) 
            throws Exception {
        ExamDto _exam;
        try {
            _exam = examService.update(exam);
            return new ResponseEntity<>(_exam, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        try {
            ExamDto exam = examService.delete(id);
            return new ResponseEntity<>(exam, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
