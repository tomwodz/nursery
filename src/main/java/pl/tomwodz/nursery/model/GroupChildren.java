package pl.tomwodz.nursery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tgroupchildren")
public class GroupChildren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "groupChildren")
    private List<Child> child;

}
