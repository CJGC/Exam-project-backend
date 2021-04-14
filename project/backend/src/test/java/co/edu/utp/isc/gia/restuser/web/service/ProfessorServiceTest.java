/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.restuser.web.service;

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
import java.io.IOException;
import static org.mockito.Mockito.verify;


/*
 *
 * @author CJ
 */
public class ProfessorServiceTest {

    public ProfessorServiceTest() {
    }

    private ProfessorRepository professorRepository;
    private ProfessorService professorService;

    @Before
    public void init() {
        professorRepository = Mockito.mock(ProfessorRepository.class);
        professorService = new ProfessorService(professorRepository, new ModelMapper(), new ProfessorValidator());
    }

    /*
     * Test of save method, of class ProfessorController.
     */
    @Test
    public void testSaveProfessorNullObject() {

        try {
            ProfessorDto professor = null;
            professorService.save(professor);
        } catch (Exception response) {
            IOException expectedException = new IOException("Professor object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testSaveProfessorNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);

        try {
            professorService.save(professorDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Professor's identification card is null\n"
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
    public void testSaveProfessorEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, "", "", "", "", "", "");

        try {
            professorService.save(professorDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Professor's identification card is empty\n"
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
    public void testSaveProfessorInvalidPropertiesIdCardEmailAndPassword() {

        ProfessorDto professor = new ProfessorDto(null, "number", "50Juan", "50Gomez", "invalidEmail.co", "45Janco", "123");

        try {
            professorService.save(professor);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Professor's identification card is invalid\n"
                        + "Professor's email is invalid\n"
                        + "Professor's password is invalid\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveProfessorInvalidPasswordAndIdCard() {

        ProfessorDto professor = new ProfessorDto(null, "01number", "Juan", "Gomez", "invalid@Emailco", "Janco", "123as");
        
        try {
            this.professorService.save(professor);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Professor's identification card is invalid\n"
                        + "Professor's password is invalid\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testSaveProfessorNullAsResponse() {

        when(professorRepository.save(any(Professor.class))).thenReturn(null);
        ProfessorDto inputProfessorDto = new ProfessorDto(null, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");

        ProfessorDto response;
        ProfessorDto expResult = null;
        try {
            response = professorService.save(inputProfessorDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    @Test
    public void testSaveProfessorValidInfo() {
        Professor savedProfessor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid", null);
        when(professorRepository.save(any(Professor.class))).thenReturn(savedProfessor);
        ProfessorDto inputProfessorDto = new ProfessorDto(null, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");
        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");

        ProfessorDto response = null;
        ProfessorDto expResult = outputProfessorDto;
        try {
            response = professorService.save(inputProfessorDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }

    /*
        Testing update method
     */

    @Test
    public void testUpdateProfessorNullObject() {

        try {
            ProfessorDto professor = null;
            professorService.update(professor);
        } catch (Exception response) {
            IOException expectedException = new IOException("Professor object is null\n");
            assertThat(response, sameBeanAs(expectedException));
        }

    }

    @Test
    public void testUpdateProfessorNullProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, null, null, null, null, null, null);

        try {
            professorService.update(professorDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Professor's id is null\n"
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
    public void testUpdateProfessorEmptyProperties() {
        ProfessorDto professorDto = new ProfessorDto(null, "", "", "", "", "", "");

        try {
            professorService.update(professorDto);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                          "Professor's id is null\n"
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
    public void testUpdateProfessorInvalidPropertiesIdCardEmailAndPassword() {

        ProfessorDto professor = new ProfessorDto(null, "number", "50Juan", "50Gomez", "invalidEmail.co", "45Janco", "123");

        try {
            professorService.update(professor);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                        "Professor's id is null\n"
                        + "Professor's identification card is invalid\n"
                        + "Professor's email is invalid\n"
                        + "Professor's password is invalid\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateProfessorInvalidPasswordAndIdCard() {

        ProfessorDto professor = new ProfessorDto(null, "01number", "Juan", "Gomez", "invalid@Emailco", "Janco", "123as");
        
        try {
            this.professorService.update(professor);
        } catch (Exception response) {
            IOException expectedException = new IOException(
                          "Professor's id is null\n"
                        + "Professor's identification card is invalid\n"
                        + "Professor's password is invalid\n"
            );
            assertThat(response, sameBeanAs(expectedException));
        }
    }

    @Test
    public void testUpdateProfessorNullAsResponse() {

        when(professorRepository.save(any(Professor.class))).thenReturn(null);
        ProfessorDto inputProfessorDto = new ProfessorDto(null, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");

        ProfessorDto response;
        ProfessorDto expResult = null;
        try {
            response = professorService.update(inputProfessorDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }
    
    @Test
    public void testUpdateProfessorValidInfo() {
        Professor savedProfessor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid", null);
        when(professorRepository.save(any(Professor.class))).thenReturn(savedProfessor);
        ProfessorDto inputProfessorDto = new ProfessorDto(null, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");
        ProfessorDto outputProfessorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");

        ProfessorDto response = null;
        ProfessorDto expResult = outputProfessorDto;
        try {
            response = professorService.update(inputProfessorDto);
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}
    }

    /*
     * Test for findOne method.
     */

    @Test
    public void testFindOneNonExistentProfessor() {
        when(professorRepository.findById(any(Long.class))).thenReturn(null);

        try {
            ProfessorDto response = null;
            ProfessorDto expResult = null;
            response = professorService.findOne(1L);
            assertEquals(expResult, response);
        } catch (Exception e) {
        }
    }

    @Test
    public void testFindOne() {

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid", null);
        Optional<Professor> op = Optional.of(professor);
        when(professorRepository.findById(any(Long.class))).thenReturn(op);

        ProfessorDto professorDto = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "Juanavid");
        ProfessorDto response;
        
        try {
            response = professorService.findOne(1L);
            assertThat(response, sameBeanAs(professorDto));
        } catch (Exception e) {}
    }

    /*
     * Test for delete method
     */

    @Test
    public void testDeleteProfessor() {
        
        try {
            String response;
            String expResult = "Professor deleted successfully";
            response = professorService.delete(1L);
            assertEquals(expResult, response);
            verify(professorRepository).deleteById(any(Long.class));
        } catch (Exception e) {}
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

        List<ProfessorDto> expResult = professorsDto;
        List<ProfessorDto> result;

        try {
            result = professorService.listAll();
            assertThat(expResult, sameBeanAs(result));
        } catch (Exception e) {}
        
    }

    @Test
    public void testfindUnexistentProfessor() {

        Professor professor = null;
        when(professorRepository.findByUsername(any(String.class))).thenReturn(professor);
        
        try {
            ProfessorDto expResult = null;
            ProfessorDto response;
            response = professorService.findByUsername("Asmed");
            assertEquals(expResult, response);
        } catch (Exception e) {}

    }
    
    @Test
    public void testfindProfessorByUsername() {

        Professor professor = new Professor(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password", null);
        ProfessorDto professorDtoOutput = new ProfessorDto(1L, "11", "Juan carlos", "Gomez", "juant@me.co", "Janco27", "password");
        when(professorRepository.findByUsername(any(String.class))).thenReturn(professor);
        ProfessorDto expResult = professorDtoOutput;

        ProfessorDto response;
        try {
            response = professorService.findByUsername("Janco27");
            assertThat(response, sameBeanAs(expResult));
        } catch (Exception e) {}        
    }
}
