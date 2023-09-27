package pl.tomwodz.nursery.infrastructure.presence.controller;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.presence.PresenceFacade;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
@Log4j2
public class PresenceScheduler {

    private final PresenceFacade presenceFacade;

    public PresenceScheduler(PresenceFacade presenceFacade) {
        this.presenceFacade = presenceFacade;
    }

    private static boolean run;

    @Scheduled(cron = "${presence.presenceRunOccurrence}")
    public void generateAutoPresence() {
        if (run) {
            log.info("cron on - generate auto day's presences for all children");
            this.presenceFacade.saveAutoPresences();
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
