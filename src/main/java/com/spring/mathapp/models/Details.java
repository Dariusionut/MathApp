package com.spring.mathapp.models;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String info;

    @OneToOne(cascade = {DETACH, REFRESH, PERSIST, MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @Override
    public String toString() {
        return info;
    }
}
