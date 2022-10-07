package com.udacity.jdnd.course3.critter.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  @Id
  @SequenceGenerator(
      name = "customer_sequence",
      sequenceName = "customer_sequence",
      allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "customer_sequence")
  private long id;

  private String name;
  private String phoneNumber;
  private String notes;

  @OneToMany(mappedBy = "customer",
      cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  @JsonBackReference
  private List<Pet> pets;

  @ManyToMany(mappedBy = "customer")
  private List<Schedule> schedules;

}
