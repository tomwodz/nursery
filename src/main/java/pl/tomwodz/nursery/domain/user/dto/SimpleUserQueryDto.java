package pl.tomwodz.nursery.domain.user.dto;

import jakarta.persistence.*;

@Entity
@Table(name ="tuser")
public class SimpleUserQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    protected SimpleUserQueryDto() {
    }

    public SimpleUserQueryDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
