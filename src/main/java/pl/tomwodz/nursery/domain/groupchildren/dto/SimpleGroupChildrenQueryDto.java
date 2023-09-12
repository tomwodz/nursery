package pl.tomwodz.nursery.domain.groupchildren.dto;

import jakarta.persistence.*;

@Entity
@Table(name ="tgroupchildren")
public class SimpleGroupChildrenQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected SimpleGroupChildrenQueryDto() {
    }

    public SimpleGroupChildrenQueryDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
