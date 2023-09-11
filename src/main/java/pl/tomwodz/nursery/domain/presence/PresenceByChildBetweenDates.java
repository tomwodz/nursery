package pl.tomwodz.nursery.domain.presence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomwodz.nursery.domain.child.Child;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresenceByChildBetweenDates {

    private Child child;
    private LocalDateTime dataFrom;
    private LocalDateTime dataTo;

}
