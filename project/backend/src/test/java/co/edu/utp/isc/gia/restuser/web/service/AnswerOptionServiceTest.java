/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

import co.edu.utp.isc.gia.examsapp.data.entity.AnswerOption;
import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
import co.edu.utp.isc.gia.examsapp.data.entity.Question;
import co.edu.utp.isc.gia.examsapp.service.AnswerOptionService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import co.edu.utp.isc.gia.examsapp.data.repository.AnswerOptionRepository;
import co.edu.utp.isc.gia.examsapp.validators.AnswerOptionValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.MultiQuestionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import java.io.IOException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/*
 *
 * @author CJ
 */
public class AnswerOptionServiceTest {

    public AnswerOptionServiceTest() {
    }

    private AnswerOptionRepository answerOptionRepository;
    private AnswerOptionService answerOptionService;

    @Before
    public void init() {
        answerOptionRepository = Mockito.mock(AnswerOptionRepository.class);
        answerOptionService = new AnswerOptionService(answerOptionRepository, new ModelMapper(), new AnswerOptionValidator());
    }

    /*
     * Test of save method, of class answerOptionService.
     */
    @Test
    public void testSaveAnswerOptionNullObject() {
        AnswerOptionDto answerOptionDto = null;

        try {
            answerOptionService.save(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException("AnswerOption object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveAnswerOptionNullProperties() {
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, null);
        
        try {
            this.answerOptionService.save(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                    "AnswerOption description is null\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is null\n"
                        + "AnswerOption question is null\n");
            
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveAnswerOptionParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(null);
        multiQuestionDto.setDescription(null);
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage(null);
        multiQuestionDto.setType(null);
        multiQuestionDto.setWeight(null);

        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, multiQuestionDto);
        
        try {
            this.answerOptionService.save(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "AnswerOption description is null\n"
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
                        + "Professor's password is null\n"
            );
            
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveAnswerOptionEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "", 0.0, "", 0, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(0L);
        multiQuestionDto.setDescription("");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("");
        multiQuestionDto.setType("");
        multiQuestionDto.setWeight(0.0);
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", false, 0.0, multiQuestionDto);
                   
        try {
            this.answerOptionService.save(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "AnswerOption description is empty\n"
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
                        + "Professor's password is empty\n"
            );
            
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveAnswerOptionInvalidProperties() {

        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", null, 0.0, null);

        try {
            this.answerOptionService.save(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "AnswerOption description is empty\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is invalid\n"
                        + "AnswerOption question is null\n"
            );
            
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveAnswerOptionNullAsResponse() {
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, multiQuestionDto);
        when(answerOptionRepository.save(any(AnswerOption.class))).thenReturn(null);
        AnswerOptionDto expectedResult = null;
        
        AnswerOptionDto response;
        try {
            response = this.answerOptionService.save(answerOptionDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
    }
    
    @Test
    public void testSaveAnswerOptionValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question = new Question(1L, "mm", 0.1, "Question description", "image.jpg", exam, null, null);
        AnswerOption answerOption = new AnswerOption(1L, "Answer option description", true, 0.1, null, question);
        when(answerOptionRepository.save(any(AnswerOption.class))).thenReturn(answerOption);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, multiQuestionDto);
        
        AnswerOptionDto response = null;
        AnswerOptionDto expResult = answerOptionDto;
        try {
            response = answerOptionService.save(answerOptionDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }
    /*
        Testing update method
     */
    
    @Test
    public void testUpdateAnswerOptionNullObject() {
        AnswerOptionDto answerOptionDto = null;

        try {
            answerOptionService.update(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException("AnswerOption object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateAnswerOptionNullProperties() {
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, null);
        
        try {
            this.answerOptionService.update(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "AnswerOption id is null\n"
                        + "AnswerOption description is null\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is null\n"
                        + "AnswerOption question is null\n");
            
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateAnswerOptionParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(null);
        multiQuestionDto.setDescription(null);
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage(null);
        multiQuestionDto.setType(null);
        multiQuestionDto.setWeight(null);

        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, multiQuestionDto);
        
        try {
            this.answerOptionService.update(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "AnswerOption id is null\n"
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
                        + "Professor's password is null\n"
            );
            
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateAnswerOptionEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "", 0.0, "", 0, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(0L);
        multiQuestionDto.setDescription("");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("");
        multiQuestionDto.setType("");
        multiQuestionDto.setWeight(0.0);
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", false, 0.0, multiQuestionDto);
                   
        try {
            this.answerOptionService.update(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "AnswerOption id is invalid\n"
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
                        + "Professor's password is empty\n"
            );
            
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateAnswerOptionInvalidProperties() {

        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", null, 0.0, null);

        try {
            this.answerOptionService.update(answerOptionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "AnswerOption id is invalid\n"
                        + "AnswerOption description is empty\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is invalid\n"
                        + "AnswerOption question is null\n"
            );
            
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateAnswerOptionNullAsResponse() {
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, multiQuestionDto);
        when(answerOptionRepository.save(any(AnswerOption.class))).thenReturn(null);
        AnswerOptionDto expectedResult = null;
        
        AnswerOptionDto response;
        try {
            response = this.answerOptionService.update(answerOptionDto);
            assertEquals(expectedResult, response);
        } catch (Exception e) {}
    }
    
    @Test
    public void testUpdateAnswerOptionValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question = new Question(1L, "mm", 0.1, "Question description", "image.jpg", exam, null, null);
        AnswerOption answerOption = new AnswerOption(1L, "Answer option description", true, 0.1, null, question);
        when(answerOptionRepository.save(any(AnswerOption.class))).thenReturn(answerOption);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(1L, "Answer option description", true, 0.1, multiQuestionDto);
        
        AnswerOptionDto response = null;
        AnswerOptionDto expResult = answerOptionDto;
        try {
            response = answerOptionService.update(answerOptionDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }

    /*
     * Test for findOne method.
     */

    @Test
    public void testFindByQuestion() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question = new Question(1L, "mm", 0.1, "Question description", "image.jpg", exam, null, null);
        AnswerOption answerOption1 = new AnswerOption(1L, "Answer option 1 description", true, 0.1, null, question);
        AnswerOption answerOption2 = new AnswerOption(2L, "Answer option 2 description", true, 0.1, null, question);
        AnswerOption answerOption3 = new AnswerOption(3L, "Answer option 3 description", false, 0.1, null, question);
        
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(answerOption1); answerOptions.add(answerOption2);
        answerOptions.add(answerOption3);
        when(answerOptionRepository.findByQuestionId(any(Long.class))).thenReturn(answerOptions);

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);
        
        AnswerOptionDto answerOption1Dto = new AnswerOptionDto(1L, "Answer option 1 description", true, 0.1, multiQuestionDto);
        AnswerOptionDto answerOption2Dto = new AnswerOptionDto(2L, "Answer option 2 description", true, 0.1, multiQuestionDto);
        AnswerOptionDto answerOption3Dto = new AnswerOptionDto(3L, "Answer option 3 description", false, 0.1, multiQuestionDto);
        List<AnswerOptionDto> answerOptionsDto = new ArrayList<>();
        
        answerOptionsDto.add(answerOption1Dto);
        answerOptionsDto.add(answerOption2Dto);
        answerOptionsDto.add(answerOption3Dto);
        
        List<AnswerOptionDto> expResult = answerOptionsDto;
        
        List<AnswerOptionDto> response;
        try {
            response = answerOptionService.findByQuestion(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }

    }

    @Test
    public void testFindByUnexistentAnswerOption() {

        when(answerOptionRepository.findByQuestionId(any(Long.class))).thenReturn(null);
        List<AnswerOptionDto> expResult = null;
        List<AnswerOptionDto> response;
        try {
            response = answerOptionService.findByQuestion(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }
        
    }
    /*
     * Test for delete method
     */

    @Test
    public void testDeleteAnswerOption() {
        try {
            String expectedResponse = "AnswerOption deleted successfully";
            String response = answerOptionService.delete(1L);
            assertEquals(expectedResponse, response);
            verify(answerOptionRepository).deleteById(any(Long.class));
        } catch (Exception e) {}

    }
    
    /*
     * Test of listAll method, of class answerOptionService.
     */
    
    @Test
    public void testListAll() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question = new Question(1L, "mm", 0.1, "Question description", "image.jpg", exam, null, null);
        AnswerOption answerOption1 = new AnswerOption(1L, "Answer option 1 description", true, 0.1, null, question);
        AnswerOption answerOption2 = new AnswerOption(2L, "Answer option 2 description", true, 0.1, null, question);
        AnswerOption answerOption3 = new AnswerOption(3L, "Answer option 3 description", false, 0.1, null, question);
        
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(answerOption1); answerOptions.add(answerOption2);
        answerOptions.add(answerOption3);
        when(answerOptionRepository.findAll()).thenReturn(answerOptions);

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        
        MultiQuestionDto multiQuestionDto = new MultiQuestionDto();
        multiQuestionDto.setId(1L);
        multiQuestionDto.setDescription("Question description");
        multiQuestionDto.setExam(examDto);
        multiQuestionDto.setQuestionImage("image.jpg");
        multiQuestionDto.setType("mm");
        multiQuestionDto.setWeight(0.1);
        
        AnswerOptionDto answerOption1Dto = new AnswerOptionDto(1L, "Answer option 1 description", true, 0.1, multiQuestionDto);
        AnswerOptionDto answerOption2Dto = new AnswerOptionDto(2L, "Answer option 2 description", true, 0.1, multiQuestionDto);
        AnswerOptionDto answerOption3Dto = new AnswerOptionDto(3L, "Answer option 3 description", false, 0.1, multiQuestionDto);
        List<AnswerOptionDto> answerOptionsDto = new ArrayList<>();
        
        answerOptionsDto.add(answerOption1Dto);
        answerOptionsDto.add(answerOption2Dto);
        answerOptionsDto.add(answerOption3Dto);
        
        List<AnswerOptionDto> expResult = answerOptionsDto;
        
        List<AnswerOptionDto> response = null;
        try {
            response = answerOptionService.listAll();
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
}
