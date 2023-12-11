package stocks.services.center.repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocks.services.center.domain.Investors;
import stocks.services.center.repo.InvestorRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final InvestorRepo investorRepo;

    @Autowired
    public UserServices(InvestorRepo investorRepo) {
        this.investorRepo = investorRepo;
    }

    public void SignUp(String name, String email, String password, String username) {
        Investors newUser = new Investors();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUsername(username);
        newUser.setBalance(10000);
        investorRepo.save(newUser);
    }

    public Investors signIn(String username, String password) {
        List<Investors> users = investorRepo.findAll();
        for (Investors user: users) {
            if(user.getUsername().equalsIgnoreCase(username)) {
                if(user.getPassword().equals(password)) {
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public void editUserData(Long id, String field, String edit) {
        Optional<Investors> userOptional = investorRepo.findById(id);
        if(userOptional.isPresent()) {
            Investors user = userOptional.get();

            switch (field) {
                case "name":
                    user.setName(edit);
                    break;
                case "email":
                    user.setEmail(edit);
                    break;
                case "username":
                    user.setUsername(edit);
                    break;
                case "password":
                    user.setPassword(edit);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
            investorRepo.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    public void addBalance(Long id, BigDecimal amount) {
        Optional<Investors> userOptional = investorRepo.findById(id);
        if(userOptional.isPresent()) {
            Investors user = userOptional.get();
            BigDecimal prevBalance = BigDecimal.valueOf(user.getBalance());
            BigDecimal newBalance = prevBalance.add(amount);
            user.setBalance(newBalance.floatValue());
            investorRepo.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    public void deleteAccount(Long id) {
        Optional<Investors> userOptional = investorRepo.findById(id);
        if (userOptional.isPresent()){
            Investors user = userOptional.get();
            investorRepo.delete(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }
}
