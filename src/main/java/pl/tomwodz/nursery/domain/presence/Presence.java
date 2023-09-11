package pl.tomwodz.nursery.domain.presence;

import jakarta.persistence.*;
import lombok.*;
import pl.tomwodz.nursery.domain.child.Child;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tpresence")
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataTimeEntry;

    private LocalDateTime dataTimeDeparture;

    @ManyToOne
    Child child;

}
