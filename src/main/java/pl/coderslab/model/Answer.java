//package pl.coderslab.model;
//
//import lombok.Data;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Data
//@Entity(name = "answers")
//@Table(name = "answers")
//public class Answer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;
//
//    private String content;
//    private String multimedia;
//    private boolean correct;
//}
