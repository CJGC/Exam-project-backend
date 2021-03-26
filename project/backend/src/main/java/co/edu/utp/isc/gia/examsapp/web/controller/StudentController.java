/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.StudentService;
import co.edu.utp.isc.gia.examsapp.web.dto.StudentDto;
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
@RequestMapping("student")
@CrossOrigin(origins="*")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentDto student) throws Exception {
        try {
            student = studentService.save(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        List<StudentDto> students = studentService.listAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        StudentDto student = studentService.findOne(id);
        if (student == null) return new ResponseEntity<> ( 
                "Student doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    
    @GetMapping("/byIdentCard")
    public ResponseEntity<?> findByIdentificationCard(
            @RequestParam("id") String identificationCard) throws Exception {
        try {
            StudentDto student = studentService.findByIdentificationCard(identificationCard);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody StudentDto student) 
            throws Exception {
        StudentDto prof;
        try {
            prof = studentService.update(student);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(prof, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id)  
            throws Exception{
        StudentDto student = studentService.delete(id);
        if (student == null) return new ResponseEntity<>(
                "Student doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    
}
