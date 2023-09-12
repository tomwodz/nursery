package pl.tomwodz.nursery.domain.groupchildren;

import jakarta.persistence.*;
import lombok.*;
import pl.tomwodz.nursery.domain.child.Child;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tgroupchildren")
class GroupChildren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String name;

    @OneToMany(mappedBy = "groupChildren")
    private List<Child> child;

    GroupChildren(String name) {
        this.name = name;
    }
    GroupChildren(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
