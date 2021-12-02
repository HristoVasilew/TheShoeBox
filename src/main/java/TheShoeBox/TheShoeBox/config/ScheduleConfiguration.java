package TheShoeBox.TheShoeBox.config;

import TheShoeBox.TheShoeBox.service.ShoeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class ScheduleConfiguration {

    Logger logger = LoggerFactory.getLogger(ScheduleConfiguration.class);
    private final ShoeService shoeService;

    public ScheduleConfiguration(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @Scheduled(cron = "0 0 20 * * *")
    public void listingsCountSchedule(){

        Integer listingsCount = shoeService.findAllShoes().size();

        logger.info("Current listings count is: {}" , listingsCount);

    }
}
