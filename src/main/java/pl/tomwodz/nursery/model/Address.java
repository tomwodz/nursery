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
    private Long id;
    private String street;
    private String zipCode;
    private String city;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
}
