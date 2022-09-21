import java.io.Serializable;

public class Account implements Serializable {
    String FirstName;
    String LastName;
    int PIN;
    String  CC_NUMBER;
    float DEPOSIT = 0.00F;

    public Account(String FirstName, String LastName, int PIN, String CC_NUMBER, float DEPOSIT) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.PIN = PIN;
        this.CC_NUMBER = CC_NUMBER;
        this.DEPOSIT += DEPOSIT;
    }

    public float getDEPOSIT() {
        return DEPOSIT;
    }

    public String getCC_NUMBER() {
        return CC_NUMBER;
    }

    public int getPIN() {
        return PIN;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
    public void addDeposit(float depo){
        DEPOSIT += depo;
    }
    public void withdrawDeposit(float depo){
        DEPOSIT -= depo;
    }
}
