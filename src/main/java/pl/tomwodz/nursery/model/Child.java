package pl.tomwodz.nursery.model;

import jakarta.persistence.*;
import lombok.*;


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

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    private User parent;

}
