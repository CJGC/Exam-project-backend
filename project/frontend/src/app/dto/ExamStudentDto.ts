import { OnInit, Directive } from '@angular/core';
import { ExamDto } from './ExamDto';
import { StudentDto } from './StudentDto';
import { OpenResponseDto } from './OpenResponseDto';

@Directive()
export class ExamStudentDto implements OnInit {

    public id : number;
    public definitiveGrade : number;
    public openResponses : Array<OpenResponseDto>;
    public student : StudentDto;
    public exam : ExamDto;

    ngOnInit() {
        this.id = 0;
        this.definitiveGrade = 0.1;
        this.openResponses = new Array<OpenResponseDto>();
        this.student = new StudentDto();
        this.exam = new ExamDto;
    }

}
