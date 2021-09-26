package com.spring.mathapp.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;

import static javax.persistence.CascadeType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String info;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private Integer age;

    @OneToOne(cascade = {DETACH, REFRESH, PERSIST, MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Details(String info, LocalDate dob, User user) {
        this.info = info;
        this.dob = dob;
        this.user = user;
        this.age = this.getAge();
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return info;
    }
}
