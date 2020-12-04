import { OnInit, Directive } from '@angular/core';
import { ExamDto } from './ExamDto';

@Directive()
export class ProfessorDto implements OnInit {
    public id : number;
    public identificationCard : String;
    public name : String;
    public lastname : String;
    public email : String;
    public username : String;
    public password : String;
    public exams : Array<ExamDto>;

    constructor() { 
    }

    ngOnInit() { 
        this.id = 1;
        this.username = new String("");
        this.password = new String("");
        this.name = new String("");
        this.email = new String("");
        this.exams = new Array<ExamDto>();   
    }

}