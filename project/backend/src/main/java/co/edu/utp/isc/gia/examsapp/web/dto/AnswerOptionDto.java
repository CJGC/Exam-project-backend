/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.dto;

import java.io.Serializable;
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
public class AnswerOptionDto implements Serializable {
    
    private Long id;
    private String description;
    private Boolean correctAnswer;
    private Double weight;
    private OpenQuestionDto question;
}
