package stocks.services.center.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import stocks.services.center.domain.Investors;

public interface InvestorRepo extends JpaRepository<Investors, Long> {
}
