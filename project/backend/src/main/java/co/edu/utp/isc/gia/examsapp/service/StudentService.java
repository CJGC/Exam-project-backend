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
    
    public StudentDto save(StudentDto student) throws Exception {        
        try {
            this.studentValidator.setstudent(student);
            this.studentValidator.performValidationsExcept("id");
            Student auxStud = modelMapper.map(student, Student.class);
            auxStud = studentRepository.save(auxStud);
            return modelMapper.map(auxStud, StudentDto.class);
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
            return modelMapper.map(studentRepository.findById(id).get(), 
                StudentDto.class);
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
            student = (student==null) ? new Student(new Long(0),"","","",null) : student;
            return modelMapper.map(student, StudentDto.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public StudentDto update(StudentDto student) throws Exception {
        try {
            this.studentValidator.setstudent(student);
            this.studentValidator.performValidations();
            Student auxStud = studentRepository.save(modelMapper.map(student, 
                    Student.class));
            return modelMapper.map(auxStud, StudentDto.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public StudentDto delete(Long id) throws Exception {
        
        try {
            StudentDto student = modelMapper.map(studentRepository.findById(id).get(), 
                    StudentDto.class);
            studentRepository.deleteById(id);
            return student;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
