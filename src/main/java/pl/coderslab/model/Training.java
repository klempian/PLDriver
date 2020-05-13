//package pl.coderslab.model;
//
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.persistence.PrimaryKeyJoinColumn;
//import javax.persistence.Table;
//import java.util.List;
//
//@Data
//@Entity(name = "trainings")
//@Table(name = "trainings")
//public class Training {
//
//    @Id
//    @GeneratedValue(generator = "gen")
//    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "advice"))
//    private Long id;
//
//    @OneToOne
//    @PrimaryKeyJoinColumn
//    private Advice advice;
//
//    private List<Question> questions;
//}
