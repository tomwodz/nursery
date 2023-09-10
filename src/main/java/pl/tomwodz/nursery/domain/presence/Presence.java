package pl.tomwodz.nursery.domain.presence;

import jakarta.persistence.*;
import lombok.*;
import pl.tomwodz.nursery.domain.child.Child;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalDate day;

    private boolean presence;

    private LocalTime timeEntry;

    private LocalTime timeDeparture;

    @ManyToOne
    Child child;

    public Presence(LocalDate day, LocalTime timeEntry, LocalTime timeDeparture) {
        this.day = day;
        this.timeEntry = timeEntry;
        this.timeDeparture = timeDeparture;
    }

    public Presence(LocalDate day, LocalTime timeEntry, LocalTime timeDeparture, Child child) {
        this.day = day;
        this.timeEntry = timeEntry;
        this.timeDeparture = timeDeparture;
        this.child = child;
    }

}
