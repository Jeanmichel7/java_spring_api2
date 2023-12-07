package fr.lde.apitest.domainLayer.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

// import fr.lde.apitest.domainLayer.model.Employee;

@Data
@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "message", nullable = false)
  private String message;

  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee author;

  @Column(name = "date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @PrePersist
  protected void onCreate() {
    date = new Date();
  }
}
