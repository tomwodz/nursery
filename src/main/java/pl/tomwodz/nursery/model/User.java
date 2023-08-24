package pl.tomwodz.nursery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Column(name ="id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String surname;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role = Role.PARENT;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Child> child;


    public enum Role {
        ADMIN,
        PARENT,
        EMPLOYEE
    }

    public List<Role> getTypeOfRole(){
        return new ArrayList<>(Arrays.asList(Role.values()));
    }

}
