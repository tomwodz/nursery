package pl.tomwodz.nursery.domain.child;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.tomwodz.nursery.domain.groupchildren.dto.SimpleGroupChildrenQueryDto;
import pl.tomwodz.nursery.domain.user.dto.SimpleUserQueryDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tchild")
class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String surname;

    @ManyToOne
    private SimpleGroupChildrenQueryDto groupChildren;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dayBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    private SimpleUserQueryDto parent;

    public Child(String name, String surname, SimpleGroupChildrenQueryDto groupChildren, LocalDateTime dayBirth, SimpleUserQueryDto parent) {
        this.name = name;
        this.surname = surname;
        this.groupChildren = groupChildren;
        this.dayBirth = dayBirth;
        this.parent = parent;
    }

}
