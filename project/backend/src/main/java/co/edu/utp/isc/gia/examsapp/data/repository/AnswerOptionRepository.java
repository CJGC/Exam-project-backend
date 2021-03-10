/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.data.repository;

import co.edu.utp.isc.gia.examsapp.data.entity.AnswerOption;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CJ
 */
public interface AnswerOptionRepository extends CrudRepository<AnswerOption, Long> {
    public List<AnswerOption> findByQuestionId(Long questionId);
}
