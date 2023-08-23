package pl.tomwodz.nursery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Address address;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Child> child;

    public enum Role {
        ADMIN,
        PARENT,
        EMPLOYEE
    }

}
