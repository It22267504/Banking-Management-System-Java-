package mngLoanApp;

public class Application {
	
    private int id;
    private String name;
    private String accountNumber;
    private String phoneNumber;
    private String email;
    private String photo;
    private String nic;
    private String type;
    private String amount;
    private String years;

    public Application(int id, String name, String accountNumber, String phoneNumber, String email, String photo, String nic, String type, String amount , String years) {
        super();
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.photo = photo;
        this.nic = nic;
        this.type = type;
        this.amount = amount;
        this.years = years;
        
    }

    public Application(String name, String accountNumber, String phoneNumber, String email, String photo, String nic, String type, String amount , String years) {
        super();
        this.name = name;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.photo = photo;
        this.nic = nic;
        this.type = type;
        this.amount = amount;
        this.years = years;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getNic() {
        return nic;
    }
    
    public String getType() {
        return type;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public String getYears() {
        return years;
    }
    
}
