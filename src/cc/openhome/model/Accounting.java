package cc.openhome.model;

public class Accounting {
    private boolean type;     		//true:income false:expense
    private String date;      		
    private String category;  		
    private boolean accounttype;	//true:cash false:bank
    private int amount;   			
    private String notes;  			
    private String name;  		
    
    public Accounting(boolean _type,String _date,String _category,boolean _accounttype,int _amount,String _notes,String _name) {
        this.type=_type;
        this.date=_date;
        this.category=_category;
        this.accounttype=_accounttype;
        this.amount=_amount;
        this.notes=_notes;
        this.name=_name;
    }
    
    public boolean getType() {
    	return type;
    }
    
	public String getDate() {
        return date;
    }
    
	public String getCategory() {
        return category;
    }
	
    public boolean getAccounttype() {
    	return accounttype;
    }
    
    public int getAmount() {
    	return amount;
    }
    
	public String getNotes() {
        return notes;
    }
    
	public String getName() {
        return name;
    }

}
