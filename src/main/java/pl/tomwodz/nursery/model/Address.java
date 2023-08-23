package pl.tomwodz.nursery.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
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

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String zipCode;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String city;

    @OneToOne(mappedBy = "address")
    private User user;

}
