/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.examsapp.web.controller.StudentController;
import co.edu.utp.isc.gia.examsapp.data.entity.Student;
import co.edu.utp.isc.gia.examsapp.service.StudentService;
import co.edu.utp.isc.gia.examsapp.web.dto.StudentDto;
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
import co.edu.utp.isc.gia.examsapp.data.repository.StudentRepository;
import co.edu.utp.isc.gia.examsapp.validators.StudentValidator;

/**
 *
 * @author CJ
 */
public class StudentControllerTest {
    
    public StudentControllerTest() {
    }

    private StudentRepository studentRepository; 
    private StudentController studentController;
    private StudentValidator studentValidator;
/*    
    @Before
    public void init() {
        studentRepository = Mockito.mock(StudentRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        studentValidator = new StudentValidator();
        StudentService studentService = new StudentService(studentRepository, 
                modelMapper, studentValidator);
        studentController = new StudentController(studentService);
    }
    */
    /**
     * Test of save method, of class StudentController.
     */
    /*
   @Test
    public void testSaveStudentNullObject() {
                ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student object is null", 
                        HttpStatus.BAD_REQUEST);
        
        StudentDto student = null;
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }

    @Test
    public void testSaveStudentNullIdentificationCard() {
        StudentDto student = new StudentDto(null, null, "Juan carlos", 
                "Gomez",   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's identification card is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
        
    }

    @Test
    public void testSaveStudentNullName() {
        StudentDto student = new StudentDto(null, "11", null, 
                "Gomez",   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's name is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testSaveStudentNullLastname() {
        StudentDto student = new StudentDto(null, "11", "Juan Carlos", 
                null,   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's lastname is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    } 

    @Test
    public void testSaveStudentEmptyIdentificationCard() {
        StudentDto student = new StudentDto(null, "", "Juan carlos", 
                "Gomez",   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's identification card is empty", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }

    @Test
    public void testSaveStudentEmptyName() {
        StudentDto student = new StudentDto(null, "11", "" ,"Gomez", 
                null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's name is empty", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testSaveStudentEmptyLastname() {
        StudentDto student = new StudentDto(null, "11", "Juan Carlos", "" ,
                   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's lastname is empty", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testSaveStudentInvalidIdentificationCard() {
        StudentDto student = new StudentDto(null, "idcard", "Juan Carlos", 
                "Gomez", null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's identification card is not a number", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.save(student);
        }
        catch(Exception e) {
        }
        //
        assertEquals(response, expResult);
    }        
    
    @Test
    public void testSave() {
        Student resulted = new Student(null,"11","Juan carlos", "Gomez",  null);

        when(studentRepository.save(any(Student.class))).thenReturn(resulted);
        
        StudentDto student = new StudentDto(null,"11","Juan carlos", "Gomez", null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(student, HttpStatus.OK);
        try {
            response = studentController.save(student);
            
        }
        catch(Exception e) { }
        
        assertEquals(response.getHeaders(), expResult.getHeaders());

        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getIdentificationCard(), bodyFromExpResult.getIdentificationCard());
        assertEquals(bodyFromResponse.getName(), bodyFromExpResult.getName());
        assertEquals(bodyFromResponse.getLastname(), bodyFromExpResult.getLastname());
    }

*/    
/* Update method test */    
/*
   @Test
    public void testUpdateStudentNullObject() {
                ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student object is null", 
                        HttpStatus.BAD_REQUEST);
        
        StudentDto student = null;
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }

    @Test
    public void testUpdateStudentNullIdentificationCard() {
        StudentDto student = new StudentDto(1L, null, "Juan carlos", 
                "Gomez",   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's identification card is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
        
    }

    @Test
    public void testUpdateStudentNullName() {
        StudentDto student = new StudentDto(1L, "11", null, 
                "Gomez",   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's name is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testUpdateStudentNullLastname() {
        StudentDto student = new StudentDto(1L, "11", "Juan Carlos", 
                null,   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's lastname is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
        
    @Test
    public void testUpdateStudentEmptyIdentificationCard() {
        StudentDto student = new StudentDto(1L, "", "Juan carlos", "Gomez",
                null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's identification card is empty", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }

    @Test
    public void testUpdateStudentEmptyName() {
        StudentDto student = new StudentDto(1L, "11", "", "Gomez",null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's name is empty", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testUpdateStudentEmptyLastname() {
        StudentDto student = new StudentDto(1L, "11", "Juan Carlos", "",
                   null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's lastname is empty", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
    
    @Test
    public void testUpdateStudentNullId() {
        StudentDto student = new StudentDto(null, "idcard", "Juan Carlos", 
                "Gomez", null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's id is null", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }      
    
    
    @Test
    public void testUpdateStudentInvalidIdentificationCard() {
        StudentDto student = new StudentDto(1L, "idcard", "Juan Carlos", 
                "Gomez", null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = 
                new ResponseEntity<>("Student's identification card is not a number", 
                        HttpStatus.BAD_REQUEST);
        
        try {
            response = this.studentController.update(student);
        }
        catch(Exception e) {
        }
        
        assertEquals(response, expResult);
    }
*/
    /**
     * Test of update method, of class StudentController.
     */
/*
    @Test
    public void testUpdate() {
        Student resulted = new Student(1L,"11","Juan carlos", "Gomez",  null);

        when(studentRepository.save(any(Student.class))).thenReturn(resulted);
        
        StudentDto student = new StudentDto(1L,"11","Juan carlos", "Gomez", null);
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(student, HttpStatus.OK);
        try {
            response = studentController.update(student);
            
        }
        catch(Exception e) { }
        
        assertEquals(response.getHeaders(), expResult.getHeaders());

        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getIdentificationCard(), bodyFromExpResult.getIdentificationCard());
        assertEquals(bodyFromResponse.getName(), bodyFromExpResult.getName());
        assertEquals(bodyFromResponse.getLastname(), bodyFromExpResult.getLastname());
    }

    @Test
    public void testFindOneNonExistentStudent() {
        when(studentRepository.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<?> response = null;
        try {
            response = studentController.findOne(1L);
        }
        catch (Exception e) {}
        
        ResponseEntity<?> expResult = 
                new ResponseEntity<> ("Student doesn't exist", 
                        HttpStatus.NOT_FOUND);
        
        assertEquals(expResult, response);

    }
    */
    /**
     * Test of findOne method, of class StudentController.
     */
/*
    @Test
    public void testFindOne() {
        
        Student resulted = new Student(1L,"11","Juan carlos", "Gomez",  null);
        Optional<Student> op = Optional.of(resulted);
        when(studentRepository.findById(any(Long.class))).thenReturn(op);
        
        ResponseEntity<?> response = null;
        try {
            response = studentController.findOne(1L);
        }
        catch (Exception e) {}
        
        StudentDto user = new StudentDto(1L,"11","Juan carlos", "Gomez", null);
        ResponseEntity<?> expResult = new ResponseEntity<>(user, HttpStatus.OK);
        
        assertEquals(expResult.getHeaders(), response.getHeaders());
        
        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getIdentificationCard(), bodyFromExpResult.getIdentificationCard());
        assertEquals(bodyFromResponse.getName(), bodyFromExpResult.getName());
        assertEquals(bodyFromResponse.getLastname(), bodyFromExpResult.getLastname());
    }


    @Test
    public void testDeleteNonExistentStudent() {
        when(studentRepository.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<?> response = null;
        try {
            response = studentController.delete(1L);
        }
        catch (Exception e) {}
        
        ResponseEntity<?> expResult = 
                new ResponseEntity<> ("Student doesn't exist", 
                        HttpStatus.NOT_FOUND);
        
        assertEquals(expResult, response);
    }
*/
    /**
     * Test of delete method, of class StudentController.
     */
    /*
    @Test
    public void testDelete() {
        
        Student resulted = new Student(1L,"11","Juan carlos", "Gomez",  null);
        Optional<Student> op = Optional.of(resulted);
        when(studentRepository.findById(any(Long.class))).thenReturn(op);
        
        ResponseEntity<?> response = null;
        try {
            response = studentController.delete(1L);
        }
        catch (Exception e) {}
        
        StudentDto student = new StudentDto(1L,"11","Juan carlos", "Gomez", null);
        ResponseEntity<?> expResult = new ResponseEntity<>(student, HttpStatus.OK);
        
        assertEquals(expResult.getHeaders(), response.getHeaders());
        
        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        
        assertEquals(bodyFromResponse.getId(), bodyFromExpResult.getId());
        assertEquals(bodyFromResponse.getIdentificationCard(), bodyFromExpResult.getIdentificationCard());
        assertEquals(bodyFromResponse.getName(), bodyFromExpResult.getName());
        assertEquals(bodyFromResponse.getLastname(), bodyFromExpResult.getLastname());
        
    }
*/
    /**
     * Test of listAll method, of class StudentController.
     */
    /*
    @Test
    public void testListAll() {
        ArrayList<Student> resulted = new ArrayList<>();
        
        resulted.add(new Student(1L,"11","Juan carlos", "Gomez",  null));
        resulted.add(new Student(2L,"12","Carlos mario", "Lopez",  null));
        resulted.add(new Student(3L,"13","Natalia", "Castano", null));
        
        when(studentRepository.findAll()).thenReturn(resulted);
        
        ArrayList<StudentDto> exit = new ArrayList<>();
        exit.add(new StudentDto(1L,"11","Juan carlos", "Gomez", null));
        exit.add(new StudentDto(2L,"12","Carlos mario", "Lopez", null));
        exit.add(new StudentDto(3L,"13","Natalia", "Castano", null));
        
        ResponseEntity<?> expResult = new ResponseEntity<>(exit, HttpStatus.OK);
        ResponseEntity<?> result = null;
        try {
            result = studentController.listAll();
        }
        catch(Exception e) {}
        
        List<StudentDto> BodyfromExpResult = (List<StudentDto>) expResult.getBody();
        List<StudentDto> BodyfromResult = (List<StudentDto>) result.getBody();
        
        assertEquals(result.getHeaders(), expResult.getHeaders());
        for (int i=0; i < BodyfromExpResult.size(); i++) {
            assertEquals(BodyfromExpResult.get(i).getId(), 
                    BodyfromResult.get(i).getId());
            assertEquals(BodyfromExpResult.get(i).getName(), 
                    BodyfromResult.get(i).getName());
            assertEquals(BodyfromExpResult.get(i).getLastname(), 
                    BodyfromResult.get(i).getLastname());
        }
        
    }
*/
}
