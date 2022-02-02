package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if(isAmountValid(amount)){
            if(amount <= balance){
                balance -= amount;
            }
            else {
                throw new InsufficientFundsException("Not enough money");
            }
        }
        else{
            throw new InsufficientFundsException("Not a valid amount");
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('.') == 0){
            return false;
        }
        if (email.length() <= 3){
            return false;
        }
        if (email.indexOf('.') == 0) {
            return false;
        }
        if (email.charAt(email.indexOf('.')) == email.charAt(email.indexOf('.') + 1)) { //checks for multiple periods in our email inputs and returns false if two are present
            return false;
        }
        if (email.indexOf('(') > email.indexOf('@')){
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isAmountValid(double amount){

        if(amount < 0){
            return false;
        }
        if((amount*100)% 1 != 0){//detrmines weather the values has correct decimal places and retruns false if it doesnt 
            return false;
        }
        else{
            return true;
        }
    }

     /**
     * @post if the amount is valid we deposit an amount into balance 
     */
    public void deposit(double amount) throws InsufficientFundsException{
        //if amount is valid then we add to our origial balance 
        if(isAmountValid(amount)){ 
            balance += amount;
        }
        else{
            throw new InsufficientFundsException("not valid");
        }
    }
    
     /**
     * @post if amount is valid the we withdraw amount from account and deposit into another account 
     */
    public void Transfer(BankAccount bank, double amount) throws InsufficientFundsException{
        //if amount is valid then we can transfer 
        if(isAmountValid(amount)){
            withdraw(amount);
            bank.deposit(amount);
        }
        else{
            throw new InsufficientFundsException("Amount Not Valid");
        }
    }
}