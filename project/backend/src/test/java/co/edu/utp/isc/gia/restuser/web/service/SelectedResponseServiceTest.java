/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

import co.edu.utp.isc.gia.examsapp.data.entity.AnswerOption;
import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.ExamStudent;
import co.edu.utp.isc.gia.examsapp.data.entity.SelectedResponse;
import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
import co.edu.utp.isc.gia.examsapp.data.entity.Question;
import co.edu.utp.isc.gia.examsapp.data.entity.Student;
import co.edu.utp.isc.gia.examsapp.service.SelectedResponseService;
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
import co.edu.utp.isc.gia.examsapp.data.repository.SelectedResponseRepository;
import co.edu.utp.isc.gia.examsapp.validators.SelectedResponseValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.MultiQuestionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import co.edu.utp.isc.gia.examsapp.web.dto.SelectedResponseDto;
import co.edu.utp.isc.gia.examsapp.web.dto.StudentDto;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import java.io.IOException;
import static org.mockito.Mockito.verify;


/*
 *
 * @author CJ
 */
public class SelectedResponseServiceTest {

    public SelectedResponseServiceTest() {
    }

    private SelectedResponseRepository SelectedResponseRepository;
    private SelectedResponseService selectedResponseService;

    @Before
    public void init() {
        SelectedResponseRepository = Mockito.mock(SelectedResponseRepository.class);
        selectedResponseService = new SelectedResponseService(SelectedResponseRepository,
                new ModelMapper(), new SelectedResponseValidator());
    }

    /*
     * Test of save method, of class selectedResponseService.
     */
    
    @Test
    public void testSaveOpenResponsetNullObject() {
        try {
            SelectedResponseDto openResponse = null;
            selectedResponseService.save(openResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException("SelectedResponse object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveOpenResponseNullProperties() {
        SelectedResponseDto openResponseDto = new SelectedResponseDto(null, null, null, null);

        try {
            this.selectedResponseService.save(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException("SelectedResponse valoration is null\n"
                        + "SelectedResponse examStudent is null\n"
                        + "SelectedResponse answerOption is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }
    
    @Test
    public void testSaveOpenResponseParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        StudentDto studentDto = new StudentDto(null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(null, null, studentDto, examDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(null);
        multiQuestionDto.setDescription(null);
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage(null);
        multiQuestionDto.setType(null);
        multiQuestionDto.setWeight(null);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, multiQuestionDto);
        
        SelectedResponseDto selectedResponse = new SelectedResponseDto(null, null, examStudentDto, answerOptionDto);
        
        try {
            this.selectedResponseService.save(selectedResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException("SelectedResponse valoration is null\n"
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
                        + "AnswerOption id is null\n"
                        + "AnswerOption description is null\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is null\n"
                        + "Question id is null\n"
                        + "Question type is null\n"
                        + "Question weight is null\n"
                        + "Question description is null\n"
                        + "Question image is null\n"
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
    public void testSaveOpenResponseEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        StudentDto studentDto = new StudentDto(0L, "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "", 0.0, "", 0, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(0L, 0.0, studentDto, examDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(0L);
        multiQuestionDto.setDescription("");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("");
        multiQuestionDto.setType("");
        multiQuestionDto.setWeight(0.0);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", true, 0.0, multiQuestionDto);
        SelectedResponseDto selectedResponse = new SelectedResponseDto(0L, 0.0, examStudentDto, answerOptionDto);
        
        try {
            this.selectedResponseService.save(selectedResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException("SelectedResponse valoration is invalid\n"
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
                        + "AnswerOption id is invalid\n"
                        + "AnswerOption description is empty\n"
                        + "AnswerOption weight is invalid\n"
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
                        + "Professor's password is empty\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveOpenResponseInvalidProperties() {

        SelectedResponseDto selectedResponse = new SelectedResponseDto(0L, 0.0, null, null);

        try {
            this.selectedResponseService.save(selectedResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException("SelectedResponse valoration is invalid\n"
                        + "SelectedResponse examStudent is null\n"
                        + "SelectedResponse answerOption is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveOpenResponseNullAsResponse() {
        when(SelectedResponseRepository.save(any(SelectedResponse.class))).thenReturn(null);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        MultiQuestionDto questionDto = new MultiQuestionDto();
        
        questionDto.setId(1L);
        questionDto.setDescription("Question description");
        questionDto.setExam(examDto);
        questionDto.setQuestionImage("image.jpg");
        questionDto.setType("mm");
        questionDto.setWeight(0.1);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, questionDto);
        SelectedResponseDto selectedResponseDto = new SelectedResponseDto(1L, 0.1, examStudentDto, answerOptionDto);
        
        SelectedResponseDto response;
        SelectedResponseDto expResult = null;
        
        try {
            response = selectedResponseService.save(selectedResponseDto);
            assertThat(response, sameBeanAs(expResult));
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
        
        AnswerOption answerOption = new AnswerOption(1L, "Answer option description", true, 0.1, null, question);
        SelectedResponse selectedResponse = new SelectedResponse(1L, 0.1, examStudent, answerOption);
        when(SelectedResponseRepository.save(any(SelectedResponse.class))).thenReturn(selectedResponse);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        MultiQuestionDto questionDto = new MultiQuestionDto();
        
        questionDto.setId(1L);
        questionDto.setDescription("Question description");
        questionDto.setExam(examDto);
        questionDto.setQuestionImage("image.jpg");
        questionDto.setType("mm");
        questionDto.setWeight(0.1);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, questionDto);
        SelectedResponseDto selectedResponseDto = new SelectedResponseDto(1L, 0.1, examStudentDto, answerOptionDto);
        
        SelectedResponseDto response;
        SelectedResponseDto expResult = selectedResponseDto;
        
        try {
            response = selectedResponseService.save(selectedResponseDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    /*
        Testing update method
     */
    
    @Test
    public void testUpdateOpenResponsetNullObject() {
        try {
            SelectedResponseDto openResponse = null;
            selectedResponseService.update(openResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException("SelectedResponse object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateOpenResponseNullProperties() {
        SelectedResponseDto openResponseDto = new SelectedResponseDto(null, null, null, null);

        try {
            this.selectedResponseService.update(openResponseDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "SelectedResponse id is null\n"
                        + "SelectedResponse valoration is null\n"
                        + "SelectedResponse examStudent is null\n"
                        + "SelectedResponse answerOption is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }
    
    @Test
    public void testUpdateOpenResponseParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        StudentDto studentDto = new StudentDto(null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(null, null, studentDto, examDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(null);
        multiQuestionDto.setDescription(null);
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage(null);
        multiQuestionDto.setType(null);
        multiQuestionDto.setWeight(null);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, multiQuestionDto);
        
        SelectedResponseDto selectedResponse = new SelectedResponseDto(null, null, examStudentDto, answerOptionDto);
        
        try {
            this.selectedResponseService.update(selectedResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "SelectedResponse id is null\n"
                        + "SelectedResponse valoration is null\n"
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
                        + "AnswerOption id is null\n"
                        + "AnswerOption description is null\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is null\n"
                        + "Question id is null\n"
                        + "Question type is null\n"
                        + "Question weight is null\n"
                        + "Question description is null\n"
                        + "Question image is null\n"
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
    public void testUpdateOpenResponseEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        StudentDto studentDto = new StudentDto(0L, "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "", 0.0, "", 0, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(0L, 0.0, studentDto, examDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(0L);
        multiQuestionDto.setDescription("");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("");
        multiQuestionDto.setType("");
        multiQuestionDto.setWeight(0.0);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", true, 0.0, multiQuestionDto);
        SelectedResponseDto selectedResponse = new SelectedResponseDto(0L, 0.0, examStudentDto, answerOptionDto);
        
        try {
            this.selectedResponseService.update(selectedResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "SelectedResponse id is invalid\n"
                        + "SelectedResponse valoration is invalid\n"
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
                        + "AnswerOption id is invalid\n"
                        + "AnswerOption description is empty\n"
                        + "AnswerOption weight is invalid\n"
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
                        + "Professor's password is empty\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateOpenResponseInvalidProperties() {

        SelectedResponseDto selectedResponse = new SelectedResponseDto(0L, 0.0, null, null);

        try {
            this.selectedResponseService.update(selectedResponse);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "SelectedResponse id is invalid\n"
                        + "SelectedResponse valoration is invalid\n"
                        + "SelectedResponse examStudent is null\n"
                        + "SelectedResponse answerOption is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateOpenResponseNullAsResponse() {
        when(SelectedResponseRepository.save(any(SelectedResponse.class))).thenReturn(null);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        MultiQuestionDto questionDto = new MultiQuestionDto();
        
        questionDto.setId(1L);
        questionDto.setDescription("Question description");
        questionDto.setExam(examDto);
        questionDto.setQuestionImage("image.jpg");
        questionDto.setType("mm");
        questionDto.setWeight(0.1);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, questionDto);
        SelectedResponseDto selectedResponseDto = new SelectedResponseDto(1L, 0.1, examStudentDto, answerOptionDto);
        
        SelectedResponseDto response;
        SelectedResponseDto expResult = null;
        
        try {
            response = selectedResponseService.update(selectedResponseDto);
            assertThat(response, sameBeanAs(expResult));
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
        
        AnswerOption answerOption = new AnswerOption(1L, "Answer option description", true, 0.1, null, question);
        SelectedResponse selectedResponse = new SelectedResponse(1L, 0.1, examStudent, answerOption);
        when(SelectedResponseRepository.save(any(SelectedResponse.class))).thenReturn(selectedResponse);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        MultiQuestionDto questionDto = new MultiQuestionDto();
        
        questionDto.setId(1L);
        questionDto.setDescription("Question description");
        questionDto.setExam(examDto);
        questionDto.setQuestionImage("image.jpg");
        questionDto.setType("mm");
        questionDto.setWeight(0.1);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, questionDto);
        SelectedResponseDto selectedResponseDto = new SelectedResponseDto(1L, 0.1, examStudentDto, answerOptionDto);
        
        SelectedResponseDto response;
        SelectedResponseDto expResult = selectedResponseDto;
        
        try {
            response = selectedResponseService.update(selectedResponseDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    /*
     * Test for findOne method.
     */

    @Test
    public void testFindOneNonExistentSelectedResponse() {
        when(SelectedResponseRepository.findById(any(Long.class))).thenReturn(null);

        try {
            SelectedResponseDto response;
            SelectedResponseDto expResult = null;
            response = selectedResponseService.findOne(1L);
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
        
        AnswerOption answerOption = new AnswerOption(1L, "Answer option description", true, 0.1, null, question);
        SelectedResponse selectedResponse = new SelectedResponse(1L, 0.1, examStudent, answerOption);
                
        Optional<SelectedResponse> op = Optional.of(selectedResponse);
        when(SelectedResponseRepository.findById(any(Long.class))).thenReturn(op);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        MultiQuestionDto questionDto = new MultiQuestionDto();
        questionDto.setId(1L);
        questionDto.setDescription("Question description");
        questionDto.setExam(examDto);
        questionDto.setQuestionImage("image.jpg");
        questionDto.setType("mm");
        questionDto.setWeight(0.1);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, questionDto);
        SelectedResponseDto selectedResponseDto = new SelectedResponseDto(1L, 0.1, examStudentDto, answerOptionDto);
        
        SelectedResponseDto expResult = selectedResponseDto;
        SelectedResponseDto response;
        
        try {
            response = selectedResponseService.findOne(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }
    }

    @Test
    public void testFindByUnexistentExamStudentAndQuestion() {
        when(SelectedResponseRepository.findByExamStudentIdAndAnswerOptionId(any(Long.class), any(Long.class))).thenReturn(null);
        try {
            SelectedResponseDto response;
            SelectedResponseDto expResult = null;
            response = selectedResponseService.findByExamStudentAndAnsOpt(1L, 1L);
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
        
        AnswerOption answerOption = new AnswerOption(1L, "Answer option description", true, 0.1, null, question);
        SelectedResponse selectedResponse = new SelectedResponse(1L, 0.1, examStudent, answerOption);
        
        when(SelectedResponseRepository.findByExamStudentIdAndAnswerOptionId(any(Long.class), any(Long.class))).thenReturn(selectedResponse);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        MultiQuestionDto questionDto = new MultiQuestionDto();
        questionDto.setId(1L);
        questionDto.setDescription("Question description");
        questionDto.setExam(examDto);
        questionDto.setQuestionImage("image.jpg");
        questionDto.setType("mm");
        questionDto.setWeight(0.1);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, questionDto);
        SelectedResponseDto selectedResponseDto = new SelectedResponseDto(1L, 0.1, examStudentDto, answerOptionDto);
        SelectedResponseDto expResult = selectedResponseDto;
        
        SelectedResponseDto response;
        try {
            response = selectedResponseService.findByExamStudentAndAnsOpt(1L, 1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    /*
     * Test for delete method
     */

    @Test
    public void testDeleteExamStudent() {
        String response;
        String expResult = "SelectedResponse deleted successfully";
           
        try {
            response = selectedResponseService.delete(1L);
            assertEquals(expResult, response);
            verify(SelectedResponseRepository).deleteById(any(Long.class));
        } catch (Exception e) {
        }
    }

    /*
     * Test of listAll method, of class selectedResponseService.
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
        
        AnswerOption answerOption = new AnswerOption(1L, "Answer option description", true, 0.1, null, question);
        
        SelectedResponse selectedResponse1 = new SelectedResponse(1L, 0.1, examStudent, answerOption);
        SelectedResponse selectedResponse2 = new SelectedResponse(2L, 0.1, examStudent, answerOption);
        SelectedResponse selectedResponse3 = new SelectedResponse(3L, 0.1, examStudent, answerOption);
        
        List<SelectedResponse> selectedResponses = new ArrayList<>();
        selectedResponses.add(selectedResponse1);
        selectedResponses.add(selectedResponse2);
        selectedResponses.add(selectedResponse3);
        
        when(SelectedResponseRepository.findAll()).thenReturn(selectedResponses);
        
        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        StudentDto studentDto = new StudentDto(1L, "1088","Esteban", "Castaño");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamStudentDto examStudentDto = new ExamStudentDto(1L, 5.0, studentDto, examDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);
        
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, multiQuestionDto);
        
        SelectedResponseDto selectedResponseDto1 = new SelectedResponseDto(1L, 0.1, examStudentDto, answerOptionDto);
        SelectedResponseDto selectedResponseDto2 = new SelectedResponseDto(2L, 0.1, examStudentDto, answerOptionDto);
        SelectedResponseDto selectedResponseDto3 = new SelectedResponseDto(3L, 0.1, examStudentDto, answerOptionDto);
        
        List<SelectedResponseDto> selectedResponsesDto = new ArrayList<>();
        selectedResponsesDto.add(selectedResponseDto1);
        selectedResponsesDto.add(selectedResponseDto2);
        selectedResponsesDto.add(selectedResponseDto3);
        
        
        List<SelectedResponseDto> response;
        List<SelectedResponseDto> expResult = selectedResponsesDto;
        
        try {
            response = selectedResponseService.listAll();
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }
    }

}
