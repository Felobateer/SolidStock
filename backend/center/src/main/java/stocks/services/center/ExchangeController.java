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
import java.util.Map;
import java.util.NoSuchElementException;
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

    @PostMapping("/open/buy/{id}/{symbol}/{assets}")
    public ResponseEntity<Void> openBuy(@PathVariable long id, @PathVariable String symbol, @PathVariable long assets) {
        exchangeServices.openBuy(id, symbol, assets);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/open/sell/{id}/{symbol}/{assets}")
    public ResponseEntity<Void> openSell(@PathVariable long id, @PathVariable String symbol, @PathVariable long assets) {
        exchangeServices.openSell(id, symbol, assets);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/close/buy/{id}/{symbol}")
    public ResponseEntity<Float> closeBuy(@PathVariable long id, @PathVariable String symbol) {
        float profit = exchangeServices.closeBuy(id, symbol);
        return ResponseEntity.ok(profit);
    }

    @PostMapping("/close/sell/{id}/{symbol}")
    public ResponseEntity<Float> closeSell(@PathVariable long id, @PathVariable String symbol) {
        float profit = exchangeServices.closeSell(id,symbol);
        return ResponseEntity.ok(profit);
    }

    @GetMapping("/history")
    public ResponseEntity<List<StockExchange>> showHistory(@RequestParam long id) {
        List<StockExchange> history = exchangeServices.showOpenHistory(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @PostMapping("/user/new/{name}/{email}/{password}/{username}")
    public ResponseEntity<Void> createUser(
            @PathVariable String name,
            @PathVariable String email,
            @PathVariable String password,
            @PathVariable String username
    ) {
        userServices.SignUp(name, email, password, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/user/edit/{id}/{field}/{edit}")
    public ResponseEntity<Void> editUser(
            @PathVariable long id,
            @PathVariable String field,
            @PathVariable String edit
    ) {
        userServices.editUserData(id, field, edit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/sign-in/{username}")
    public ResponseEntity<Investors> signIn(
            @PathVariable String username,
            @RequestBody Map<String, String> requestBody
    ) {
        String password = requestBody.get("password");
        Investors user = userServices.signIn(username, password);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/user/add-balance/{id}")
    public ResponseEntity<Void> addBalance(
            @PathVariable long id,
            @RequestBody BigDecimal amount
    ) {
        userServices.addBalance(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        try {
            userServices.deleteAccount(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions if needed
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
