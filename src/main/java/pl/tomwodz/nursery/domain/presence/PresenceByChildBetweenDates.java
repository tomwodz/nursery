package pl.tomwodz.nursery.domain.presence;

import lombok.*;
import pl.tomwodz.nursery.domain.child.Child;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresenceByChildBetweenDates {

    private Child child;
    private LocalDate dataFrom;
    private LocalDate dataTo;

}
