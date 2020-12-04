import { OnInit, Directive } from '@angular/core';
import { QuestionDto } from './abstractDto/QuestionDto';
import { ExamStudentDto } from './ExamStudentDto';
import { OpenQuestionDto } from './OpenQuestionDto';

@Directive()
export class OpenResponseDto implements OnInit {
    
    public id : number;
    public content : String;
    public valoration : number;
    public examStudent : ExamStudentDto;
    public openQuestion : QuestionDto;

    ngOnInit() {
        this.id = 0;
        this.content = new String;
        this.valoration = 0.0;
        this.examStudent = new ExamStudentDto;
        this.openQuestion = new OpenQuestionDto;
    }

}
