package pl.tomwodz.nursery.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tchild")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String surname;
    @ManyToOne
    private GroupChildren groupChildren;

    @Column(nullable = false)
    private LocalDate dayOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    private User parent;

}
