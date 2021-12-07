package TheShoeBox.TheShoeBox.config;

import TheShoeBox.TheShoeBox.service.OrderService;
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
    private final OrderService orderService;

    public ScheduleConfiguration(ShoeService shoeService, OrderService orderService) {
        this.shoeService = shoeService;
        this.orderService = orderService;
    }

//    @Scheduled(cron = "/*10 * * * * *")
    public void listingsCountSchedule(){

        Integer listingsCount = shoeService.findAllShoes().size();
        Integer ordersCount = orderService.findAllOrders().size();

        logger.info("Current listings count is: {}" , listingsCount);
        logger.info("Current listings count is: {}" , ordersCount);

         if (ordersCount > 0)
          orderService.deleteAll();

    }
}
