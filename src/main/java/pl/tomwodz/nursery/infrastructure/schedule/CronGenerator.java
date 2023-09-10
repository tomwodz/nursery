package pl.tomwodz.nursery.infrastructure.schedule;


import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.child.Child;
import pl.tomwodz.nursery.domain.presence.Presence;
import pl.tomwodz.nursery.services.PresenceService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
@Log4j2
public class CronGenerator {

    private final PresenceService presenceService;
    private final ChildFacade childFacade;

    public CronGenerator(PresenceService presenceService, ChildFacade childFacade) {
        this.presenceService = presenceService;
        this.childFacade = childFacade;
    }

    private boolean run;
    @Scheduled(cron = "0    0    0    *    *    MON-FRI")
    public void generateAutoPresence() {
        if(run) {
            Random random = new Random();
            List<ChildResponseDto> childrenToAutoPresence = this.childFacade.findAllChildren();
            childrenToAutoPresence.stream()
                    .forEach(child -> presenceService.save(
                            new Presence(
                                    LocalDate.now(),
                                    LocalTime.of(
                                            random.nextInt(6, 8),
                                            random.nextInt(00, 60)),
                                    LocalTime.of(
                                            random.nextInt(14, 16),
                                            random.nextInt(00, 60)),
                                   Child.builder().id(child.id()).build())));
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
