package cc.openhome.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

public class AccountDAOJdbcImpl implements AccountDAO {
    private DataSource dataSource;
    
    public AccountDAOJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public void createAccount(Account acct) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
               "INSERT INTO account(name, email, password, salt, budget) VALUES(?, ?, ?, ?, ?)");
            PreparedStatement stmt2 = conn.prepareStatement(
               "INSERT INTO account_role(name, role, loginfail) VALUES(?, 'unverified', 1)")) {
            
            stmt.setString(1, acct.getName());
            stmt.setString(2, acct.getEmail());
            stmt.setString(3, acct.getPassword());
            stmt.setString(4, acct.getSalt());
            stmt.setInt(5, acct.getBudget());
            stmt.executeUpdate();
            
            stmt2.setString(1, acct.getName());
            stmt2.executeUpdate();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        } 
    }

    @Override
    public Optional<Account> accountByUsername(String name) {
        try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT * FROM account WHERE name = ?")) {
            stmt.setString(1, name);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return Optional.of(new Account(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
                ));
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Account> accountByEmail(String email) {
        try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT * FROM account WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return Optional.of(new Account(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
                ));
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void activateAccount(Account acct) {			
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
               "UPDATE account_role SET role = ? WHERE name = ?")) {
            stmt.setString(1, "member");	
            stmt.setString(2, acct.getName());
            stmt.executeUpdate();

        } catch (SQLException e) {
           throw new RuntimeException(e);
        } 
    }
    
    
    @Override
    public int loginFailTimes(String name) {
    	
        try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT loginfail FROM account_role WHERE name = ?")) {
            stmt.setString(1, name);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                return rs.getInt(1);	 //return times
            }
            return -1;					
            
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    @Override
    public void freezeAccount(String name) {	
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
               "UPDATE account_role SET role = ? WHERE name = ?")) {
            stmt.setString(1, "freeze");						//role -> freeze, cannot login
            stmt.setString(2, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
           throw new RuntimeException(e);
        } 
    }
    
    @Override
    public void loginFail(String name) {					//accumulate fail times if login fail
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
               "UPDATE account_role SET loginfail = loginfail+1 WHERE name = ?")) {
            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (SQLException e) {
           throw new RuntimeException(e);
        } 
    }

    @Override
    public void updatePasswordSalt(String name, String password, String salt) {	
        try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                   "UPDATE account SET password = ?, salt = ? WHERE name = ?")) {
                stmt.setString(1, password);
                stmt.setString(2, salt);
                stmt.setString(3, name);
                stmt.executeUpdate();
            } catch (SQLException e) {
               throw new RuntimeException(e);
            } 
    }
}
