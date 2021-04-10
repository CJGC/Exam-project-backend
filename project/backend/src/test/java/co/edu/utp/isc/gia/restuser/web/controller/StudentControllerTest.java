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
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.mockito.Mockito.verify;

/**
 *
 * @author CJ
 */
public class StudentControllerTest {
    
    public StudentControllerTest() {
    }

    private StudentRepository studentRepository; 
    private StudentController studentController;

    @Before
    public void init() {
        studentRepository = Mockito.mock(StudentRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        StudentService studentService = new StudentService(studentRepository, 
                modelMapper, new StudentValidator());
        studentController = new StudentController(studentService);
    }
    
    /**
     * Test of save method, of class StudentController.
     */
    
    @Test
    public void testSaveStudentNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("Student object is null\n",
                        HttpStatus.BAD_REQUEST);

        StudentDto studentDto = null;

        try {
            response = studentController.save(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveStudentNullProperties() {
        StudentDto studentDto = new StudentDto(null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Student's identification card is null\n"
                        + "Student's name is null\n"
                        + "Student's lastname is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.studentController.save(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

    }

    @Test
    public void testSaveStudentEmptyProperties() {
        StudentDto studentDto = new StudentDto(1L, "", "", "");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Student's identification card is empty\n"
                        + "Student's name is empty\n"
                        + "Student's lastname is empty\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.studentController.save(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveStudentInvalidPropertiesCard() {

        StudentDto studentDto = new StudentDto(0L, "number", "Esteban", "Castano");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Student's identification card is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.studentController.save(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }
    
    @Test
    public void testSaveStudentValidInfo() {
        Student student = new Student(1L, "10088", "Esteban", "Castaño", null);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        StudentDto inputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");
        StudentDto outputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(outputStudentDto, HttpStatus.OK);
        try {
            response = studentController.save(inputStudentDto);

        } catch (Exception e) {
        }

        assertEquals(response.getHeaders(), expResult.getHeaders());

        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }
     /*
        Testing update method
     */
    
   
    @Test
    public void testUpdateProfessorNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("Student object is null\n",
                        HttpStatus.BAD_REQUEST);

        StudentDto studentDto = null;

        try {
            response = studentController.update(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateStudentNullProperties() {
        StudentDto studentDto = new StudentDto(null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Student's id is null\n"
                        + "Student's identification card is null\n"
                        + "Student's name is null\n"
                        + "Student's lastname is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.studentController.update(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

    }

    @Test
    public void testUpdateStudentEmptyProperties() {
        StudentDto studentDto = new StudentDto(0L, "", "", "");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Student's id is invalid\n"
                        + "Student's identification card is empty\n"
                        + "Student's name is empty\n"
                        + "Student's lastname is empty\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.studentController.update(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateStudentInvalidPropertiesIdCard() {

        StudentDto studentDto = new StudentDto(0L, "number", "Esteban", "Castano");
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Student's id is invalid\n"
                        + "Student's identification card is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.studentController.update(studentDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateStudentValidInfo() {
        Student student = new Student(1L, "10088", "Esteban", "Castaño", null);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        StudentDto inputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");
        StudentDto outputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(outputStudentDto, HttpStatus.OK);

        try {
            response = studentController.update(inputStudentDto);
        } catch (Exception e) {
        }

        assertEquals(response.getHeaders(), expResult.getHeaders());

        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));

    }

    /*
     * Test for findOne method.
     */
    
    
    @Test
    public void testFindOneNonExistentStudent() {
        when(studentRepository.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<?> response = null;
        try {
            response = studentController.findOne(1L);
        } catch (Exception e) {}

        ResponseEntity<?> expResult = new ResponseEntity<>(null,HttpStatus.OK);

        assertEquals(expResult, response);

    }

    @Test
    public void testFindOne() {

        Student studentDto = new Student(1L, "10088", "Esteban", "Castano", null);
        Optional<Student> op = Optional.of(studentDto);
        when(studentRepository.findById(any(Long.class))).thenReturn(op);

        ResponseEntity<?> response = null;
        try {
            response = studentController.findOne(1L);
        } catch (Exception e) {
        }

        StudentDto professorDtoOutput = new StudentDto(1L, "10088", "Esteban", "Castano");
        ResponseEntity<?> expResult = new ResponseEntity<>(professorDtoOutput, HttpStatus.OK);

        assertEquals(expResult.getHeaders(), response.getHeaders());
        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    @Test
    public void testFindByIdentificationCardNoExistent() {
        when(studentRepository.findByIdentificationCard(any(String.class))).thenReturn(null);
        ResponseEntity<?> response = null;
        try {
            response = studentController.findByIdentificationCard("1088");
        } catch (Exception e) {}

        ResponseEntity<?> expResult = new ResponseEntity<>(null,HttpStatus.OK);

        assertEquals(expResult, response);

    }
    
    @Test
    public void testFindByIdentificationCard() {

        Student studentDto = new Student(1L, "10088", "Esteban", "Castano", null);
        when(studentRepository.findByIdentificationCard(any(String.class))).thenReturn(studentDto);

        ResponseEntity<?> response = null;
        try {
            response = studentController.findByIdentificationCard("1088");
        } catch (Exception e) {
        }

        StudentDto professorDtoOutput = new StudentDto(1L, "10088", "Esteban", "Castano");
        ResponseEntity<?> expResult = new ResponseEntity<>(professorDtoOutput, HttpStatus.OK);

        assertEquals(expResult.getHeaders(), response.getHeaders());
        StudentDto bodyFromResponse = (StudentDto) response.getBody();
        StudentDto bodyFromExpResult = (StudentDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }
    /*
     * Test for delete method
     */
    
    @Test
    public void testDeleteStudent() {
        ResponseEntity<?> response = null;
        try {
            response = studentController.delete(1L);
        } catch (Exception e) {
        }

        ResponseEntity<?> expResult
                = new ResponseEntity<>("Student deleted successfully",
                        HttpStatus.OK);

        assertEquals(expResult, response);
        verify(studentRepository).deleteById(any(Long.class));
    }

    /*
     * Test of listAll method, of class ProfessorController.
     */
    
    @Test
    public void testListAll() {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student(1L, "10088", "Esteban", "Castaño", null));
        students.add(new Student(2L, "10089", "Felipe", "Perez", null));
        students.add(new Student(3L, "10088", "Carlos", "Ramirez", null));

        when(studentRepository.findAll()).thenReturn(students);

        ArrayList<StudentDto> studentsDto = new ArrayList<>();
        studentsDto.add(new StudentDto(1L, "10088", "Esteban", "Castaño"));
        studentsDto.add(new StudentDto(2L, "10089", "Felipe", "Perez"));
        studentsDto.add(new StudentDto(3L, "10088", "Carlos", "Ramirez"));

        ResponseEntity<?> expResult = new ResponseEntity<>(studentsDto, HttpStatus.OK);
        ResponseEntity<?> result = null;

        try {
            result = studentController.listAll();
        } catch (Exception e) {
        }

        assertEquals(result.getHeaders(), expResult.getHeaders());
        List<StudentDto> bodyfromExpResult = (List<StudentDto>) expResult.getBody();
        List<StudentDto> bodyfromResult = (List<StudentDto>) result.getBody();
        assertThat(bodyfromResult, sameBeanAs(bodyfromExpResult));
    }

}
