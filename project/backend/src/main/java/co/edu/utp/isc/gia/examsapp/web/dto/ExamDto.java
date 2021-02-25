/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.dto;

import co.edu.utp.isc.gia.examsapp.web.dto.abstractdto.QuestionDto;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author CJ
 */
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class ExamDto implements Serializable {
    
    private Long id;
    private String name;
    private String link;
    private Double maxGrade;
    private String description;
    private Integer durability;
    private ProfessorDto professor;
}
