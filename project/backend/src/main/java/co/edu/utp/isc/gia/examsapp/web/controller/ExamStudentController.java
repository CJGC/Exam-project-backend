/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.ExamStudentService;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamStudentDto;
import java.io.IOException;
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
@CrossOrigin(origins = "*")
public class ExamStudentController {

    private final ExamStudentService examStudentService;

    public ExamStudentController(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ExamStudentDto examStudentDto) throws Exception {
        try {
            examStudentDto = examStudentService.save(examStudentDto);
            return new ResponseEntity<>(examStudentDto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        try {
            List<ExamStudentDto> examStudentsDto = examStudentService.listAll();
            return new ResponseEntity<>(examStudentsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byexam")
    public ResponseEntity<?> findByExam(@RequestParam("id") Long examId) throws Exception {
        try {
            List<ExamStudentDto> examStudentsDto = examStudentService.findByExam(examId);
            return new ResponseEntity<>(examStudentsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bystudent")
    public ResponseEntity<?> findByStudent(@RequestParam("id") Long studentId) throws Exception {
        try {
            ExamStudentDto examStudentDto = examStudentService.findByStudent(studentId);
            return new ResponseEntity<>(examStudentDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        try {
            ExamStudentDto examStudentDto = examStudentService.findOne(id);
            return new ResponseEntity<>(examStudentDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ExamStudentDto examStudent)
            throws Exception {
        try {
            ExamStudentDto examStudentDto = examStudentService.update(examStudent);
            return new ResponseEntity<>(examStudentDto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
            throws Exception {
        try {
            String message = examStudentService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
