/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.ExamStudent;
import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
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
import co.edu.utp.isc.gia.examsapp.data.repository.ExamStudentRepository;
import co.edu.utp.isc.gia.examsapp.validators.ExamStudentValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import co.edu.utp.isc.gia.examsapp.web.dto.StudentDto;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import java.io.IOException;
import static org.mockito.Mockito.verify;


/*
 *
 * @author CJ
 */
public class ExamStudentServiceTest {

    public ExamStudentServiceTest() {
    }

    private ExamStudentRepository ExamStudentRepository;
    private ExamStudentService examStudentService;

    @Before
    public void init() {
        ExamStudentRepository = Mockito.mock(ExamStudentRepository.class);
        examStudentService = new ExamStudentService(ExamStudentRepository, new ModelMapper(), new ExamStudentValidator());
    }

    /*
     * Test of save method, of class ExamStudentController.
     */
    
    @Test
    public void testSaveExamStudentNullObject() {

        ExamStudentDto examstudent = null;

        try {
            examStudentService.save(examstudent);
        } catch (Exception response) {
            IOException expectedException = new IOException("ExamStudent object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveExamStudentNullProperties() {
        ExamStudentDto examstudentDto = new ExamStudentDto(null, null, null, null);

        try {
            this.examStudentService.save(examstudentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "ExamStudent definitive grade is null\n"
                        + "ExamStudent student is null\n"
                        + "ExamStudent exam is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }


    }
    
    @Test
    public void testSaveExamStudentParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        StudentDto studentDto = new StudentDto(null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        
        ExamStudentDto examstudentDto = new ExamStudentDto(null, null, studentDto, examDto);
        try {
            
            this.examStudentService.save(examstudentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "ExamStudent definitive grade is null\n"
                        + "Student's id is null\n"
                        + "Student's identification card is null\n"
                        + "Student's name is null\n"
                        + "Student's lastname is null\n"
                        + "Exam id is null\n"
                        + "Exam name is null\n"
                        + "Exam link is null\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is null\n"
                        + "Exam durability is null\n"
                        + "Professor's id is null\n"
                        + "Professor's identification card is null\n"
                        + "Professor's name is null\n"
                        + "Professor's lastname is null\n"
                        + "Professor's email is null\n"
                        + "Professor's username is null\n"
                        + "Professor's password is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testSaveExamStudentEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        StudentDto studentDto = new StudentDto(0L, "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "",0.0, "", 0, professorDto);
        ExamStudentDto examstudentDto = new ExamStudentDto(0L, 0.0, studentDto, examDto);
        
        try {
            this.examStudentService.save(examstudentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "ExamStudent definitive grade is invalid\n"
                        + "Student's id is invalid\n"
                        + "Student's identification card is empty\n"
                        + "Student's name is empty\n"
                        + "Student's lastname is empty\n"
                        + "Exam id is invalid\n"
                        + "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Professor's id is invalid\n"
                        + "Professor's identification card is empty\n"
                        + "Professor's name is empty\n"
                        + "Professor's lastname is empty\n"
                        + "Professor's email is empty\n"
                        + "Professor's username is empty\n"
                        + "Professor's password is empty\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveExamStudentInvalidProperties() {

        ExamStudentDto examstudent = new ExamStudentDto(0L, 0.0, null, null);

        try {
            this.examStudentService.save(examstudent);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "ExamStudent definitive grade is invalid\n"
                        + "ExamStudent student is null\n"
                        + "ExamStudent exam is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testSaveAnswerOptionNullAsResponse() {

        when(ExamStudentRepository.save(any(ExamStudent.class))).thenReturn(null);

        ProfessorDto inputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto inputStudentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto inputExamDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, inputProfessorDto);
        ExamStudentDto inputExamstudentDto = new ExamStudentDto(1L, 5.0, inputStudentDto, inputExamDto);
        
        ExamStudentDto response;
        ExamDto expectedResult = null;
        try {
            response = this.examStudentService.save(inputExamstudentDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
    }

    @Test
    public void testSaveExamStudentValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        when(ExamStudentRepository.save(any(ExamStudent.class))).thenReturn(examStudent);

        // input data
        ProfessorDto inputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto inputStudentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto inputExamDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, inputProfessorDto);
        ExamStudentDto inputExamstudentDto = new ExamStudentDto(1L, 5.0, inputStudentDto, inputExamDto);
        
        // output data
        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto outputStudentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto outputExamDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, outputProfessorDto);
        ExamStudentDto outputExamStudentDto = new ExamStudentDto(1L, 5.0, outputStudentDto, outputExamDto);

        ExamStudentDto response = null;
        ExamStudentDto expResult = outputExamStudentDto;
        try {
            response = examStudentService.save(inputExamstudentDto);
            assertThat(response, sameBeanAs(expResult));

        } catch (Exception e) {}

    }
    /*
        Testing update method
     */

    @Test
    public void testUpdateExamStudentNullObject() {

        ExamStudentDto examstudent = null;

        try {
            examStudentService.update(examstudent);
        } catch (Exception response) {
            IOException expectedException = new IOException("ExamStudent object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateExamStudentNullProperties() {
        ExamStudentDto examstudentDto = new ExamStudentDto(null, null, null, null);

        try {
            this.examStudentService.update(examstudentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "ExamStudent id is null\n"
                        + "ExamStudent definitive grade is null\n"
                        + "ExamStudent student is null\n"
                        + "ExamStudent exam is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }
    
    @Test
    public void testUpdateExamStudentParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        StudentDto studentDto = new StudentDto(null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        
        ExamStudentDto examstudentDto = new ExamStudentDto(null, null, studentDto, examDto);
        try {
            
            this.examStudentService.update(examstudentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                         "ExamStudent id is null\n"
                        + "ExamStudent definitive grade is null\n"
                        + "Student's id is null\n"
                        + "Student's identification card is null\n"
                        + "Student's name is null\n"
                        + "Student's lastname is null\n"
                        + "Exam id is null\n"
                        + "Exam name is null\n"
                        + "Exam link is null\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is null\n"
                        + "Exam durability is null\n"
                        + "Professor's id is null\n"
                        + "Professor's identification card is null\n"
                        + "Professor's name is null\n"
                        + "Professor's lastname is null\n"
                        + "Professor's email is null\n"
                        + "Professor's username is null\n"
                        + "Professor's password is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testUpdateExamStudentEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        StudentDto studentDto = new StudentDto(0L, "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "",0.0, "", 0, professorDto);
        ExamStudentDto examstudentDto = new ExamStudentDto(0L, 0.0, studentDto, examDto);
        
        try {
            this.examStudentService.update(examstudentDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                          "ExamStudent id is invalid\n"
                        + "ExamStudent definitive grade is invalid\n"
                        + "Student's id is invalid\n"
                        + "Student's identification card is empty\n"
                        + "Student's name is empty\n"
                        + "Student's lastname is empty\n"
                        + "Exam id is invalid\n"
                        + "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Professor's id is invalid\n"
                        + "Professor's identification card is empty\n"
                        + "Professor's name is empty\n"
                        + "Professor's lastname is empty\n"
                        + "Professor's email is empty\n"
                        + "Professor's username is empty\n"
                        + "Professor's password is empty\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateExamStudentInvalidProperties() {

        ExamStudentDto examstudent = new ExamStudentDto(0L, 0.0, null, null);

        try {
            this.examStudentService.update(examstudent);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                          "ExamStudent id is invalid\n"
                        + "ExamStudent definitive grade is invalid\n"
                        + "ExamStudent student is null\n"
                        + "ExamStudent exam is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testUpdateAnswerOptionNullAsResponse() {

        when(ExamStudentRepository.save(any(ExamStudent.class))).thenReturn(null);
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez",
                "juant@me.co", "Janco27", "password");

        ProfessorDto inputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto inputStudentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto inputExamDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, inputProfessorDto);
        ExamStudentDto inputExamstudentDto = new ExamStudentDto(1L, 5.0, inputStudentDto, inputExamDto);
        
        ExamStudentDto response;
        ExamDto expectedResult = null;
        try {
            response = this.examStudentService.update(inputExamstudentDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
    }

    @Test
    public void testUpdateExamStudentValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        when(ExamStudentRepository.save(any(ExamStudent.class))).thenReturn(examStudent);

        // input data
        ProfessorDto inputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto inputStudentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto inputExamDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, inputProfessorDto);
        ExamStudentDto inputExamstudentDto = new ExamStudentDto(1L, 5.0, inputStudentDto, inputExamDto);
        
        // output data
        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto outputStudentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto outputExamDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, outputProfessorDto);
        ExamStudentDto outputExamStudentDto = new ExamStudentDto(1L, 5.0, outputStudentDto, outputExamDto);

        ExamStudentDto response = null;
        ExamStudentDto expResult = outputExamStudentDto;
        try {
            response = examStudentService.update(inputExamstudentDto);
            assertThat(response, sameBeanAs(expResult));

        } catch (Exception e) {}

    }
    
    /*
     * Test for findOne method.
     */
   
    @Test
    public void testFindOneNonExistentExamStudent() {
        when(ExamStudentRepository.findById(any(Long.class))).thenReturn(null);
        ExamStudentDto response;
        ExamStudentDto expResult = null;
        try {
            response = examStudentService.findOne(1L);
            assertEquals(expResult, response);
        } catch (Exception e) {
        }

    }

    @Test
    public void testFindOne() {
        // beans
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        Optional<ExamStudent> op = Optional.of(examStudent);
        when(ExamStudentRepository.findById(any(Long.class))).thenReturn(op);
        
        // dto
        
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examstudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        ExamStudentDto response = null;
        ExamStudentDto expResult = examstudentDto;
        
        try {
            response = examStudentService.findOne(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }
    }

    @Test
    public void testFindByExamNonExistentExamStudent() {
        when(ExamStudentRepository.findByExamId(any(Long.class))).thenReturn(null);
        List<ExamStudentDto> response;
        List<ExamStudentDto> expResult= null;
                
        try {
            response = examStudentService.findByExam(1L);
            assertEquals(expResult, response);
        } catch (Exception e) {
        }
    }

    @Test
    public void testFindByExam() {
        // beans
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student1 = new Student(1L, "1088","Esteban", "Castaño", null);
        Student student2 = new Student(2L, "1089", "Carlos", "Villegas", null);
        Student student3 = new Student(3L, "1090", "Sebastian", "Castrera", null);
        
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent1 = new ExamStudent(1L, 5.0, null, student1, exam);
        ExamStudent examStudent2 = new ExamStudent(2L, 4.0, null, student2, exam);
        ExamStudent examStudent3 = new ExamStudent(3L, 4.5, null, student3, exam);

        List<ExamStudent> examStudents = new ArrayList<>();
        examStudents.add(examStudent1);
        examStudents.add(examStudent2);
        examStudents.add(examStudent3);
        
        when(ExamStudentRepository.findByExamId(any(Long.class))).thenReturn(examStudents);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto student1Dto = new StudentDto(1L, "1088","Esteban", "Castaño");
        StudentDto student2Dto = new StudentDto(2L, "1089", "Carlos", "Villegas");
        StudentDto student3Dto = new StudentDto(3L, "1090", "Sebastian", "Castrera");
        
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudent1Dto = new ExamStudentDto(1L, 5.0, student1Dto, examDto);
        ExamStudentDto examStudent2Dto = new ExamStudentDto(2L, 4.0, student2Dto, examDto);
        ExamStudentDto examStudent3Dto = new ExamStudentDto(3L, 4.5, student3Dto, examDto);

        List<ExamStudentDto> examStudentsDto = new ArrayList<>();
        examStudentsDto.add(examStudent1Dto);
        examStudentsDto.add(examStudent2Dto);
        examStudentsDto.add(examStudent3Dto);
        
        List<ExamStudentDto> response;
        List<ExamStudentDto> expResult = examStudentsDto;
        
        try {
            response = examStudentService.findByExam(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }
    
    @Test
    public void testfindByStudentNonExistentExamStudent() {
        when(ExamStudentRepository.findByStudentId(any(Long.class))).thenReturn(null);
        ExamStudentDto response;
        ExamStudentDto expResult = null;
        
        try {
            response = examStudentService.findByStudent(1L);
            assertEquals(expResult, response);
        } catch (Exception e) {}
    }

    @Test
    public void testFindByfindByStudent() {
        // beans
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        
        when(ExamStudentRepository.findByStudentId(any(Long.class))).thenReturn(examStudent);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        ExamStudentDto response;
        ExamStudentDto expResult = examStudentDto;
        try {
            response = examStudentService.findByStudent(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }
    }
    
    /*
     * Test for delete method
     */
    
    @Test
    public void testDeleteExamStudent() {
        
        try {
            String response;
            String expResult = "ExamStudent deleted successfully";
            response = examStudentService.delete(1L);
            assertEquals(expResult, response);
            verify(ExamStudentRepository).deleteById(any(Long.class));
        } catch (Exception e) {}
        
    }
    
    /*
     * Test of listAll method, of class ExamStudentController.
     */
    
    @Test
    public void testListAll() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student1 = new Student(1L, "1088","Esteban", "Castaño", null);
        Student student2 = new Student(2L, "1089", "Carlos", "Villegas", null);
        Student student3 = new Student(3L, "1090", "Sebastian", "Castrera", null);
        
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent1 = new ExamStudent(1L, 5.0, null, student1, exam);
        ExamStudent examStudent2 = new ExamStudent(2L, 4.0, null, student2, exam);
        ExamStudent examStudent3 = new ExamStudent(3L, 4.5, null, student3, exam);

        List<ExamStudent> examStudents = new ArrayList<>();
        examStudents.add(examStudent1);
        examStudents.add(examStudent2);
        examStudents.add(examStudent3);
        
        when(ExamStudentRepository.findAll()).thenReturn(examStudents);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto student1Dto = new StudentDto(1L, "1088","Esteban", "Castaño");
        StudentDto student2Dto = new StudentDto(2L, "1089", "Carlos", "Villegas");
        StudentDto student3Dto = new StudentDto(3L, "1090", "Sebastian", "Castrera");
        
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudent1Dto = new ExamStudentDto(1L, 5.0, student1Dto, examDto);
        ExamStudentDto examStudent2Dto = new ExamStudentDto(2L, 4.0, student2Dto, examDto);
        ExamStudentDto examStudent3Dto = new ExamStudentDto(3L, 4.5, student3Dto, examDto);

        List<ExamStudentDto> examStudentsDto = new ArrayList<>();
        examStudentsDto.add(examStudent1Dto);
        examStudentsDto.add(examStudent2Dto);
        examStudentsDto.add(examStudent3Dto);
        
        List<ExamStudentDto> expResult = examStudentsDto;
        List<ExamStudentDto> response;
        try {
            response = examStudentService.listAll();
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }
    
}
