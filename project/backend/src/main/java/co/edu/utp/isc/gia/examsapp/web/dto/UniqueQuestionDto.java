/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.web.dto;

import co.edu.utp.isc.gia.examsapp.web.dto.abstractdto.QuestionDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author CJ
 */

@NoArgsConstructor
@Setter  @Getter
@SuperBuilder
public class UniqueQuestionDto extends QuestionDto {
    private List<AnswerOptionDto> answerOptions;
}
