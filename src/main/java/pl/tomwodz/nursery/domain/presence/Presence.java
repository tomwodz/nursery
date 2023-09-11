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

    public Presence(LocalDateTime dataTimeEntry, LocalDateTime dataTimeDeparture) {
        this.dataTimeEntry = dataTimeEntry;
        this.dataTimeDeparture = dataTimeDeparture;
    }

    /*    public Presence(LocalDate day, LocalTime timeEntry, LocalTime timeDeparture) {
        this.timeEntry = timeEntry;
        this.timeDeparture = timeDeparture;
    }

    public Presence(LocalDate day, LocalTime timeEntry, LocalTime timeDeparture, Child child) {
        this.day = day;
        this.timeEntry = timeEntry;
        this.timeDeparture = timeDeparture;
        this.child = child;
    }*/

}
