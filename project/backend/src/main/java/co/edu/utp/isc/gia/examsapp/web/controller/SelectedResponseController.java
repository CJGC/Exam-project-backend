/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.SelectedResponseService;
import co.edu.utp.isc.gia.examsapp.web.dto.SelectedResponseDto;
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
@RequestMapping("selectedResponse")
@CrossOrigin(origins = "*")
public class SelectedResponseController {

    private final SelectedResponseService selectedResponseService;

    public SelectedResponseController(SelectedResponseService selectedResponseService) {
        this.selectedResponseService = selectedResponseService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SelectedResponseDto selectedResponseDto) throws Exception {
        try {
            selectedResponseDto = selectedResponseService.save(selectedResponseDto);
            return new ResponseEntity<>(selectedResponseDto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        try {
            List<SelectedResponseDto> selectedResponsesDto = selectedResponseService.listAll();
            return new ResponseEntity<>(selectedResponsesDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byexamstudentandquestion")
    public ResponseEntity<?> findByExamStudentAndQuestion(@RequestParam("examStudentId") Long examStudentId,
            @RequestParam("ansOptId") Long ansOptId) throws Exception {
        try {
            SelectedResponseDto selectedResponseDto = selectedResponseService.findByExamStudentAndAnsOpt(
                    examStudentId, ansOptId);
            return new ResponseEntity<>(selectedResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Exception {
        try {
            SelectedResponseDto selectedResponseDto = selectedResponseService.findOne(id);
            return new ResponseEntity<>(selectedResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody SelectedResponseDto selectedResponseDto)
            throws Exception {
        try {
            selectedResponseDto = selectedResponseService.update(selectedResponseDto);
            return new ResponseEntity<>(selectedResponseDto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        try {
            String message = selectedResponseService.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
