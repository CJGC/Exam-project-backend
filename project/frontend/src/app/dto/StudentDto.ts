import { OnInit, Directive } from '@angular/core';
import { ExamDto } from './ExamDto';
import { ExamStudentDto } from './ExamStudentDto';

@Directive()
export class StudentDto implements OnInit {

    public id : number;
    public identificationCard : String;
    public name : String;
    public lastname : String;
    public examStudent : Array<ExamStudentDto>;

    ngOnInit() {
        this.id = 0;
        this.identificationCard = new String;
        this.name = new String;
        this.lastname = new String;
        this.examStudent = new Array<ExamStudentDto>();
    }

}
