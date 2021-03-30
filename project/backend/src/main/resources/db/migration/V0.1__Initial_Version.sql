CREATE TABLE Professor (

    id bigint auto_increment,
    identification_card varchar(100) unique not null,
    name varchar(100) not null,
    lastname varchar(100) not null,
    email varchar(100) unique not null,
    username varchar(100) not null,
    password varchar(100) not null,

    CONSTRAINT pk_professor PRIMARY KEY (id)
);


CREATE TABLE Exam (
    id bigint auto_increment,
    name varchar(100) not null,
    link varchar(512) not null,
    max_grade double default 0.0,
    description varchar(1024) not null,
    durability int default 0,
    professor_id bigint,

    constraint pk_exam primary key (id),
    foreign key (professor_id) references Professor(id)
);

CREATE TABLE Question (
    id bigint auto_increment,
    question_type varchar(10) not null,
    weight double default 0.0,
    description varchar(2048) not null,
    question_image varbinary,
    exam_id bigint,

    constraint pk_question primary key (id),
    foreign key (exam_id) references Exam(id)
);

CREATE TABLE Answer_option (
    id bigint auto_increment,
    description varchar(2048) not null,
    correct_answer boolean default false,
    weight double default 0.0,
    question_id bigint,
    
    constraint pk_asnwer_option primary key (id),
    foreign key (question_id) references Question(id)
);

CREATE TABLE Student (
    id bigint auto_increment,
    identification_card varchar(100) not null,
    name varchar(100) not null,
    lastname varchar default false,
    
    constraint pk_student primary key (id)
);

CREATE TABLE Exam_student (
    id bigint auto_increment,
    exam_id bigint,
    student_id bigint,
    definitive_grade double default 0.0,
    
    constraint pk_exam_student primary key (id),

    foreign key (exam_id) references Exam(id),
    foreign key (student_id) references Student(id)
);

CREATE TABLE Open_response (
    id bigint auto_increment,
    content varchar(2048),
    valoration double default 0.0,
    exam_student_id bigint,
    question_id bigint,

    constraint pk_open_response primary key (id),

    foreign key (exam_student_id) references Exam_student(id),
    foreign key (question_id) references Question(id)
);

CREATE TABLE Selected_response (
    id bigint auto_increment,
    valoration double default 0.0,
    exam_student_id bigint,
    answer_option_id bigint,

    constraint pk_selected_response primary key (id),

    foreign key (exam_student_id) references Exam_student(id),
    foreign key (answer_option_id) references Answer_option(id)
);