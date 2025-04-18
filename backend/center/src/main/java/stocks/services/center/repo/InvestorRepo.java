package stocks.services.center.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stocks.services.center.domain.Investors;


public interface InvestorRepo extends JpaRepository<Investors, Long> {

}
