package pl.tomwodz.nursery.domain.user;

import jakarta.persistence.*;
import lombok.*;
import pl.tomwodz.nursery.domain.address.Address;
import pl.tomwodz.nursery.domain.child.dto.SimpleChildQueryDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
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

    @Column(columnDefinition = "varchar(11)", nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role = Role.PARENT;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<SimpleChildQueryDto> child;

    private boolean active = true;

    public enum Role {
        ADMIN,
        PARENT,
        EMPLOYEE
    }

    public List<Role> getTypeOfRole(){
        return new ArrayList<>(Arrays.asList(Role.values()));
    }

    public User(String login, String password, String name, String surname,
                String email, String phoneNumber, Address address) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(Long id, String name, String surname, String email, String phoneNumber, Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
