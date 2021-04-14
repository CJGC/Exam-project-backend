/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

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
import co.edu.utp.isc.gia.examsapp.data.repository.StudentRepository;
import co.edu.utp.isc.gia.examsapp.validators.StudentValidator;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import java.io.IOException;
import static org.mockito.Mockito.verify;

/**
 *
 * @author CJ
 */
public class StudentServiceTest {

    public StudentServiceTest() {
    }

    private StudentRepository studentRepository;
    private StudentService studentService;

    @Before
    public void init() {
        studentRepository = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(studentRepository, new ModelMapper(), new StudentValidator());
    }

    /**
     * Test of save method, of class StudentService.
     */
    @Test
    public void testSaveStudentNullObject() {

        StudentDto studentDto = null;
        try {
            studentService.save(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException("Student object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveStudentNullProperties() {
        StudentDto studentDto = new StudentDto(null, null, null, null);

        try {
            this.studentService.save(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                    "Student's identification card is null\n"
                    + "Student's name is null\n"
                    + "Student's lastname is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveStudentEmptyProperties() {
        StudentDto studentDto = new StudentDto(1L, "", "", "");

        try {
            this.studentService.save(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                    "Student's identification card is empty\n"
                    + "Student's name is empty\n"
                    + "Student's lastname is empty\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveStudentInvalidPropertiesCard() {

        StudentDto studentDto = new StudentDto(0L, "number", "Esteban", "Castano");

        try {
            this.studentService.save(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException("Student's identification card is invalid\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveStudentNullAsResponse() {
        when(studentRepository.save(any(Student.class))).thenReturn(null);
        StudentDto inputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");

        StudentDto response;
        StudentDto expResult = null;
        try {
            response = studentService.save(inputStudentDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }

    @Test
    public void testSaveStudentValidInfo() {
        Student student = new Student(1L, "10088", "Esteban", "Castaño", null);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        StudentDto inputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");
        StudentDto outputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");

        StudentDto response;
        StudentDto expResult = outputStudentDto;
        try {
            response = studentService.save(inputStudentDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }

    /*
        Testing update method
     */
    @Test
    public void testUpdateStudentNullObject() {

        StudentDto studentDto = null;
        try {
            studentService.update(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException("Student object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateStudentNullProperties() {
        StudentDto studentDto = new StudentDto(null, null, null, null);

        try {
            this.studentService.update(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                    "Student's id is null\n"
                    + "Student's identification card is null\n"
                    + "Student's name is null\n"
                    + "Student's lastname is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateStudentEmptyProperties() {
        StudentDto studentDto = new StudentDto(0L, "", "", "");

        try {
            this.studentService.update(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                    "Student's id is invalid\n"
                    + "Student's identification card is empty\n"
                    + "Student's name is empty\n"
                    + "Student's lastname is empty\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateStudentInvalidPropertiesCard() {

        StudentDto studentDto = new StudentDto(0L, "number", "Esteban", "Castano");

        try {
            this.studentService.update(studentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                    "Student's id is invalid\n"
                    + "Student's identification card is invalid\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateStudentNullAsResponse() {
        when(studentRepository.save(any(Student.class))).thenReturn(null);
        StudentDto inputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");

        StudentDto response;
        StudentDto expResult = null;
        try {
            response = studentService.update(inputStudentDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }

    @Test
    public void testUpdateStudentValidInfo() {
        Student student = new Student(1L, "10088", "Esteban", "Castaño", null);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        StudentDto inputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");
        StudentDto outputStudentDto = new StudentDto(1L, "10088", "Esteban", "Castaño");

        StudentDto response;
        StudentDto expResult = outputStudentDto;
        try {
            response = studentService.update(inputStudentDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }

    /*
     * Test for findOne method.
     */
    @Test
    public void testFindOneNonExistentStudent() {
        when(studentRepository.findById(any(Long.class))).thenReturn(null);

        try {
            StudentDto response;
            StudentDto expResult = null;
            response = studentService.findOne(1L);
            assertEquals(expResult, response);
        } catch (Exception e) {}
    }

    @Test
    public void testFindOne() {

        Student studentDto = new Student(1L, "10088", "Esteban", "Castano", null);
        Optional<Student> op = Optional.of(studentDto);
        when(studentRepository.findById(any(Long.class))).thenReturn(op);
        try {
            StudentDto response;
            StudentDto professorDtoOutput = new StudentDto(1L, "10088", "Esteban", "Castano");
            StudentDto expResult = professorDtoOutput;
            response = studentService.findOne(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }


    }

    @Test
    public void testFindByIdentificationCardNoExistent() {
        when(studentRepository.findByIdentificationCard(any(String.class))).thenReturn(null);
        StudentDto response;
        StudentDto expResult = null;
        
        try {
            response = studentService.findByIdentificationCard("1088");
            assertEquals(expResult, response);
        } catch (Exception e) {}
    }

    @Test
    public void testFindByIdentificationCard() {

        Student studentDto = new Student(1L, "10088", "Esteban", "Castano", null);
        when(studentRepository.findByIdentificationCard(any(String.class))).thenReturn(studentDto);
        try {
            StudentDto response;
            StudentDto professorDtoOutput = new StudentDto(1L, "10088", "Esteban", "Castano");
            StudentDto expResult = professorDtoOutput;
            response = studentService.findByIdentificationCard("1088");
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }
    }

    /*
     * Test for delete method
     */

    @Test
    public void testDeleteStudent() {
        try {
            String response;
            String expResult = "Student deleted successfully";
            response = studentService.delete(1L);
            assertEquals(expResult, response);
            verify(studentRepository).deleteById(any(Long.class));
        } catch (Exception e) {}
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

        List<StudentDto> expResult = studentsDto;
        List<StudentDto> result;

        try {
            result = studentService.listAll();
            assertThat(result, sameBeanAs(expResult));
        } catch (Exception e) {            
        }
    }
    
}
