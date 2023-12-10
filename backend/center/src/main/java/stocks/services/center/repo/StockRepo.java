package stocks.services.center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import stocks.services.center.domain.Stock;

public interface StockRepo extends JpaRepository<Stock, Long> {

}
