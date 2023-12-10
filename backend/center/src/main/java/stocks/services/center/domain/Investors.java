package stocks.services.center.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Investors implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String name;
    private String email;
    private String password;
    private String username;
    private float balance;
    @OneToMany(mappedBy = "investors", cascade = CascadeType.ALL)
    private List<StockExchange> stockExchanges;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<StockExchange> getStockExchanges() {
        return stockExchanges;
    }

    public void setStockExchanges(StockExchange stockExchange) {
        this.stockExchanges.add(stockExchange);
    }
}
