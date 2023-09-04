package pl.tomwodz.nursery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
