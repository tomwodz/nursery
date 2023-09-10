package pl.tomwodz.nursery.domain.child;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.tomwodz.nursery.domain.user.User;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildren;

import java.time.LocalDate;

@Builder
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    private User parent;

    public Child(String name, String surname, LocalDate dayOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dayBirth = dayOfBirth;
    }
}
