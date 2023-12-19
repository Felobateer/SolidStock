package stocks.services.center;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stocks.services.center.domain.Investors;
import stocks.services.center.domain.Stock;
import stocks.services.center.domain.StockExchange;
import stocks.services.center.repo.InvestorRepo;
import stocks.services.center.repo.services.ExchangeServices;
import stocks.services.center.repo.services.StockServices;
import stocks.services.center.repo.services.UserServices;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ExchangeController {
    private final UserServices userServices;
    private final StockServices stockServices;
    private final ExchangeServices exchangeServices;

    public ExchangeController(UserServices userServices, StockServices stockServices, ExchangeServices exchangeServices) {
        this.userServices = userServices;
        this.stockServices = stockServices;
        this.exchangeServices = exchangeServices;
    }

    @GetMapping("/stocks/all")
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> stocks = exchangeServices.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/stocks/ten")
    public ResponseEntity<List<Stock>> fewStocks() {
        List<Stock> stocks = exchangeServices.getTenStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @PostMapping("/open/buy")
    public ResponseEntity<Void> openBuy(@RequestParam long id, @RequestParam String symbol, @RequestParam long assets) {
        exchangeServices.openBuy(id, symbol, assets);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/open/sell")
    public ResponseEntity<Void> openSell(@RequestParam long id, @RequestParam String symbol, @RequestParam long assets) {
        exchangeServices.openSell(id, symbol, assets);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/close/buy")
    public ResponseEntity<Float> closeBuy(@RequestParam long id, @RequestParam String symbol) {
        float profit = exchangeServices.closeBuy(id, symbol);
        return ResponseEntity.ok(profit);
    }

    @PostMapping("/close/sell")
    public ResponseEntity<Float> closeSell(@RequestParam long id, @RequestParam String symbol) {
        float profit = exchangeServices.closeSell(id,symbol);
        return ResponseEntity.ok(profit);
    }

    @GetMapping("/history")
    public ResponseEntity<List<StockExchange>> showHistory(@RequestParam long id) {
        List<StockExchange> history = exchangeServices.showOpenHistory(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @PostMapping("/user/new")
    public ResponseEntity<Void> createUser(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String username) {
        userServices.SignUp(name, email, password, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/user/edit")
    public ResponseEntity<Void> editUser(@RequestParam long id, @RequestParam String field, @RequestParam String edit) {
        userServices.editUserData(id, field, edit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/sign-in")
    public ResponseEntity<Investors> signIn(@RequestParam String username, @RequestParam String password) {
        Investors user = userServices.signIn(username, password);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/user/add-balance")
    public ResponseEntity<Void> addBalance(@RequestParam long id, @RequestParam BigDecimal amount) {
        userServices.addBalance(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        try {
            userServices.deleteAccount(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/info/{id}")
    public ResponseEntity<Investors> getUser(@PathVariable long id) {
        Optional<Investors> userOptional = userServices.getUserById(id);
        if (userOptional.isPresent()) {
            Investors user = userOptional.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
