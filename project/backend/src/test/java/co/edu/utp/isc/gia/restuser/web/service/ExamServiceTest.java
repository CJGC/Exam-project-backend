/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
import co.edu.utp.isc.gia.examsapp.data.repository.ExamRepository;
import co.edu.utp.isc.gia.examsapp.service.ExamService;
import co.edu.utp.isc.gia.examsapp.validators.ExamValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import java.io.IOException;
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

/**
 *
 * @author Christhian Gomez
 */
public class ExamServiceTest {

    private ExamRepository examRepository;
    private ExamService examService;

    @Before
    public void init() {
        examRepository = Mockito.mock(ExamRepository.class);
        examService = new ExamService(examRepository, new ModelMapper(), new ExamValidator());
    }

    /*
     * Test of save method, of class ExamService.
     */
    @Test
    public void testSaveExamNullObject() {
        try {
            ExamDto exam = null;
            examService.save(exam);
        } catch (Exception response) {
            IOException expectedException = new IOException("Exam object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveExamNullProperties() {
        ExamDto examDto = new ExamDto(null, null, null,
                null, null, null, null);

        try {
            this.examService.save(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                          "Exam name is null\n"
                        + "Exam link is null\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is null\n"
                        + "Exam durability is null\n"
                        + "Exam professor is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveExamEmptyProperties() {
        ExamDto examDto = new ExamDto(null, "", "",
                null, "", null, null);

        try {
            this.examService.save(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is empty\n"
                        + "Exam durability is null\n"
                        + "Exam professor is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveExamInvalidPropertiesMaxGradeDurability() {

        ExamDto examDto = new ExamDto(0L, "", "",
                -1.0, "", -1, null);
        try {
            this.examService.save(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Exam professor is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveExamInvalidPropertiesWithProfessor() {

        ProfessorDto professorDto = new ProfessorDto(null, "01number", "Juan",
                "Gomez", "invalid@Emailco", "Janco", "123as");

        ExamDto examDto = new ExamDto(0L, "", "",
                -1.0, "", -1, professorDto);

        try {
            this.examService.save(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Professor's id is null\n"
                        + "Professor's identification card is invalid\n"
                        + "Professor's password is invalid\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveAnswerOptionNullAsResponse() {

        when(examRepository.save(any(Exam.class))).thenReturn(null);
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez",
                "juant@me.co", "Janco27", "password");

        ExamDto examDto = new ExamDto(null, "Exam name", "URL",
                0.1, "Exam description", 3600, professorDto);
        ExamDto response;
        ExamDto expectedResult = null;
        try {
            response = this.examService.save(examDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
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

        ExamDto response;
        ExamDto expResult = outputExamDto;
        try {
            response = examService.save(examDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }

    /*
        Testing update method
     */
    
    @Test
    public void testUpdateExamNullObject() {
        try {
            ExamDto exam = null;
            examService.update(exam);
        } catch (Exception response) {
            IOException expectedException = new IOException("Exam object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateExamNullProperties() {
        ExamDto examDto = new ExamDto(null, null, null,
                null, null, null, null);

        try {
            this.examService.update(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Exam id is null\n"    
                        + "Exam name is null\n"
                        + "Exam link is null\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is null\n"
                        + "Exam durability is null\n"
                        + "Exam professor is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateExamEmptyProperties() {
        ExamDto examDto = new ExamDto(null, "", "",
                null, "", null, null);

        try {
            this.examService.update(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Exam id is null\n"    
                        + "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is null\n"
                        + "Exam description is empty\n"
                        + "Exam durability is null\n"
                        + "Exam professor is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateExamInvalidPropertiesMaxGradeDurability() {

        ExamDto examDto = new ExamDto(0L, "", "", -1.0, "", -1, null);
        
        try {
            this.examService.update(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Exam id is invalid\n"
                        + "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Exam professor is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateExamInvalidPropertiesWithProfessor() {

        ProfessorDto professorDto = new ProfessorDto(null, "01number", "Juan",
                "Gomez", "invalid@Emailco", "Janco", "123as");

        ExamDto examDto = new ExamDto(0L, "", "",-1.0, "", -1, professorDto);

        try {
            this.examService.update(examDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Exam id is invalid\n"
                        + "Exam name is empty\n"
                        + "Exam link is empty\n"
                        + "Exam maxgrade is invalid\n"
                        + "Exam description is empty\n"
                        + "Exam durability is invalid\n"
                        + "Professor's id is null\n"
                        + "Professor's identification card is invalid\n"
                        + "Professor's password is invalid\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateAnswerOptionNullAsResponse() {

        when(examRepository.save(any(Exam.class))).thenReturn(null);
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");

        ExamDto examDto = new ExamDto(null, "Exam name", "URL",0.1, "Exam description", 3600, professorDto);
        ExamDto response;
        ExamDto expectedResult = null;
        try {
            response = this.examService.update(examDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
    }
    
    @Test
    public void testUpdateExamValidInfo() {
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

        ExamDto response;
        ExamDto expResult = outputExamDto;
        try {
            response = examService.update(examDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }

    /*
     * Test for find methods.
     */
    
    @Test
    public void testFindByUnexistentProfessor() {
        when(examRepository.findByProfessorId(any(Long.class))).thenReturn(null);
        List<ExamDto> expResult = null;
        List<ExamDto> response;

        try {
            response = examService.findByProfessor(1L);
            assertEquals(expResult, response);
        } catch (Exception e) {
        }
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

        List<ExamDto> expResult = examsDto;
        List<ExamDto> response = null;
        
        try {
            response = examService.findByProfessor(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }


    }

    @Test
    public void testFindByUnexistentLink() {

        when(examRepository.findByLink(any(String.class))).thenReturn(null);
        ExamDto expResult = null;
        ExamDto response;
        
        try {
            response = examService.findByLink("https://example.com");
            assertEquals(expResult, response);
        } catch (Exception e) {
        }
    }

    @Test
    public void testFindByLink() {

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "First exam", "https://example.com", 0.1, "Exam 1 description", 3600, professor, null, null);
        when(examRepository.findByLink(any(String.class))).thenReturn(exam);

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "First exam", "https://example.com", 0.1, "Exam 1 description", 3600, professorDto);

        ExamDto expResult = examDto;
        ExamDto response;
        try {
            response = examService.findByLink("https://example.com");
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }

    /*
     * Test for delete method
     */
 
    @Test
    public void testDeleteExam() {
        
        try {
            String response;
            String expResult = "Exam deleted successfully";
            response = examService.delete(1L);
            assertEquals(expResult, response);
            verify(examRepository).deleteById(any(Long.class));
        } catch (Exception e) {
        }

    }

    /*
     * Test of listAll method, of class ExamService.
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

        List<ExamDto> expResult = examsDto;
        List<ExamDto> response;

        try {
            response = examService.listAll();
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }
}
