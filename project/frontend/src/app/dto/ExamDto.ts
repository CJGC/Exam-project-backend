import { OnInit, Directive } from '@angular/core';
import { ProfessorDto } from './ProfessorDto';
import { QuestionDto } from './abstractDto/QuestionDto';
import { ExamStudentDto } from './ExamStudentDto';

@Directive()
export class ExamDto implements OnInit {

    public id : number;
    public name : String;
    public link : String;
    public maxGrade : number;
    public description : String;
    public examtime : number;
    public professor : ProfessorDto;
    public questions : Array<QuestionDto>;
    public examStudents : Array<ExamStudentDto>;

    ngOnInit() { 
        this.id = 0;
        this.name = new String;
        this.link = new String;
        this.maxGrade = 0.0;
        this.description = new String;
        this.examtime = 0;
        this.professor = new ProfessorDto;
        this.questions = new Array<QuestionDto>();
        this.examStudents = new Array<ExamStudentDto>();
    }
}
