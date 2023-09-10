package pl.tomwodz.nursery.domain.presence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildren;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresenceByGroupChildrenBetweenDates {

    private GroupChildren groupChildren;
    private LocalDate dataFrom;
    private LocalDate dataTo;

}
