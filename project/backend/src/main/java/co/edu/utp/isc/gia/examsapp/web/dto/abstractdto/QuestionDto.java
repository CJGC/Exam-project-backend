/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.dto.abstractdto;

import co.edu.utp.isc.gia.examsapp.web.dto.ExamDto;
import java.io.File;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author CJ
 */
@Setter @Getter
@SuperBuilder()
public abstract class QuestionDto {
    
    private Long id;
    private String questionType;
    private Double weight;
    private String description;
    private File questionImage;
    private ExamDto exam;
}
