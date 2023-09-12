package pl.tomwodz.nursery.domain.user.dto;

import jakarta.persistence.*;
import pl.tomwodz.nursery.domain.address.Address;

@Entity
@Table(name ="tuser")
public class SimpleUserQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    protected SimpleUserQueryDto() {
    }

    public SimpleUserQueryDto(Long id) {
        this.id = id;
    }

    public enum Role {
        ADMIN,
        PARENT,
        EMPLOYEE
    }
    public Long getId() {
        return id;
    }
}
