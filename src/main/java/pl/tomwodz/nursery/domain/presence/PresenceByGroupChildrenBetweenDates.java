package pl.tomwodz.nursery.domain.presence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomwodz.nursery.domain.groupchildren.dto.SimpleGroupChildrenQueryDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PresenceByGroupChildrenBetweenDates {

    private SimpleGroupChildrenQueryDto groupChildren;
    private LocalDateTime dataFrom;
    private LocalDateTime dataTo;

}
