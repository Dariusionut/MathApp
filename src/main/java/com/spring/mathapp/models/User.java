package com.spring.mathapp.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 20, unique = true, nullable = false)
    @NonNull
    private String userName;

    @Column(length = 65, nullable = false)
    @NonNull
    private String password;

    @Column(length = 45, unique = true, nullable = false)
    @NonNull
    private String email;

    @Column(length = 25, nullable = false)
    @NonNull
    private String firstName;
    @Column(length = 25, nullable = false)
    @NonNull
    private String lastName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate dob;

    private Integer age;

    private Boolean isEnabled;

    @OneToOne(mappedBy = "user", cascade = ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Details details;

    @ManyToMany(cascade = {DETACH, REFRESH, PERSIST, MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(@NonNull String userName, @NonNull String password, @NonNull String email, @NonNull String firstName,
                @NonNull String lastName, @NonNull LocalDate dob, Integer age, Boolean isEnabled, Details details, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.age = age;
        this.isEnabled = isEnabled;
        this.details = details;
        this.roles = roles;
    }

    public void addDetails(String info) {
        this.setDetails(new Details(info, this));
    }

    public void updateDetails(Long id, String info) {
        this.setDetails(new Details(id, info, this));
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public String getFullName() {
        this.setFirstName(this.getFirstName().substring(0, 1).toUpperCase() + this.getFirstName().substring(1).toLowerCase());
        this.setLastName(this.getLastName().substring(0, 1).toUpperCase() + this.getLastName().substring(1).toLowerCase());
        return this.firstName + " " + this.lastName;
    }

    public void setEncodedPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        boolean passwordMatches = encoder.matches(rawPassword, encodedPassword);
        this.password = encodedPassword;
        if (!passwordMatches) {
            System.out.println("Password not encoded!");
        }
    }

    @Override
    public String toString() {
        return userName;
    }
}
