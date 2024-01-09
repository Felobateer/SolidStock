package stocks.services.center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import stocks.services.center.domain.StockExchange;

public interface ExchangeRepo extends JpaRepository<StockExchange, Long> {
    StockExchange findByUserId(long userId);
}
