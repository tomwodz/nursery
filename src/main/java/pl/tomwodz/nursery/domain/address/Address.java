package pl.tomwodz.nursery.domain.address;

import jakarta.persistence.*;
import lombok.*;
import pl.tomwodz.nursery.domain.user.dto.SimpleUserQueryDto;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="taddress")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String street;

    @Column(columnDefinition = "varchar(6)", nullable = false)
    private String zipCode;

    @Column(columnDefinition = "varchar(24)", nullable = false)
    private String city;

    @OneToOne(mappedBy = "address")
    private SimpleUserQueryDto user;

    public Address(String street, String zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Address(Long id, String street, String zipCode, String city) {
        this.id = id;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }
}
