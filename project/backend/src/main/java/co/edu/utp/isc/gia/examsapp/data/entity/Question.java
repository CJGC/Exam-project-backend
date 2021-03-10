/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.gia.examsapp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "Question")
@Entity()
public class Question implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="question_type")
    private String type;
    
    @Column(precision=3, scale=2)
    private Double weight;
    private String description;
    private File questionImage;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn
    private Exam exam;
    
    @JsonIgnore
    @OneToMany(mappedBy="question", cascade=CascadeType.ALL)
    private List<AnswerOption> answerOption;

    @JsonIgnore
    @OneToMany(mappedBy="question", cascade=CascadeType.ALL)
    private List<OpenResponse> openResponse;
}
