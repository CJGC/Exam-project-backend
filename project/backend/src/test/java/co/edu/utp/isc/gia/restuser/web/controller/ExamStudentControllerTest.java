/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.ExamStudent;
import co.edu.utp.isc.gia.examsapp.web.controller.ExamStudentController;
import co.edu.utp.isc.gia.examsapp.data.entity.Student;
import co.edu.utp.isc.gia.examsapp.service.ExamStudentService;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamStudentDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.edu.utp.isc.gia.examsapp.data.repository.ExamStudentRepository;
import co.edu.utp.isc.gia.examsapp.validators.ExamStudentValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.StudentDto;

/**
 *
 * @author CJ
 */


public class ExamStudentControllerTest {
    
    public ExamStudentControllerTest() {
    }

    private ExamStudentRepository examStudentRepository; 
    private ExamStudentController examStudentController;
    private ExamStudentValidator examStudentValidator;
/*    
    @Before
    public void init() {
        examStudentRepository = Mockito.mock(ExamStudentRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        examStudentValidator = new ExamStudentValidator();
        ExamStudentService examStudentService = new ExamStudentService(examStudentRepository, 
                modelMapper, examStudentValidator);
        examStudentController = new ExamStudentController(examStudentService);
    }
 */
    /**
     * Test of save method, of class ExamStudentController.
     */
/*
   @Test
    public void testSaveExamStudentNullObject() {
                ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent object is null", 
                        HttpStatus.BAD_REQUEST);
        
        ExamStudentDto examStudent = null;
        
        try {
            response = this.examStudentController.save(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }

    @Test
    public void testSaveExamStudentNullDefinitiveGrade() {
        ExamDto exam = new ExamDto();
        StudentDto student = new StudentDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(null, null, null, 
                student, exam);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent definitive grade is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.examStudentController.save(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
        
    }

    @Test
    public void testSaveExamStudentNullStudent() {
        ExamDto exam = new ExamDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(null, 0.0, null, 
                null, exam);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent student is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.examStudentController.save(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testSaveExamStudentNullExam() {
        StudentDto student = new StudentDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(null, 0.0, null, 
                student, null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent exam is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.examStudentController.save(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testSave() {
        Exam exam = new Exam();
        Student student = new Student();
        
        ExamStudent resulted = new ExamStudent(null, 5.0, null, 
                student, exam);

        when(examStudentRepository.save(any(ExamStudent.class))).thenReturn(resulted);
        
        ExamDto examDto = new ExamDto();
        StudentDto studentDto = new StudentDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(null,5.0, null, studentDto, examDto);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(examStudent, HttpStatus.OK);
        try {
            response = examStudentController.save(examStudent);
            
        }
        catch(Exception e) { }
        
        assertEquals(response.getHeaders(), expResult.getHeaders());

        ExamStudentDto bodyFromResponse = (ExamStudentDto) response.getBody();
        ExamStudentDto bodyFromExpResult = (ExamStudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getDefinitiveGrade(), bodyFromExpResult.getDefinitiveGrade());
    }
 */
///* Update method test */    
/*
   @Test
    public void testUpdateExamStudentNullObject() {
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent object is null", 
                        HttpStatus.BAD_REQUEST);
        
        ExamStudentDto examStudent = null;
        
        try {
            response = this.examStudentController.update(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }

    @Test
    public void testUpdateExamStudentNullDefinitiveGrade() {
        ExamDto exam = new ExamDto();
        StudentDto student = new StudentDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(1L, null, null, 
                student, exam);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent definitive grade is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.examStudentController.update(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
        
    }

    @Test
    public void testUpdateExamStudentNullStudent() {
        ExamDto exam = new ExamDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(1L, 0.0, null, 
                null, exam);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent student is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.examStudentController.update(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testUpdateStudentNullExam() {
        StudentDto student = new StudentDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(1L, 0.0, null, 
                student, null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent exam is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.examStudentController.update(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
        
    @Test
    public void testUpdateStudentNullId() {
        ExamDto exam = new ExamDto();
        StudentDto student = new StudentDto();
        
        ExamStudentDto examStudent = new ExamStudentDto(null, 0.0, null, 
                student, exam);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("ExamStudent id is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.examStudentController.update(examStudent);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }      
    
*/
    /**
     * Test of update method, of class ExamStudentController.
     */
    
    /*
    @Test
    public void testUpdate() {
        Exam exam = new Exam();
        Student student = new Student();
        
        ExamStudent resulted = new ExamStudent(1L, 0.0, null, 
                student, exam);

        when(examStudentRepository.save(any(ExamStudent.class))).thenReturn(resulted);
        
        ExamDto examDto = new ExamDto();
        StudentDto studentDto = new StudentDto();
          
        
        ExamStudentDto examStudent = new ExamStudentDto(1L, 0.0, null, 
                studentDto, examDto);   
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(examStudent, HttpStatus.OK);
        try {
            response = examStudentController.update(examStudent);
            
        }
        catch(Exception e) { }
        
        assertEquals(response.getHeaders(), expResult.getHeaders());

        ExamStudentDto bodyFromResponse = (ExamStudentDto) response.getBody();
        ExamStudentDto bodyFromExpResult = (ExamStudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getDefinitiveGrade(), bodyFromExpResult.getDefinitiveGrade());
    }

    @Test
    public void testFindOneNonExistentExamStudent() {
        when(examStudentRepository.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<?> response = null;
        try {
            response = examStudentController.findOne(1L);
        }
        catch (Exception e) {}
        
        ResponseEntity<?> expResult = 
                new ResponseEntity<> ("ExamStudent doesn't exist", 
                        HttpStatus.NOT_FOUND);
        
        assertEquals(expResult, response);

    }
    */
    /**
     * Test of findOne method, of class ExamStudentController.
     */
    /*
    @Test
    public void testFindOne() {
      
        Exam exam = new Exam();
        Student student = new Student();
        
        ExamStudent resulted = new ExamStudent(1L, 0.0, null, 
                student, exam);
        Optional<ExamStudent> op = Optional.of(resulted);
        when(examStudentRepository.findById(any(Long.class))).thenReturn(op);
        
        ResponseEntity<?> response = null;
        try {
            response = examStudentController.findOne(1L);
        }
        catch (Exception e) {}
        
        ExamDto examDto = new ExamDto();
        StudentDto studentDto = new StudentDto();
          
        
        ExamStudentDto examStudent = new ExamStudentDto(1L, 0.0, null, 
                studentDto, examDto);   
        
        ResponseEntity<?> expResult = new ResponseEntity<>(examStudent, HttpStatus.OK);
        
        assertEquals(expResult.getHeaders(), response.getHeaders());
        
        ExamStudentDto bodyFromResponse = (ExamStudentDto) response.getBody();
        ExamStudentDto bodyFromExpResult = (ExamStudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getDefinitiveGrade(), bodyFromExpResult.getDefinitiveGrade());
    }


    @Test
    public void testDeleteNonExistentExamStudent() {
        when(examStudentRepository.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<?> response = null;
        try {
            response = examStudentController.delete(1L);
        }
        catch (Exception e) {}
        
        ResponseEntity<?> expResult = 
                new ResponseEntity<> ("ExamStudent doesn't exist", 
                        HttpStatus.NOT_FOUND);
        
        assertEquals(expResult, response);
    }
*/
    /**
     * Test of delete method, of class ExamStudentController.
     */
    /*
    @Test
    public void testDelete() {
        Exam exam = new Exam();
        Student student = new Student();
        
        ExamStudent resulted = new ExamStudent(1L, 0.0, null, 
                student, exam);
        Optional<ExamStudent> op = Optional.of(resulted);
        when(examStudentRepository.findById(any(Long.class))).thenReturn(op);
        
        ResponseEntity<?> response = null;
        try {
            response = examStudentController.delete(1L);
        }
        catch (Exception e) {}
        
        ExamDto examDto = new ExamDto();
        StudentDto studentDto = new StudentDto();
          
        
        ExamStudentDto examStudent = new ExamStudentDto(1L, 0.0, null, 
                studentDto, examDto);
        ResponseEntity<?> expResult = new ResponseEntity<>(examStudent, HttpStatus.OK);
        
        assertEquals(expResult.getHeaders(), response.getHeaders());
        
        ExamStudentDto bodyFromResponse = (ExamStudentDto) response.getBody();
        ExamStudentDto bodyFromExpResult = (ExamStudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getDefinitiveGrade(), bodyFromExpResult.getDefinitiveGrade());
        
    }
*/
    /**
     * Test of listAll method, of class ExamStudentController.
     */
    /*
    @Test
    public void testListAll() {
        
        Exam exam = new Exam();
        Student student = new Student();
        ArrayList<ExamStudent> resulted = new ArrayList<>();
        
        resulted.add(new ExamStudent(1L, 0.0, null, 
                student, exam));
        resulted.add(new ExamStudent(2L, 0.0, null, 
                student, exam));
        resulted.add(new ExamStudent(3L, 0.0, null, 
                student, exam));
        
        when(examStudentRepository.findAll()).thenReturn(resulted);
        
        ExamDto examDto = new ExamDto();
        StudentDto studentDto = new StudentDto();
        
        ArrayList<ExamStudentDto> exit = new ArrayList<>();
        exit.add(new ExamStudentDto(1L, 0.0, null, 
                studentDto, examDto));
        exit.add(new ExamStudentDto(2L, 0.0, null, 
                studentDto, examDto));
        exit.add(new ExamStudentDto(3L, 0.0, null, 
                studentDto, examDto));
        
        ResponseEntity<?> expResult = new ResponseEntity<>(exit, HttpStatus.OK);
        ResponseEntity<?> result = null;
        try {
            result = examStudentController.listAll();
        }
        catch(Exception e) {}
        
        List<ExamStudentDto> BodyfromExpResult = (List<ExamStudentDto>) expResult.getBody();
        List<ExamStudentDto> BodyfromResult = (List<ExamStudentDto>) result.getBody();
        
        assertEquals(result.getHeaders(), expResult.getHeaders());
        for (int i=0; i < BodyfromExpResult.size(); i++) {
            assertEquals(BodyfromExpResult.get(i).getId(), 
                    BodyfromResult.get(i).getId());
            assertEquals(BodyfromExpResult.get(i).getDefinitiveGrade(), 
                    BodyfromResult.get(i).getDefinitiveGrade());
        }
        
    }
*/
}
