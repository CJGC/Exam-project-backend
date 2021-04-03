/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.ExamStudentService;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamStudentDto;
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
@RequestMapping("examStudent")
@CrossOrigin(origins="*")
public class ExamStudentController {
    
    private final ExamStudentService examStudentService;

    public ExamStudentController(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ExamStudentDto examStudent) throws Exception {
        try {
            examStudent = examStudentService.save(examStudent);
            return new ResponseEntity<>(examStudent, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        List<ExamStudentDto> examStudents = examStudentService.listAll();
        return new ResponseEntity<>(examStudents, HttpStatus.OK);
    }
    
    @GetMapping("/byexam")
    public ResponseEntity<?> findByExam(@RequestParam("id") Long examId) throws Exception {
        try {
            List<ExamStudentDto> examStudents = examStudentService.findByExam(examId);
            return new ResponseEntity<>(examStudents, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
        
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        ExamStudentDto examStudent = examStudentService.findOne(id);
        if (examStudent == null) return new ResponseEntity<> ( 
                "ExamStudent doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(examStudent, HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ExamStudentDto examStudent) 
            throws Exception {
        ExamStudentDto prof;
        try {
            prof = examStudentService.update(examStudent);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(prof, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        ExamStudentDto examStudent = examStudentService.delete(id);
        if (examStudent == null) return new ResponseEntity<>(
                "ExamStudent doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(examStudent, HttpStatus.OK);
    }
    
}
