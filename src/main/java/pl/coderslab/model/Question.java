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
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.util.Set;
//
//@Data
//@Entity(name = "questions")
//@Table(name = "questions")
//public class Question {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "training_id")
//    private Training training;
//
//    private String title;
//
//    @OneToMany(mappedBy = "question")
//    private Set<Answer> answers;
//}
