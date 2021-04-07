/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.OpenResponseService;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenResponseDto;
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
@RequestMapping("openResponse")
@CrossOrigin(origins = "*")
public class OpenResponseController {

    private final OpenResponseService openResponseService;

    public OpenResponseController(OpenResponseService openResponseService) {
        this.openResponseService = openResponseService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OpenResponseDto openResponseDto) throws Exception {
        try {
            openResponseDto = openResponseService.save(openResponseDto);
            return new ResponseEntity<>(openResponseDto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        try {
            List<OpenResponseDto> openResponsesDto = openResponseService.listAll();
            return new ResponseEntity<>(openResponsesDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byexamstudentandquestion")
    public ResponseEntity<?> findByExamStudentAndQuestion(
            @RequestParam("examStudentId") Long examStudentId,
            @RequestParam("questionId") Long questionId) throws Exception {
        try {
            OpenResponseDto openResponseDto = openResponseService.findByExamStudentAndQuestion(examStudentId, questionId);
            return new ResponseEntity<>(openResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        try {
            OpenResponseDto openResponseDto = openResponseService.findOne(id);
            return new ResponseEntity<>(openResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody OpenResponseDto openResponseDto)
            throws Exception {
        try {
            openResponseDto = openResponseService.update(openResponseDto);
            return new ResponseEntity<>(openResponseDto, HttpStatus.OK);
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
            String message = openResponseService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
