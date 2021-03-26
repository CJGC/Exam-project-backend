/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.data.repository;

import co.edu.utp.isc.gia.examsapp.data.entity.Student;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CJ
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByIdentificationCard(String identificationCard);
}
