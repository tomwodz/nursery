package pl.tomwodz.nursery.model;

import lombok.*;

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
