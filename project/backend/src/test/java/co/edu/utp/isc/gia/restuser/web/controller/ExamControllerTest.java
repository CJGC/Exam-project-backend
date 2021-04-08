/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
import co.edu.utp.isc.gia.examsapp.data.repository.ExamRepository;
import co.edu.utp.isc.gia.examsapp.service.ExamService;
import co.edu.utp.isc.gia.examsapp.validators.ExamValidator;
import co.edu.utp.isc.gia.examsapp.web.controller.ExamController;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Christhian Gomez
 */
public class ExamControllerTest {

    private ExamRepository examRepository;
    private ExamController ExamController;
    private ExamValidator examValidator;

    @Before
    public void init() {
        examRepository = Mockito.mock(ExamRepository.class);
        examValidator = new ExamValidator();
        ExamService examService = new ExamService(examRepository, new ModelMapper(), examValidator);
        ExamController = new ExamController(examService);
    }

    /*
     * Test of save method, of class ExamController.
     */
    @Test
    public void testSaveExamNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("Exam object is null\n",
                        HttpStatus.BAD_REQUEST);

        ExamDto exam = null;

        try {
            response = ExamController.save(exam);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveExamNullProperties() {
        ExamDto examDto = new ExamDto(null, null, null,
                null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Exam name is null\n"
                        + "Exam link is null\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is null\n"
                        + "Exam durability is null\n"
                        + "Exam professor is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.ExamController.save(examDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveExamEmptyProperties() {
        ExamDto examDto = new ExamDto(null, "", "",
                null, "", null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is empty\n"
                        + "Exam durability is null\n"
                        + "Exam professor is null\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.ExamController.save(examDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveExamInvalidPropertiesMaxGradeDurability() {

        ExamDto examDto = new ExamDto(0L, "", "",
                -1.0, "", -1, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Exam professor is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.ExamController.save(examDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveExamInvalidPropertiesWithProfessor() {

        ProfessorDto professorDto = new ProfessorDto(null, "01number", "Juan",
                "Gomez", "invalid@Emailco", "Janco", "123as");

        ExamDto examDto = new ExamDto(0L, "", "",
                -1.0, "", -1, professorDto);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Professor's id is null\n"
                        + "Professor's identification card is invalid\n"
                        + "Professor's password is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.ExamController.save(examDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveExamValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez",
                "juant@me.co", "Janco27", "password", null);

        Exam exam = new Exam(1L, "Exam name", "URL",
                0.1, "Exam description", 3600, professor, null, null);
        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez",
                "juant@me.co", "Janco27", "password");

        ExamDto examDto = new ExamDto(null, "Exam name", "URL",
                0.1, "Exam description", 3600, professorDto);

        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez",
                "juant@me.co", "Janco27", "password");

        ExamDto outputExamDto = new ExamDto(1L, "Exam name", "URL",
                0.1, "Exam description", 3600, outputProfessorDto);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(outputExamDto, HttpStatus.OK);
        try {
            response = ExamController.save(examDto);

        } catch (Exception e) {
        }

        assertEquals(response.getHeaders(), expResult.getHeaders());
        ExamDto bodyFromResponse = (ExamDto) response.getBody();
        ExamDto bodyFromExpResult = (ExamDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));

    }

    /*
        Testing update method
     */
    @Test
    public void testUpdateProfessorNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("Exam object is null\n",
                        HttpStatus.BAD_REQUEST);

        ExamDto exam = null;

        try {
            response = ExamController.update(exam);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateProfessorNullProperties() {
        ExamDto examDto = new ExamDto(null, null, null,
                null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Exam id is null\n"
                        + "Exam name is null\n"
                        + "Exam link is null\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is null\n"
                        + "Exam durability is null\n"
                        + "Exam professor is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.ExamController.update(examDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

    }

    @Test
    public void testUpdateExamEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "",
                "", "", "");

        ExamDto examDto = new ExamDto(0L, "", "",
                null, "", null, professorDto);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Exam id is invalid\n"
                        + "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is empty\n"
                        + "Exam durability is null\n"
                        + "Professor's id is invalid\n"
                        + "Professor's identification card is empty\n"
                        + "Professor's name is empty\n"
                        + "Professor's lastname is empty\n"
                        + "Professor's email is empty\n"
                        + "Professor's username is empty\n"
                        + "Professor's password is empty\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.ExamController.update(examDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

    }

    @Test
    public void testUpdateExamAndProfessorInvalidProperties() {

        ProfessorDto professorDto = new ProfessorDto(0L, "card", "Juan carlos", "Gomez",
                "juantme.co", "Janco27", "123");

        ExamDto examDto = new ExamDto(0L, "Exam name", "URL",
                -0.1, "Exam description", -1, professorDto);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Exam id is invalid\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam durability is invalid\n"
                        + "Professor's id is invalid\n"
                        + "Professor's identification card is invalid\n"
                        + "Professor's email is invalid\n"
                        + "Professor's password is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.ExamController.update(examDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateExamValidInfo() {
        Professor updatedProfessor = new Professor(1L, "11", "Juan(edited)", "Gomez(edited)", "juan@domain.co", "Janco27", "password", null);
        Exam updatedExam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, updatedProfessor, null, null);

        when(examRepository.save(any(Exam.class))).thenReturn(updatedExam);

        ProfessorDto inputProfessorDto = new ProfessorDto(1L, "11", "Juan(edited)", "Gomez(edited)", "juan@domain.co", "Janco27", "password");
        ExamDto inputExamDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, inputProfessorDto);

        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan(edited)", "Gomez(edited)", "juan@domain.co", "Janco27", "password");
        ExamDto outputExamDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, outputProfessorDto);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(outputExamDto, HttpStatus.OK);

        try {
            response = ExamController.update(inputExamDto);
        } catch (Exception e) {
        }

        assertEquals(response.getHeaders(), expResult.getHeaders());
        ExamDto bodyFromResponse = (ExamDto) response.getBody();
        ExamDto bodyFromExpResult = (ExamDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));

    }

    /*
     * Test for find methods.
     */
    @Test
    public void testFindByUnexistentProfessor() {
        when(examRepository.findByProfessorId(any(Long.class))).thenReturn(null);
        ResponseEntity<?> expResult = new ResponseEntity<>(null, HttpStatus.OK);

        ResponseEntity<?> response = null;
        try {
            response = ExamController.findByProfessor(1L);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testFindByProfessor() {

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam1 = new Exam(1L, "First exam", "URL", 0.1, "Exam 1 description", 3600, professor, null, null);
        Exam exam2 = new Exam(2L, "Second exam", "URL", 0.1, "Exam 2 description", 7200, professor, null, null);
        List<Exam> exams = new ArrayList<>();
        exams.add(exam1);
        exams.add(exam2);

        when(examRepository.findByProfessorId(any(Long.class))).thenReturn(exams);

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto exam1Dto = new ExamDto(1L, "First exam", "URL", 0.1, "Exam 1 description", 3600, professorDto);
        ExamDto exam2Dto = new ExamDto(2L, "Second exam", "URL", 0.1, "Exam 2 description", 7200, professorDto);
        List<ExamDto> examsDto = new ArrayList<>();
        examsDto.add(exam1Dto);
        examsDto.add(exam2Dto);

        ResponseEntity<?> expResult = new ResponseEntity<>(examsDto, HttpStatus.OK);

        ResponseEntity<?> response = null;
        try {
            response = ExamController.findByProfessor(1L);
        } catch (Exception e) {
        }

        assertEquals(expResult.getHeaders(), response.getHeaders());
        List<ExamDto> bodyFromResponse = (List<ExamDto>) response.getBody();
        List<ExamDto> bodyFromExpResult = (List<ExamDto>) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    @Test
    public void testFindByUnexistentLink() {

        when(examRepository.findByLink(any(String.class))).thenReturn(null);
        ResponseEntity<?> expResult = new ResponseEntity<>(null, HttpStatus.OK);

        ResponseEntity<?> response = null;
        try {
            response = ExamController.findByLink("https://example.com");
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testFindByLink() {

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "First exam", "https://example.com", 0.1, "Exam 1 description", 3600, professor, null, null);

        when(examRepository.findByLink(any(String.class))).thenReturn(exam);

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "First exam", "https://example.com", 0.1, "Exam 1 description", 3600, professorDto);

        ResponseEntity<?> expResult = new ResponseEntity<>(examDto, HttpStatus.OK);

        ResponseEntity<?> response = null;
        try {
            response = ExamController.findByLink("https://example.com");
        } catch (Exception e) {
        }

        assertEquals(expResult.getHeaders(), response.getHeaders());
        ExamDto bodyFromResponse = (ExamDto) response.getBody();
        ExamDto bodyFromExpResult = (ExamDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    /*
     * Test for delete method
     */
    @Test
    public void testDeleteExam() {
        ResponseEntity<?> response = null;
        try {
            response = ExamController.delete(1L);
        } catch (Exception e) {
        }

        ResponseEntity<?> expResult
                = new ResponseEntity<>("Exam deleted successfully",
                        HttpStatus.OK);

        assertEquals(expResult, response);
        verify(examRepository).deleteById(any(Long.class));
    }

    /*
     * Test of listAll method, of class ExamController.
     */
    @Test
    public void testListAll() {
        ArrayList<Exam> exams = new ArrayList<>();

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        exams.add(new Exam(1L, "First exam", "https://example1.com", 0.1, "Exam 1 description", 3600, professor, null, null));
        exams.add(new Exam(1L, "Second exam", "https://example2.com", 0.1, "Exam 2 description", 4200, professor, null, null));
        exams.add(new Exam(1L, "Third exam", "https://example3.com", 0.1, "Exam 3 description", 7200, professor, null, null));

        when(examRepository.findAll()).thenReturn(exams);

        ArrayList<ExamDto> examsDto = new ArrayList<>();

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        examsDto.add(new ExamDto(1L, "First exam", "https://example1.com", 0.1, "Exam 1 description", 3600, professorDto));
        examsDto.add(new ExamDto(1L, "Second exam", "https://example2.com", 0.1, "Exam 2 description", 4200, professorDto));
        examsDto.add(new ExamDto(1L, "Third exam", "https://example3.com", 0.1, "Exam 3 description", 7200, professorDto));

        ResponseEntity<?> expResult = new ResponseEntity<>(examsDto, HttpStatus.OK);
        ResponseEntity<?> result = null;

        try {
            result = ExamController.listAll();
        } catch (Exception e) {
        }

        List<ExamDto> bodyfromExpResult = (List<ExamDto>) expResult.getBody();
        List<ExamDto> bodyfromResult = (List<ExamDto>) result.getBody();
        assertEquals(result.getHeaders(), expResult.getHeaders());
        assertThat(bodyfromResult, sameBeanAs(bodyfromExpResult));
    }

}
