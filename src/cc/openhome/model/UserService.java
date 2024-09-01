package cc.openhome.model;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class UserService {


    private final AccountDAO acctDAO;
    private final MessageDAO messageDAO;
    private final AccountingDAOjdbcImpl acctingDAO;

    public UserService(AccountDAO acctDAO, MessageDAO messageDAO, AccountingDAOjdbcImpl acctingDAO) {
        this.acctDAO = acctDAO;
        this.messageDAO = messageDAO;
        this.acctingDAO = acctingDAO;
    }
	
    public int findTodayAmount(String name) {
    	return acctingDAO.findTodayAmount(name);
    }
    public int findAllCash(String name) {
    	return acctingDAO.findAllCash(name);
    }
    
    public int findAllDeposit(String name) {
    	return acctingDAO.findAllDeposit(name);
    }
    
    public int findAllAmount(String name) {
    	return acctingDAO.findAllAmount(name);
    }
    
    public int getBudget(String name) {
    	return acctingDAO.getBudget(name);
    }
    
    public int getMonthCost(String name) {
    	return acctingDAO.getMonthCost(name);
    }
    
    public void loginFail(String name) {
    	acctDAO.loginFail(name);
    }
    
    public int loginFailTimes(String name) {
    	return acctDAO.loginFailTimes(name);
    }
    
    public void freezeAccount(String name) {
    	acctDAO.freezeAccount(name);
    }
    
    
    //增加budget欄位
    public Optional<Account> tryCreateUser(String email, String username, String password, int budget)  {
        if(emailExisted(email) || userExisted(username)) {
            return Optional.empty();
        }
        return Optional.of(createUser(username, email, password, budget));
    }
    
    
    //增加budget欄位
    private Account createUser(String username, String email, String password, int budget) {
        int salt = (int) (Math.random() * 100);
        int encrypt = salt + password.hashCode();
        Account acct = new Account(username, email, String.valueOf(encrypt), String.valueOf(salt), budget);
        
        acctDAO.createAccount(acct);
        return acct;
    }
    
    public void tryCreateAccounting(Accounting accting1) {
    	acctingDAO.createAccounting(accting1);
    }
    
    public Optional<String> encryptedPassword(String username, String password) {
        Optional<Account> optionalAcct = acctDAO.accountByUsername(username);
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            int salt = Integer.parseInt(acct.getSalt());
            return Optional.of(String.valueOf(password.hashCode() + salt));
        }
        return Optional.empty();
    }

    public boolean login(String username, String password) {
        if(username == null || username.trim().length() == 0) {
            return false;
        }
        
        Optional<Account> optionalAcct = acctDAO.accountByUsername(username);
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            int encrypt = Integer.parseInt(acct.getPassword());
            int salt = Integer.parseInt(acct.getSalt());
            return password.hashCode() + salt == encrypt;
        }
        return false;
    }
    
    
    public boolean userExisted(String username) {
        return acctDAO.accountByUsername(username).isPresent();
    }
    
    public boolean emailExisted(String email) {
        return acctDAO.accountByEmail(email).isPresent();
    }
    
    public Optional<Account> verify(String email, String token) {
        Optional<Account> optionalAcct= acctDAO.accountByEmail(email);
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            if(acct.getPassword().equals(token)) {
                acctDAO.activateAccount(acct);
                return Optional.of(acct);
            }
        }
        return Optional.empty();
    }

    public Optional<Account> accountByNameEmail(String name, String email) {
        Optional<Account> optionalAcct = acctDAO.accountByUsername(name);
        if(optionalAcct.isPresent() && optionalAcct.get().getEmail().equals(email)) {
            return optionalAcct;
        }
        return Optional.empty();
    }

    public void resetPassword(String name, String password) {
        int salt = (int) (Math.random() * 100);
        int encrypt = salt + password.hashCode();
        acctDAO.updatePasswordSalt(name, String.valueOf(encrypt), String.valueOf(salt));
    }
}
