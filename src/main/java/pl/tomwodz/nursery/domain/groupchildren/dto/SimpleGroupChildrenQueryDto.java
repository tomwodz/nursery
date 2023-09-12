package pl.tomwodz.nursery.domain.groupchildren.dto;

import jakarta.persistence.*;
import pl.tomwodz.nursery.domain.child.dto.SimpleChildQueryDto;

import java.util.List;

@Entity
@Table(name ="tgroupchildren")
public class SimpleGroupChildrenQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "groupChildren")
    private List<SimpleChildQueryDto> child;

    protected SimpleGroupChildrenQueryDto() {
    }

    public SimpleGroupChildrenQueryDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
