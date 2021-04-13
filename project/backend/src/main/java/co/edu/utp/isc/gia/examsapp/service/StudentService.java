/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.service;

import co.edu.utp.isc.gia.examsapp.data.entity.Student;
import co.edu.utp.isc.gia.examsapp.web.dto.StudentDto;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import co.edu.utp.isc.gia.examsapp.data.repository.StudentRepository;
import co.edu.utp.isc.gia.examsapp.validators.StudentValidator;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author CJ
 */
@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final StudentValidator studentValidator;
    
    public StudentService(
            StudentRepository studentRepository, 
            ModelMapper modelMapper,
            StudentValidator studentValidator)
    {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.studentValidator = studentValidator;
    }
    
    public StudentDto save(StudentDto studentDto) throws Exception {        
        try {
            this.studentValidator.setStudent(studentDto);
            this.studentValidator.setExceptions("");
            this.studentValidator.performValidationsExcept("id");
            String studentExceptions = this.studentValidator.getExceptions();
            
            if (studentExceptions.length() > 0) {
                throw new IOException(studentExceptions);
            }
            
            Student student = modelMapper.map(studentDto, Student.class);
            student = studentRepository.save(student);
            
            if (student != null) {
                return modelMapper.map(student, StudentDto.class);
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public List<StudentDto> listAll() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        
        List<StudentDto> studentsDto = new ArrayList<>();
        students.forEach(student -> {
            studentsDto.add(modelMapper.map(student, StudentDto.class));
        });
        return studentsDto;
    }
    
    public StudentDto findOne(Long id) throws Exception {
        try {
            
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent != null) {
                return modelMapper.map(optionalStudent.get(), StudentDto.class);                
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public StudentDto findByIdentificationCard(String identificationCard) throws Exception {
        try {
            Student student;
            student = studentRepository.findByIdentificationCard(identificationCard);
            
            if (student != null) {
                return modelMapper.map(student, StudentDto.class);
            } else {
                return null;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public StudentDto update(StudentDto studentDto) throws Exception {
        try {
            this.studentValidator.setStudent(studentDto);
            this.studentValidator.setExceptions("");
            this.studentValidator.performValidations();
            String studentExceptions = this.studentValidator.getExceptions();
            
            if (studentExceptions.length() > 0) {
                throw new IOException(studentExceptions);
            }
            
            Student student = modelMapper.map(studentDto, Student.class);
            student = studentRepository.save(student);
            
            if (student != null) {
                return modelMapper.map(student, StudentDto.class);                
            } else {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public String delete(Long id) throws Exception {
        try {
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
