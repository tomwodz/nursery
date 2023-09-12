package pl.tomwodz.nursery.domain.presence;

import jakarta.persistence.*;
import lombok.*;
import pl.tomwodz.nursery.domain.child.dto.SimpleChildQueryDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tpresence")
class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataTimeEntry;

    private LocalDateTime dataTimeDeparture;

    @ManyToOne
    SimpleChildQueryDto child;

    public Presence(LocalDateTime dataTimeEntry, LocalDateTime dataTimeDeparture, SimpleChildQueryDto child) {
        this.dataTimeEntry = dataTimeEntry;
        this.dataTimeDeparture = dataTimeDeparture;
        this.child = child;
    }
}
