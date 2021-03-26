/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.data.repository;

import co.edu.utp.isc.gia.examsapp.data.entity.Exam;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Christhian Gomez
 */
public interface ExamRepository extends CrudRepository<Exam, Long> {
    List<Exam> findByProfessorId(Long professorID);
    Exam findByLink(String link);
}
