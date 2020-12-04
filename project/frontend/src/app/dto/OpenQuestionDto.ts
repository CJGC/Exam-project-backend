import { OnInit, Directive } from '@angular/core';
import { QuestionDto } from './abstractDto/QuestionDto';
import { ExamStudentDto } from './ExamStudentDto';

@Directive()
export class OpenQuestionDto extends QuestionDto implements OnInit {
    
    ngOnInit() {  }

}
