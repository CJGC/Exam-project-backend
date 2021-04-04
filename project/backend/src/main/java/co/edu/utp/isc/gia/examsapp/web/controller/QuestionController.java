/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.controller;

import co.edu.utp.isc.gia.examsapp.service.QuestionService;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenQuestionDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> save(@RequestBody OpenQuestionDto question) throws Exception {
        try {
            question = questionService.save(question);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/saveImage")
    public ResponseEntity<?> saveImage(@RequestParam("file") MultipartFile image) throws Exception {
        try {
            String fileName = questionService.saveFile(image);
            return new ResponseEntity<>(fileName, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> listAll() throws Exception {
        try {
            List<OpenQuestionDto> questions = questionService.listAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{examId}")
    public ResponseEntity<?> findbyExam(@PathVariable("examId") Long examId) throws Exception {
        try {
            List<OpenQuestionDto> questions = questionService.findByExam(examId);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
    value = "/getImage",
            produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> uploadFile(@RequestParam("imgRoute") String examId) throws Exception {
        try {
            byte[] image = questionService.uploadFile(examId);
            return new ResponseEntity<>(image, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
   @PutMapping
    public ResponseEntity<?> update(@RequestBody OpenQuestionDto question) 
            throws Exception {
        OpenQuestionDto _exam;
        try {
            _exam = questionService.update(question);
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
            OpenQuestionDto question = questionService.delete(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/delImage")
    public ResponseEntity<?> deleteImage(@RequestParam("imgRoute") String imageRoute) throws Exception {
        try {
            String fileRoute = questionService.deleteFile(imageRoute);
            return new ResponseEntity<>(fileRoute, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
