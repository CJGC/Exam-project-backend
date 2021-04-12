/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

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
import org.springframework.http.ResponseEntity;
import co.edu.utp.isc.gia.examsapp.data.repository.AnswerOptionRepository;
import co.edu.utp.isc.gia.examsapp.validators.AnswerOptionValidator;
import co.edu.utp.isc.gia.examsapp.web.controller.AnswerOptionController;
import co.edu.utp.isc.gia.examsapp.web.dto.AnswerOptionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.MultiQuestionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/*
 *
 * @author CJ
 */
public class AnswerOptionControllerTest {

    public AnswerOptionControllerTest() {
    }

    private AnswerOptionRepository answerOptionRepository;
    private AnswerOptionController answerOptionController;

    @Before
    public void init() {
        answerOptionRepository = Mockito.mock(AnswerOptionRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        AnswerOptionService answerOptionService = new AnswerOptionService(answerOptionRepository,
                modelMapper, new AnswerOptionValidator());
        answerOptionController = new AnswerOptionController(answerOptionService);
    }

    /*
     * Test of save method, of class answerOptionController.
     */
    @Test
    public void testSaveAnswerOptionNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("AnswerOption object is null\n",
                        HttpStatus.BAD_REQUEST);

        AnswerOptionDto answerOptionDto = null;

        try {
            response = answerOptionController.save(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveAnswerOptionNullProperties() {
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "AnswerOption description is null\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is null\n"
                        + "AnswerOption question is null\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.answerOptionController.save(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
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
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
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
                        + "Professor's password is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.answerOptionController.save(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

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

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
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
                        + "Professor's password is empty\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.answerOptionController.save(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveAnswerOptionInvalidProperties() {

        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", null, 0.0, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "AnswerOption description is empty\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is invalid\n"
                        + "AnswerOption question is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.answerOptionController.save(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
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
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(answerOptionDto, HttpStatus.OK);
        try {
            response = answerOptionController.save(answerOptionDto);
        } catch (Exception e) {}

        assertEquals(response.getHeaders(), expResult.getHeaders());
        AnswerOptionDto bodyFromResponse = (AnswerOptionDto) response.getBody();
        AnswerOptionDto bodyFromExpResult = (AnswerOptionDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    /*
        Testing update method
     */
    
    @Test
    public void testUpdateAnswerOptionNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("AnswerOption object is null\n",
                        HttpStatus.BAD_REQUEST);

        AnswerOptionDto answerOptionDto = null;

        try {
            response = answerOptionController.update(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testupdateAnswerOptionNullProperties() {
        AnswerOptionDto answerOptionDto = new AnswerOptionDto(null, null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "AnswerOption id is null\n"
                        + "AnswerOption description is null\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is null\n"
                        + "AnswerOption question is null\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.answerOptionController.update(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
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
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
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
                        + "Professor's password is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.answerOptionController.update(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

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

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "AnswerOption id is invalid\n"
                        +"AnswerOption description is empty\n"
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
                        + "Professor's password is empty\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.answerOptionController.update(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateAnswerOptionInvalidProperties() {

        AnswerOptionDto answerOptionDto = new AnswerOptionDto(0L, "", null, 0.0, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "AnswerOption id is invalid\n"
                        +"AnswerOption description is empty\n"
                        + "AnswerOption correctAnswer is null\n"
                        + "AnswerOption weight is invalid\n"
                        + "AnswerOption question is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.answerOptionController.update(answerOptionDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
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
        
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(answerOptionDto, HttpStatus.OK);
        try {
            response = answerOptionController.update(answerOptionDto);
        } catch (Exception e) {}

        assertEquals(response.getHeaders(), expResult.getHeaders());
        AnswerOptionDto bodyFromResponse = (AnswerOptionDto) response.getBody();
        AnswerOptionDto bodyFromExpResult = (AnswerOptionDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    /*
     * Test for findOne method.
     */
    
    @Test
    public void testFindByAnswerOption() {
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
        
        ResponseEntity<?> expResult = new ResponseEntity<>(answerOptionsDto, HttpStatus.OK);
        
        ResponseEntity<?> response = null;
        try {
            response = answerOptionController.findbyQuestion(1L);
        } catch (Exception e) {
        }

        assertEquals(expResult.getHeaders(), response.getHeaders());
        List<AnswerOptionDto> bodyFromResponse = (List<AnswerOptionDto>) response.getBody();
        List<AnswerOptionDto> bodyFromExpResult = (List<AnswerOptionDto>) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    @Test
    public void testFindByUnexistentAnswerOption() {

        when(answerOptionRepository.findByQuestionId(any(Long.class))).thenReturn(null);
        ResponseEntity<?> expResult = new ResponseEntity<>(null, HttpStatus.OK);
        ResponseEntity<?> response = null;
        try {
            response = answerOptionController.findbyQuestion(1L);
        } catch (Exception e) {
        }

        assertEquals(expResult.getHeaders(), response.getHeaders());
        List<AnswerOptionDto> bodyFromResponse = (List<AnswerOptionDto>) response.getBody();
        List<AnswerOptionDto> bodyFromExpResult = (List<AnswerOptionDto>) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }
    
    /*
     * Test for delete method
     */
    
    @Test
    public void testDeleteAnswerOption() {
        ResponseEntity<?> response = null;
        try {
            response = answerOptionController.delete(1L);
        } catch (Exception e) {
        }

        ResponseEntity<?> expResult
                = new ResponseEntity<>("AnswerOption deleted successfully",
                        HttpStatus.OK);

        assertEquals(expResult, response);
        verify(answerOptionRepository).deleteById(any(Long.class));
    }

    /*
     * Test of listAll method, of class answerOptionController.
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
        
        ResponseEntity<?> expResult = new ResponseEntity<>(answerOptionsDto, HttpStatus.OK);
        
        ResponseEntity<?> response = null;
        try {
            response = answerOptionController.listAll();
        } catch (Exception e) {
        }

        assertEquals(expResult.getHeaders(), response.getHeaders());
        List<AnswerOptionDto> bodyFromResponse = (List<AnswerOptionDto>) response.getBody();
        List<AnswerOptionDto> bodyFromExpResult = (List<AnswerOptionDto>) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }
}
