package cc.openhome.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

public class AccountingDAOjdbcImpl {
    private DataSource dataSource;
    
    public AccountingDAOjdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    
    public int getBudget(String name) {
    	try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT BUDGET FROM ACCOUNT WHERE NAME = ?")) {
    		stmt.setString(1, name);
    		ResultSet rs = stmt.executeQuery();
            
    		int budget = 0;
            while(rs.next()) {
            	budget=rs.getInt(1);
            }
            return budget;
            
    	}catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int getMonthCost(String name) {	
    	Date month=new Date();						
    	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMM")
    	
    	try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT AMOUNT, ID FROM accounting WHERE name = ? and date LIKE ? AND TYPE = ?")) {
    		stmt.setString(1, name);
            stmt.setString(2, dateFormat.format(month)+'%');	//date = current month
            stmt.setBoolean(3, false);							//type: expense
            
    		ResultSet rs = stmt.executeQuery();
            int amount = 0;
            while(rs.next()) {            	
            	amount += rs.getInt(1);
            }
            return amount;
            
    	}catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int findTodayAmount(String name) {	
    	Date date=new Date();
    	SimpleDateFormat dateFormat=new SimpleDateFormat("YYYYMMdd");
//    	System.out.println(dateFormat.format(date));
    	
    	
    	try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT TYPE, AMOUNT, ID FROM accounting WHERE name = ? and date = ?")) {
    		stmt.setString(1, name);
            stmt.setString(2, dateFormat.format(date));
    		ResultSet rs = stmt.executeQuery();
            
            
            int amount = 0;
            while(rs.next()) {
            	if (rs.getBoolean(1)==true) {	//income
            		amount += rs.getInt(2);
            	} else {						//expense
            		amount -= rs.getInt(2);
            	}
            }
            return amount;
            
    	}catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public int findAllCash(String name) {
    	try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT TYPE, AMOUNT FROM accounting WHERE name = ? and accounttype = ?")) {
    		stmt.setString(1, name);
    		stmt.setBoolean(2, true);
            ResultSet rs = stmt.executeQuery();
            
            int amount = 0;
            while(rs.next()) {
        		if (rs.getBoolean(1)==true) {		//income
            		amount += rs.getInt(2);
            	} else {							
            		amount -= rs.getInt(2);
            	}
            	
            }
//            System.out.println("findAllCash：  "+amount);
            return amount;
            
    	}catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public int findAllDeposit(String name) {
    	try(Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT TYPE, AMOUNT FROM accounting WHERE name = ? and ACCOUNTTYPE = ?")) {
    		stmt.setString(1, name);
    		stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            
            int amount = 0;
            while(rs.next()) {
        		if (rs.getBoolean(1)==true) {	//income
            		amount += rs.getInt(2);
            	} else {					
            		amount -= rs.getInt(2);
            	}
            }
            return amount;
            
    	}catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int findAllAmount(String name) {
    	return findAllCash(name)+findAllDeposit(name);
    }
    
    public void createAccounting(Accounting accting) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
               "INSERT INTO ACCOUNTING (TYPE, DATE, CATEGORY, ACCOUNTTYPE, AMOUNT, NOTES, NAME) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?)")){
            System.out.println("category:  "+accting.getCategory());
            
        	stmt.setBoolean(1, accting.getType());
        	stmt.setString(2, accting.getDate());
        	stmt.setString(3, accting.getCategory());
        	stmt.setBoolean(4, accting.getAccounttype());
        	stmt.setInt(5, accting.getAmount());
        	stmt.setString(6, accting.getNotes());
        	stmt.setString(7, accting.getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        } 
    }
    
}
