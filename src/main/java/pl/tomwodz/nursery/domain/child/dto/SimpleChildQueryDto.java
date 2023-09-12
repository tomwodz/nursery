package pl.tomwodz.nursery.domain.child.dto;

import jakarta.persistence.*;
import pl.tomwodz.nursery.domain.groupchildren.dto.SimpleGroupChildrenQueryDto;
import pl.tomwodz.nursery.domain.user.dto.SimpleUserQueryDto;

@Entity
@Table(name ="tchild")
public class SimpleChildQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SimpleGroupChildrenQueryDto groupChildren;

    @ManyToOne
    private SimpleUserQueryDto parent;

    protected SimpleChildQueryDto() {
    }
    public SimpleChildQueryDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
