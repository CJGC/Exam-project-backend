/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.ExamStudent;
import co.edu.utp.isc.gia.examsapp.data.entity.OpenResponse;
import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
import co.edu.utp.isc.gia.examsapp.data.entity.Question;
import co.edu.utp.isc.gia.examsapp.data.entity.Student;
import co.edu.utp.isc.gia.examsapp.service.OpenResponseService;
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
import co.edu.utp.isc.gia.examsapp.data.repository.OpenResponseRepository;
import co.edu.utp.isc.gia.examsapp.validators.OpenResponseValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenQuestionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenResponseDto;
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
public class OpenResponseServiceTest {

    public OpenResponseServiceTest() {
    }

    private OpenResponseRepository OpenResponseRepository;
    private OpenResponseService openResponseService;

    @Before
    public void init() {
        OpenResponseRepository = Mockito.mock(OpenResponseRepository.class);
        openResponseService = new OpenResponseService(OpenResponseRepository, new ModelMapper(), new OpenResponseValidator());
    }

    /*
     * Test of save method, of class OpenResponseService.
     */
    
    @Test
    public void testSaveOpenResponsetNullObject() {

        OpenResponseDto openResponse = null;
        try {
            openResponseService.save(openResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException("OpenResponse object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveOpenResponseNullProperties() {
        OpenResponseDto openResponseDto = new OpenResponseDto(null, null, null, null, null);
        try {
            this.openResponseService.save(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                          "OpenResponse content is null\n"
                        + "OpenResponse valoration is null\n"
                        + "OpenResponse examStudent is null\n"
                        + "OpenResponse question is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testSaveOpenResponseParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        StudentDto studentDto = new StudentDto(null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(null, null, studentDto, examDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(null);
        openQuestionDto.setDescription(null);
        openQuestionDto.setExam(null);
        openQuestionDto.setQuestionImage(null);
        openQuestionDto.setType(null);
        openQuestionDto.setWeight(null);
        
        OpenResponseDto openResponseDto = new OpenResponseDto(null, null, null,examStudentDto, openQuestionDto);
        
        try {
            this.openResponseService.save(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "OpenResponse content is null\n"
                        + "OpenResponse valoration is null\n"
                        + "ExamStudent id is null\n"
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
                        + "Professor's password is null\n"
                        + "Question id is null\n"
                        + "Question type is null\n"
                        + "Question weight is null\n"
                        + "Question description is null\n"
                        + "Question image is null\n"
                        + "Question exam is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testSaveOpenResponseEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        StudentDto studentDto = new StudentDto(0L, "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "",0.0, "", 0, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(0L, 0.0, studentDto, examDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(0L);
        openQuestionDto.setDescription("");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("");
        openQuestionDto.setType("");
        openQuestionDto.setWeight(0.0);
        
        OpenResponseDto openResponseDto = new OpenResponseDto(0L, "", 0.0,examStudentDto, openQuestionDto);
        
        try {
            this.openResponseService.save(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "OpenResponse content is empty\n"
                        + "OpenResponse valoration is invalid\n"
                        + "ExamStudent id is invalid\n"
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
                        + "Professor's password is empty\n"
                        + "Question id is invalid\n"
                        + "Question type is empty\n"
                        + "Question weight is invalid\n"
                        + "Question description is empty\n"
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
                        + "Professor's password is empty\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveOpenResponseInvalidProperties() {

        OpenResponseDto openResponseDto = new OpenResponseDto(0L, "", 0.0, null, null);

        try {
            this.openResponseService.save(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "OpenResponse content is empty\n"
                        + "OpenResponse valoration is invalid\n"
                        + "OpenResponse examStudent is null\n"
                        + "OpenResponse question is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testSaveOpenResponseNullAsResponse() {

        when(OpenResponseRepository.save(any(OpenResponse.class))).thenReturn(null);
        
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        OpenQuestionDto multiQuestionDto = new OpenQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);

        OpenResponseDto openResponseDto = new OpenResponseDto(1L, "Open response content", 0.1, examStudentDto, multiQuestionDto);
        
        OpenResponseDto response;
        OpenResponseDto expectedResult = null;
        try {
            response = this.openResponseService.save(openResponseDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
    }

    @Test
    public void testSaveOpenResponseValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        Question question = new Question();
        
        question.setId(1L);
        question.setDescription("Question description");
        question.setExam(exam);
        question.setQuestionImage("image.jpg");
        question.setType("mm");
        question.setWeight(0.1);
        
        OpenResponse openResponse = new OpenResponse(1L, "Open response content", 0.1, examStudent, question);
        when(OpenResponseRepository.save(any(OpenResponse.class))).thenReturn(openResponse);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        OpenQuestionDto multiQuestionDto = new OpenQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);

        OpenResponseDto openResponseDto = new OpenResponseDto(1L, "Open response content", 0.1, examStudentDto, multiQuestionDto);
        
        OpenResponseDto response = null;
        OpenResponseDto expResult = openResponseDto;
        try {
            response = openResponseService.save(openResponseDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }
    /*
        Testing update method
     */
 
    @Test
    public void testUpdateOpenResponsetNullObject() {

        OpenResponseDto openResponse = null;
        try {
            openResponseService.save(openResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException("OpenResponse object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateOpenResponseNullProperties() {
        OpenResponseDto openResponseDto = new OpenResponseDto(null, null, null, null, null);
        try {
            this.openResponseService.update(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "OpenResponse id is null\n"    
                        + "OpenResponse content is null\n"
                        + "OpenResponse valoration is null\n"
                        + "OpenResponse examStudent is null\n"
                        + "OpenResponse question is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testUpdateOpenResponseParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        StudentDto studentDto = new StudentDto(null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(null, null, studentDto, examDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(null);
        openQuestionDto.setDescription(null);
        openQuestionDto.setExam(null);
        openQuestionDto.setQuestionImage(null);
        openQuestionDto.setType(null);
        openQuestionDto.setWeight(null);
        
        OpenResponseDto openResponseDto = new OpenResponseDto(null, null, null,examStudentDto, openQuestionDto);
        
        try {
            this.openResponseService.update(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "OpenResponse id is null\n"
                        + "OpenResponse content is null\n"
                        + "OpenResponse valoration is null\n"
                        + "ExamStudent id is null\n"
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
                        + "Professor's password is null\n"
                        + "Question id is null\n"
                        + "Question type is null\n"
                        + "Question weight is null\n"
                        + "Question description is null\n"
                        + "Question image is null\n"
                        + "Question exam is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testUpdateOpenResponseEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        StudentDto studentDto = new StudentDto(0L, "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "",0.0, "", 0, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(0L, 0.0, studentDto, examDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(0L);
        openQuestionDto.setDescription("");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("");
        openQuestionDto.setType("");
        openQuestionDto.setWeight(0.0);
        
        OpenResponseDto openResponseDto = new OpenResponseDto(0L, "", 0.0,examStudentDto, openQuestionDto);
        
        try {
            this.openResponseService.update(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "OpenResponse id is invalid\n"
                        + "OpenResponse content is empty\n"
                        + "OpenResponse valoration is invalid\n"
                        + "ExamStudent id is invalid\n"
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
                        + "Professor's password is empty\n"
                        + "Question id is invalid\n"
                        + "Question type is empty\n"
                        + "Question weight is invalid\n"
                        + "Question description is empty\n"
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
                        + "Professor's password is empty\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateOpenResponseInvalidProperties() {

        OpenResponseDto openResponseDto = new OpenResponseDto(0L, "", 0.0, null, null);

        try {
            this.openResponseService.update(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "OpenResponse id is invalid\n"
                        + "OpenResponse content is empty\n"
                        + "OpenResponse valoration is invalid\n"
                        + "OpenResponse examStudent is null\n"
                        + "OpenResponse question is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testUpdateOpenResponseNullAsResponse() {

        when(OpenResponseRepository.save(any(OpenResponse.class))).thenReturn(null);
        
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        OpenQuestionDto multiQuestionDto = new OpenQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);

        OpenResponseDto openResponseDto = new OpenResponseDto(1L, "Open response content", 0.1, examStudentDto, multiQuestionDto);
        
        OpenResponseDto response;
        OpenResponseDto expectedResult = null;
        try {
            response = this.openResponseService.update(openResponseDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
    }

    @Test
    public void testUpdateOpenResponseValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        Question question = new Question();
        
        question.setId(1L);
        question.setDescription("Question description");
        question.setExam(exam);
        question.setQuestionImage("image.jpg");
        question.setType("mm");
        question.setWeight(0.1);
        
        OpenResponse openResponse = new OpenResponse(1L, "Open response content", 0.1, examStudent, question);
        when(OpenResponseRepository.save(any(OpenResponse.class))).thenReturn(openResponse);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        OpenQuestionDto multiQuestionDto = new OpenQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);

        OpenResponseDto openResponseDto = new OpenResponseDto(1L, "Open response content", 0.1, examStudentDto, multiQuestionDto);
        
        OpenResponseDto response = null;
        OpenResponseDto expResult = openResponseDto;
        try {
            response = openResponseService.update(openResponseDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }

    /*
     * Test for findOne method.
     */

    @Test
    public void testFindOneNonExistentExamStudent() {
        when(OpenResponseRepository.findById(any(Long.class))).thenReturn(null);
        try {
            OpenResponseDto response;
            response = openResponseService.findOne(1L);
            OpenResponse expResult = null;
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
        Question question = new Question();
        
        question.setId(1L);
        question.setDescription("Question description");
        question.setExam(exam);
        question.setQuestionImage("image.jpg");
        question.setType("mm");
        question.setWeight(0.1);
        OpenResponse openResponse = new OpenResponse(1L, "Open response content", 0.1, examStudent, question);
                
        Optional<OpenResponse> op = Optional.of(openResponse);
        when(OpenResponseRepository.findById(any(Long.class))).thenReturn(op);
        
        // dto
        
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        OpenQuestionDto multiQuestionDto = new OpenQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);

        OpenResponseDto openResponseDto = new OpenResponseDto(1L, "Open response content", 0.1, examStudentDto, multiQuestionDto);
        OpenResponseDto expResult = openResponseDto;
        
        OpenResponseDto response = null;
        try {
            response = openResponseService.findOne(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }

    @Test
    public void testFindByUnexistentExamStudentAndQuestion() {
        when(OpenResponseRepository.findByExamStudentIdAndQuestionId(any(Long.class), any(Long.class))).thenReturn(null);
        
        try {
            OpenResponseDto response;
            OpenResponseDto expResult = null;
            response = openResponseService.findByExamStudentAndQuestion(1L, 1L);
            assertEquals(expResult, response);
        } catch (Exception e) {}

    }

    @Test
    public void testFindByExamStudentAndQuestion() {
        // beans
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        Question question = new Question();
        
        question.setId(1L);
        question.setDescription("Question description");
        question.setExam(exam);
        question.setQuestionImage("image.jpg");
        question.setType("mm");
        question.setWeight(0.1);
        
        OpenResponse openResponse = new OpenResponse(1L, "Open response content", 0.1, examStudent, question);
        
        when(OpenResponseRepository.findByExamStudentIdAndQuestionId(any(Long.class), any(Long.class))).thenReturn(openResponse);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        
        OpenQuestionDto multiQuestionDto = new OpenQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);

        OpenResponseDto openResponseDto = new OpenResponseDto(1L, "Open response content", 0.1, examStudentDto, multiQuestionDto);
        OpenResponseDto expResult = openResponseDto;
        OpenResponseDto response;
        
        try {
            response = openResponseService.findByExamStudentAndQuestion(1L, 1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }

    /*
     * Test for delete method
     */

    @Test
    public void testDeleteExamStudent() {
       
        try {
            String response;
            String expResult = "OpenResponse deleted successfully";
            response = openResponseService.delete(1L);
            assertEquals(expResult, response);
            verify(OpenResponseRepository).deleteById(any(Long.class));
        } catch (Exception e) {
        }
    }

    /*
     * Test of listAll method, of class OpenResponseService.
     */

    @Test
    public void testListAll() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Student student = new Student(1L, "1088","Esteban", "Castaño", null);
        Exam exam = new Exam(1L, "Exam name", "URL",0.1, "Exam description", 3600, professor, null, null);
        ExamStudent examStudent = new ExamStudent(1L, 5.0, null, student, exam);
        Question question = new Question();
        
        question.setId(1L);
        question.setDescription("Question description");
        question.setExam(exam);
        question.setQuestionImage("image.jpg");
        question.setType("mm");
        question.setWeight(0.1);
        
        OpenResponse openResponse1 = new OpenResponse(1L, "Open response content", 0.1, examStudent, question);
        OpenResponse openResponse2 = new OpenResponse(2L, "Open response content", 0.1, examStudent, question);
        OpenResponse openResponse3 = new OpenResponse(3L, "Open response content", 0.1, examStudent, question);
        
        List<OpenResponse> openResponses = new ArrayList<>();
        openResponses.add(openResponse1);
        openResponses.add(openResponse2);
        openResponses.add(openResponse3);
        
        when(OpenResponseRepository.findAll()).thenReturn(openResponses);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        
        openQuestionDto.setId(1L);
        openQuestionDto.setDescription("Question description");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("image.jpg");
        openQuestionDto.setType("mm");
        openQuestionDto.setWeight(0.1);
        
        OpenResponseDto openResponseDto1 = new OpenResponseDto(1L, "Open response content", 0.1, examStudentDto, openQuestionDto);
        OpenResponseDto openResponseDto2 = new OpenResponseDto(2L, "Open response content", 0.1, examStudentDto, openQuestionDto);
        OpenResponseDto openResponseDto3 = new OpenResponseDto(3L, "Open response content", 0.1, examStudentDto, openQuestionDto);

        List<OpenResponseDto> openResponsesDto = new ArrayList<>();
        openResponsesDto.add(openResponseDto1);
        openResponsesDto.add(openResponseDto2);
        openResponsesDto.add(openResponseDto3);
        
        
        List<OpenResponseDto> response;
        try {
            response = openResponseService.listAll();
            assertThat(response, sameBeanAs(openResponsesDto));
        } catch (Exception e) {}
    }

}
