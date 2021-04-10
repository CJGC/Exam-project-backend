/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.examsapp.data.entity.Professor;
import co.edu.utp.isc.gia.examsapp.service.ProfessorService;
import co.edu.utp.isc.gia.examsapp.web.dto.ProfessorDto;
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
import co.edu.utp.isc.gia.examsapp.data.repository.ProfessorRepository;
import co.edu.utp.isc.gia.examsapp.validators.ProfessorValidator;
import co.edu.utp.isc.gia.examsapp.web.controller.ProfessorController;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.mockito.Mockito.verify;


/*
 *
 * @author CJ
 */
public class ProfessorControllerTest {

    public ProfessorControllerTest() {
    }

    private ProfessorRepository professorRepository;
    private ProfessorController professorController;

    @Before
    public void init() {
        professorRepository = Mockito.mock(ProfessorRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ProfessorService professorService = new ProfessorService(professorRepository,
                modelMapper, new ProfessorValidator());
        professorController = new ProfessorController(professorService);
    }

    /*
     * Test of save method, of class ProfessorController.
     */
    @Test
    public void testSaveProfessorNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("Professor object is null\n",
                        HttpStatus.BAD_REQUEST);

        ProfessorDto professor = null;

        try {
            response = professorController.save(professor);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveProfessorNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's identification card is null\n"
                        + "Professor's name is null\n"
                        + "Professor's lastname is null\n"
                        + "Professor's email is null\n"
                        + "Professor's username is null\n"
                        + "Professor's password is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.professorController.save(professorDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

    }

    @Test
    public void testSaveProfessorEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, "", "", "", "", "", "");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's identification card is empty\n"
                        + "Professor's name is empty\n"
                        + "Professor's lastname is empty\n"
                        + "Professor's email is empty\n"
                        + "Professor's username is empty\n"
                        + "Professor's password is empty\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.professorController.save(professorDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveProfessorInvalidPropertiesIdCardEmailAndPassword() {

        ProfessorDto professor = new ProfessorDto(null, "number", "50Juan", "50Gomez", "invalidEmail.co", "45Janco", "123");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's identification card is invalid\n"
                        + "Professor's email is invalid\n"
                        + "Professor's password is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.professorController.save(professor);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveProfessorInvalidPasswordAndIdCard() {

        ProfessorDto professor = new ProfessorDto(null, "01number", "Juan", "Gomez", "invalid@Emailco", "Janco", "123as");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's identification card is invalid\n"
                        + "Professor's password is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.professorController.save(professor);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testSaveProfessorValidInfo() {
        Professor savedProfessor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid", null);
        when(professorRepository.save(any(Professor.class))).thenReturn(savedProfessor);
        ProfessorDto inputProfessorDto = new ProfessorDto(null, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");
        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(outputProfessorDto, HttpStatus.OK);
        try {
            response = professorController.save(inputProfessorDto);

        } catch (Exception e) {
        }

        assertEquals(response.getHeaders(), expResult.getHeaders());

        ProfessorDto bodyFromResponse = (ProfessorDto) response.getBody();
        ProfessorDto bodyFromExpResult = (ProfessorDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    /*
        Testing update method
     */
    @Test
    public void testUpdateProfessorNullObject() {

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>("Professor object is null\n",
                        HttpStatus.BAD_REQUEST);

        ProfessorDto professor = null;

        try {
            response = professorController.update(professor);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateProfessorNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null,
                null, null, null, null);

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's id is null\n"
                        + "Professor's identification card is null\n"
                        + "Professor's name is null\n"
                        + "Professor's lastname is null\n"
                        + "Professor's email is null\n"
                        + "Professor's username is null\n"
                        + "Professor's password is null\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.professorController.update(professorDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);

    }

    @Test
    public void testUpdateProfessorEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(0L, "", "",
                "", "", "", "");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's id is invalid\n"
                        + "Professor's identification card is empty\n"
                        + "Professor's name is empty\n"
                        + "Professor's lastname is empty\n"
                        + "Professor's email is empty\n"
                        + "Professor's username is empty\n"
                        + "Professor's password is empty\n",
                        HttpStatus.BAD_REQUEST);
        try {
            response = this.professorController.update(professorDto);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateProfessorInvalidPropertiesIdCardEmailAndPassword() {

        ProfessorDto professor = new ProfessorDto(0L, "number", "Juan", "Gomez", "emaildomain.co", "45Janco", "123");

        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's id is invalid\n"
                        + "Professor's identification card is invalid\n"
                        + "Professor's email is invalid\n"
                        + "Professor's password is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.professorController.update(professor);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateProfessorInvalidPasswordAndIdCard() {

        ProfessorDto professor = new ProfessorDto(1L, "01number", "Juan", "Gomez", "invalid@Emailco", "Janco", "123as");
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult
                = new ResponseEntity<>(
                        "Professor's identification card is invalid\n"
                        + "Professor's password is invalid\n",
                        HttpStatus.BAD_REQUEST);

        try {
            response = this.professorController.update(professor);
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testUpdateProfessorValidInfo() {
        Professor savedProfessor = new Professor(1L, "11", "Juan(edited)", "Gomez(edited)", "juan@domain.co", "Janco27", "password", null);
        when(professorRepository.save(any(Professor.class))).thenReturn(savedProfessor);
        ProfessorDto inputProfessorDto = new ProfessorDto(1L, "11", "Juan(edited)", "Gomez(edited)", "juan@domain.co", "Janco27", "password");
        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan(edited)", "Gomez(edited)", "juan@domain.co", "Janco27", "password");
        ResponseEntity<?> response = null;
        ResponseEntity<?> expResult = new ResponseEntity<>(outputProfessorDto, HttpStatus.OK);

        try {
            response = professorController.update(inputProfessorDto);
        } catch (Exception e) {
        }

        assertEquals(response.getHeaders(), expResult.getHeaders());

        ProfessorDto bodyFromResponse = (ProfessorDto) response.getBody();
        ProfessorDto bodyFromExpResult = (ProfessorDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));

    }

    /*
     * Test for findOne method.
     */
    @Test
    public void testFindOneNonExistentProfessor() {
        when(professorRepository.findById(any(Long.class))).thenReturn(null);
        ResponseEntity<?> response = null;
        try {
            response = professorController.findOne(1L);
        } catch (Exception e) {
        }

        ResponseEntity<?> expResult
                = new ResponseEntity<>(null,
                        HttpStatus.OK);

        assertEquals(expResult, response);

    }

    @Test
    public void testFindOne() {

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid", null);
        Optional<Professor> op = Optional.of(professor);
        when(professorRepository.findById(any(Long.class))).thenReturn(op);

        ResponseEntity<?> response = null;
        try {
            response = professorController.findOne(1L);
        } catch (Exception e) {
        }

        ProfessorDto professorDtoOutput = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");
        ResponseEntity<?> expResult = new ResponseEntity<>(professorDtoOutput, HttpStatus.OK);

        assertEquals(expResult.getHeaders(), response.getHeaders());
        ProfessorDto bodyFromResponse = (ProfessorDto) response.getBody();
        ProfessorDto bodyFromExpResult = (ProfessorDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }

    /*
     * Test for delete method
     */
    @Test
    public void testDeleteProfessor() {
        ResponseEntity<?> response = null;
        try {
            response = professorController.delete(1L);
        } catch (Exception e) {
        }

        ResponseEntity<?> expResult
                = new ResponseEntity<>("Professor deleted successfully",
                        HttpStatus.OK);

        assertEquals(expResult, response);
        verify(professorRepository).deleteById(any(Long.class));
    }

    /*
     * Test of listAll method, of class ProfessorController.
     */
    @Test
    public void testListAll() {
        ArrayList<Professor> professors = new ArrayList<>();

        professors.add(new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid", null));
        professors.add(new Professor(2L, "12", "Carlos mario", "Lopez", "carlos@me.co", "Carl727", "Carlosthebest", null));
        professors.add(new Professor(3L, "13", "Natalia", "Castano", "natalia@me.co", "Natalia50", "Nataliaalva", null));

        when(professorRepository.findAll()).thenReturn(professors);

        ArrayList<ProfessorDto> professorsDto = new ArrayList<>();
        professorsDto.add(new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid"));
        professorsDto.add(new ProfessorDto(2L, "12", "Carlos mario", "Lopez", "carlos@me.co", "Carl727", "Carlosthebest"));
        professorsDto.add(new ProfessorDto(3L, "13", "Natalia", "Castano", "natalia@me.co", "Natalia50", "Nataliaalva"));

        ResponseEntity<?> expResult = new ResponseEntity<>(professorsDto, HttpStatus.OK);
        ResponseEntity<?> result = null;

        try {
            result = professorController.listAll();
        } catch (Exception e) {
        }

        assertEquals(result.getHeaders(), expResult.getHeaders());
        List<ProfessorDto> bodyfromExpResult = (List<ProfessorDto>) expResult.getBody();
        List<ProfessorDto> bodyfromResult = (List<ProfessorDto>) result.getBody();
        assertThat(bodyfromResult, sameBeanAs(bodyfromExpResult));
    }

    @Test
    public void testfindUnexistentProfessor() {

        Professor professor = null;
        when(professorRepository.findByUsername(any(String.class))).thenReturn(professor);
        ResponseEntity<?> expResult
                = new ResponseEntity<>(null, HttpStatus.OK);

        ResponseEntity<?> response = null;
        try {
            response = professorController.findByUsername("Asmed");
        } catch (Exception e) {
        }

        assertEquals(expResult, response);
    }

    @Test
    public void testfindProfessorByUsername() {

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);

        ProfessorDto professorDtoOutput = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        when(professorRepository.findByUsername(any(String.class))).thenReturn(professor);
        ResponseEntity<?> expResult
                = new ResponseEntity<>(professorDtoOutput, HttpStatus.OK);

        ResponseEntity<?> response = null;
        try {
            response = professorController.findByUsername("Janco27");
        } catch (Exception e) {
        }

        assertEquals(expResult.getHeaders(), response.getHeaders());

        ProfessorDto bodyFromResponse = (ProfessorDto) response.getBody();
        ProfessorDto bodyFromExpResult = (ProfessorDto) expResult.getBody();
        assertThat(bodyFromResponse, sameBeanAs(bodyFromExpResult));
    }
}
