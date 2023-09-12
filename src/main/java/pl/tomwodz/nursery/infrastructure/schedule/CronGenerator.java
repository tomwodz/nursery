package pl.tomwodz.nursery.infrastructure.schedule;


import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.presence.PresenceFacade;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
@Log4j2
public class CronGenerator {

    private final PresenceFacade presenceFacade;
    private final ChildFacade childFacade;

    public CronGenerator(PresenceFacade presenceFacade, ChildFacade childFacade) {
        this.presenceFacade = presenceFacade;
        this.childFacade = childFacade;
    }

    private boolean run;
    @Scheduled(cron = "0    0   18    *    *     *")
    public void generateAutoPresence() {
        if(run) {
            Random random = new Random();
            List<ChildResponseDto> childrenToAutoPresence = this.childFacade.findAllChildren();
            childrenToAutoPresence.stream()
                    .forEach(ch -> presenceFacade.savePresence(
                            PresenceRequestDto.builder()
                                    .id(ch.id())
                                    .dataTimeEntry(LocalDateTime.of(LocalDate.now(),
                                            LocalTime.of(
                                            random.nextInt(6, 8),
                                            random.nextInt(00, 60))))
                                    .dataTimeDeparture(LocalDateTime.of(LocalDate.now(),
                                            LocalTime.of(
                                            random.nextInt(14, 16),
                                            random.nextInt(00, 60))))
                                    .build())
                    );
            log.info("cron on - generate auto day's presences for all children");
        } else {
            log.info("cron off");
        }
    }

    public boolean isRun() {
        return run;
    }

    public void cornOnOff() {
        if(run == true){
            run = false;
            log.info("corn off - admin stopped");
        } else {
            run = true;
            log.info("corn on - admin started");
        }
    }

}
