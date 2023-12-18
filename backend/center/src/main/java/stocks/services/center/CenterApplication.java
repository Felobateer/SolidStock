package stocks.services.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import stocks.services.center.repo.services.StockServices;


@SpringBootApplication
@EnableScheduling
public class CenterApplication {

	@Autowired
	private StockServices stockServices;

	public static void main(String[] args) {
		SpringApplication.run(CenterApplication.class, args);
	}



	@Scheduled(fixedRate = 600000)
	public void updateData() {
		stockServices.updateStockPrice();
	}
}
