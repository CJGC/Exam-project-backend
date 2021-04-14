/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
import co.edu.utp.isc.gia.examsapp.data.entity.Question;
import co.edu.utp.isc.gia.examsapp.service.QuestionService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import co.edu.utp.isc.gia.examsapp.data.repository.QuestionRepository;
import co.edu.utp.isc.gia.examsapp.validators.QuestionValidator;
import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import co.edu.utp.isc.gia.examsapp.web.dto.OpenQuestionDto;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import java.io.IOException;
import static org.mockito.Mockito.verify;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

/*
 *
 * @author CJ
 */
public class QuestionServiceTest {

    public QuestionServiceTest() {
    }

    private QuestionRepository questionRepository;
    private QuestionService questionService;

    @Before
    public void init() {
        questionRepository = Mockito.mock(QuestionRepository.class);
        questionService = new QuestionService(questionRepository, new ModelMapper(), new QuestionValidator());
    }

    /*
     * Test of save method, of class questionService.
     */
    @Test
    public void testSaveQuestionNullObject() {

        OpenQuestionDto examstudent = null;

        try {
            questionService.save(examstudent);
        } catch (Exception response) {
            IOException expectedException = new IOException("Question object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveQuestionNullProperties() {
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(null);
        openQuestionDto.setDescription(null);
        openQuestionDto.setExam(null);
        openQuestionDto.setQuestionImage(null);
        openQuestionDto.setType(null);
        openQuestionDto.setWeight(null);

        try {
            this.questionService.save(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(                        
                        "Question type is null\n"
                        + "Question weight is null\n"
                        + "Question description is null\n"
                        + "Question image is null\n"
                        + "Question exam is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveQuestionParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);

        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(null);
        openQuestionDto.setDescription(null);
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage(null);
        openQuestionDto.setType(null);
        openQuestionDto.setWeight(null);

        try {
            this.questionService.save(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Question type is null\n"
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
    public void testSaveQuestionEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "", 0.0, "", 0, professorDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(0L);
        openQuestionDto.setDescription("");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("");
        openQuestionDto.setType("");
        openQuestionDto.setWeight(0.0);

        try {
            this.questionService.save(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Question type is empty\n"
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
    public void testSaveQuestionInvalidProperties() {

        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(0L);
        openQuestionDto.setDescription("");
        openQuestionDto.setExam(null);
        openQuestionDto.setQuestionImage("");
        openQuestionDto.setType("another");
        openQuestionDto.setWeight(0.0);

        try {
            this.questionService.save(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Question type is invalid\n"
                        + "Question weight is invalid\n"
                        + "Question description is empty\n"
                        + "Question exam is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testSaveQuestionNullAsResponse() {

        when(questionRepository.save(any(Question.class))).thenReturn(null);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(1L);
        openQuestionDto.setDescription("Question description");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("image.jpg");
        openQuestionDto.setType("op");
        openQuestionDto.setWeight(0.1);

        OpenQuestionDto response;
        OpenQuestionDto expResult = null;
        
        try {
            response = questionService.save(openQuestionDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    @Test
    public void testSaveQuestionValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question = new Question(1L, "op", 0.1, "Question description", "image.jpg", exam, null, null);
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(1L);
        openQuestionDto.setDescription("Question description");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("image.jpg");
        openQuestionDto.setType("op");
        openQuestionDto.setWeight(0.1);

        OpenQuestionDto response;
        OpenQuestionDto expResult = openQuestionDto;
        
        try {
            response = questionService.save(openQuestionDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }

    /*
        Testing update method
     */
    
    @Test
    public void testUpdateQuestionNullObject() {

        OpenQuestionDto examstudent = null;

        try {
            questionService.update(examstudent);
        } catch (Exception response) {
            IOException expectedException = new IOException("Question object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateQuestionNullProperties() {
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(null);
        openQuestionDto.setDescription(null);
        openQuestionDto.setExam(null);
        openQuestionDto.setQuestionImage(null);
        openQuestionDto.setType(null);
        openQuestionDto.setWeight(null);

        try {
            this.questionService.update(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(                        
                        "Question id is null\n"
                        + "Question type is null\n"
                        + "Question weight is null\n"
                        + "Question description is null\n"
                        + "Question image is null\n"
                        + "Question exam is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateQuestionParentsWithNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);
        ExamDto examDto = new ExamDto(null, null, null, null, null, null, professorDto);

        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(null);
        openQuestionDto.setDescription(null);
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage(null);
        openQuestionDto.setType(null);
        openQuestionDto.setWeight(null);

        try {
            this.questionService.update(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Question id is null\n"
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
    public void testUpdateQuestionEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "", "", "", "", "");
        ExamDto examDto = new ExamDto(0L, "", "", 0.0, "", 0, professorDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(0L);
        openQuestionDto.setDescription("");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("");
        openQuestionDto.setType("");
        openQuestionDto.setWeight(0.0);

        try {
            this.questionService.update(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Question id is invalid\n"
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
    public void testUpdateQuestionInvalidProperties() {

        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(0L);
        openQuestionDto.setDescription("");
        openQuestionDto.setExam(null);
        openQuestionDto.setQuestionImage("");
        openQuestionDto.setType("another");
        openQuestionDto.setWeight(0.0);

        try {
            this.questionService.update(openQuestionDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Question id is invalid\n"
                        +"Question type is invalid\n"
                        + "Question weight is invalid\n"
                        + "Question description is empty\n"
                        + "Question exam is null\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }
    
    @Test
    public void testUpdateQuestionNullAsResponse() {

        when(questionRepository.save(any(Question.class))).thenReturn(null);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(1L);
        openQuestionDto.setDescription("Question description");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("image.jpg");
        openQuestionDto.setType("op");
        openQuestionDto.setWeight(0.1);

        OpenQuestionDto response;
        OpenQuestionDto expResult = null;
        
        try {
            response = questionService.update(openQuestionDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    @Test
    public void testUpdateQuestionValidInfo() {
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question = new Question(1L, "op", 0.1, "Question description", "image.jpg", exam, null, null);
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        // input data
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        OpenQuestionDto openQuestionDto = new OpenQuestionDto();
        openQuestionDto.setId(1L);
        openQuestionDto.setDescription("Question description");
        openQuestionDto.setExam(examDto);
        openQuestionDto.setQuestionImage("image.jpg");
        openQuestionDto.setType("op");
        openQuestionDto.setWeight(0.1);

        OpenQuestionDto response;
        OpenQuestionDto expResult = openQuestionDto;
        
        try {
            response = questionService.update(openQuestionDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    /*
     * Test for findOne method.
     */
    @Test
    public void testFindByExamNonExistentQuestion() {
        when(questionRepository.findByExamId(any(Long.class))).thenReturn(null);
        List<OpenQuestionDto> response;
        
        OpenQuestionDto expResult = null;
        try {
            response = questionService.findByExam(1L);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }

    @Test
    public void testFindByExam() {
        // beans
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question1 = new Question(1L, "op", 0.1, "First question description", "image.jpg", exam, null, null);
        Question question2 = new Question(2L, "op", 0.3, "Second question description", "image.jpg", exam, null, null);
        Question question3 = new Question(3L, "op", 0.6, "Third question description", "image.jpg", exam, null, null);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        when(questionRepository.findByExamId(any(Long.class))).thenReturn(questions);

        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        List<OpenQuestionDto> openQuestionsDto = new ArrayList<>();

        OpenQuestionDto openQuestionDto1 = new OpenQuestionDto();
        openQuestionDto1.setId(1L);
        openQuestionDto1.setDescription("First question description");
        openQuestionDto1.setExam(examDto);
        openQuestionDto1.setQuestionImage("image.jpg");
        openQuestionDto1.setType("op");
        openQuestionDto1.setWeight(0.1);

        OpenQuestionDto openQuestionDto2 = new OpenQuestionDto();
        openQuestionDto2.setId(2L);
        openQuestionDto2.setDescription("Second question description");
        openQuestionDto2.setExam(examDto);
        openQuestionDto2.setQuestionImage("image.jpg");
        openQuestionDto2.setType("op");
        openQuestionDto2.setWeight(0.3);

        OpenQuestionDto openQuestionDto3 = new OpenQuestionDto();
        openQuestionDto3.setId(3L);
        openQuestionDto3.setDescription("Third question description");
        openQuestionDto3.setExam(examDto);
        openQuestionDto3.setQuestionImage("image.jpg");
        openQuestionDto3.setType("op");
        openQuestionDto3.setWeight(0.6);

        openQuestionsDto.add(openQuestionDto1);
        openQuestionsDto.add(openQuestionDto2);
        openQuestionsDto.add(openQuestionDto3);

        List<OpenQuestionDto> response;
        List<OpenQuestionDto> expResult = openQuestionsDto;
        try {
            response = questionService.findByExam(1L);
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
            String expResult = "Question deleted successfully";
            response = questionService.delete(1L);
            assertEquals(expResult, response);
            verify(questionRepository).deleteById(any(Long.class));
        } catch (Exception e) {}
    }
    /*
     * Test of listAll method, of class questionService.
     */
    
    @Test
    public void testListAll() {
        // beans
        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        Exam exam = new Exam(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professor, null, null);
        Question question1 = new Question(1L, "op", 0.1, "First question description", "image.jpg", exam, null, null);
        Question question2 = new Question(2L, "op", 0.3, "Second question description", "image.jpg", exam, null, null);
        Question question3 = new Question(3L, "op", 0.6, "Third question description", "image.jpg", exam, null, null);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        when(questionRepository.findAll()).thenReturn(questions);

        // dto
        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        ExamDto examDto = new ExamDto(1L, "Exam name", "URL", 0.1, "Exam description", 3600, professorDto);
        List<OpenQuestionDto> openQuestionsDto = new ArrayList<>();

        OpenQuestionDto openQuestionDto1 = new OpenQuestionDto();
        openQuestionDto1.setId(1L);
        openQuestionDto1.setDescription("First question description");
        openQuestionDto1.setExam(examDto);
        openQuestionDto1.setQuestionImage("image.jpg");
        openQuestionDto1.setType("op");
        openQuestionDto1.setWeight(0.1);

        OpenQuestionDto openQuestionDto2 = new OpenQuestionDto();
        openQuestionDto2.setId(2L);
        openQuestionDto2.setDescription("Second question description");
        openQuestionDto2.setExam(examDto);
        openQuestionDto2.setQuestionImage("image.jpg");
        openQuestionDto2.setType("op");
        openQuestionDto2.setWeight(0.3);

        OpenQuestionDto openQuestionDto3 = new OpenQuestionDto();
        openQuestionDto3.setId(3L);
        openQuestionDto3.setDescription("Third question description");
        openQuestionDto3.setExam(examDto);
        openQuestionDto3.setQuestionImage("image.jpg");
        openQuestionDto3.setType("op");
        openQuestionDto3.setWeight(0.6);

        openQuestionsDto.add(openQuestionDto1);
        openQuestionsDto.add(openQuestionDto2);
        openQuestionsDto.add(openQuestionDto3);

        List<OpenQuestionDto> response;
        List<OpenQuestionDto> expResult = openQuestionsDto;
        try {
            response = questionService.listAll();
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {
        }
    }

    @Test
    public void testSaveUnexistentImage() {

        MockMultipartFile image
                = new MockMultipartFile("image", "image.png", MediaType.IMAGE_PNG_VALUE, "image content".getBytes());

        String expResult = image.getName() + ".png";
        String response = null;
        try {
            response = this.questionService.saveImage(image);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    @Test
    public void testSaveExistentImage() {

        MockMultipartFile image
                = new MockMultipartFile("image", "image.png", MediaType.IMAGE_PNG_VALUE, "image content".getBytes());

        String expResult = image.getName() + ".png";
        String response = null;
        try {
            response = this.questionService.saveImage(image);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}

    }

    @Test
    public void testSaveInvalidImage() {

        MockMultipartFile image
                = new MockMultipartFile("file", "file.txt", MediaType.TEXT_PLAIN_VALUE, "file content".getBytes());

        String expResult = "Invalid data type file or empty file";
        String response = null;

        try {
            response = this.questionService.saveImage(image);
            assertEquals(expResult, response);
        } catch (Exception e) {
        }

    }

    @Test
    public void testUploadImage() {

        try {
            byte[] expResult = "image content".getBytes();
            byte[] response = null;
            response = this.questionService.uploadImage("image.png");
            assertArrayEquals(expResult, response);

        } catch (Exception e) {
        }

    }

    @Test
    public void testUploadUnexistentImage() {

        try {
            this.questionService.uploadImage("unexistent.png");

        } catch (Exception response) {
            Exception expectedException = new Exception("Invalid route or file does not exist");
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testDeleteImage() {

        try {
            String expResult = "image.png";
            String response;
            response = this.questionService.deleteImage("image.png");
            assertThat(response, sameBeanAs(expResult));

        } catch (Exception e) {
        }

    }

}
