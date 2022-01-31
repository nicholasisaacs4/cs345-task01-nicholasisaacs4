package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);

        BankAccount bankAccount02 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount02.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        BankAccount bankAccount01 = new BankAccount("a@b.com", 500);
        assertThrows(InsufficientFundsException.class, () -> bankAccount01.withdraw(600));
        BankAccount bankAccount02 = new BankAccount("a@b.com", 600); 
        bankAccount02.withdraw(300);
        assertEquals(300, bankAccount02.getBalance(), 0.001);
        bankAccount02.withdraw(300);
        assertEquals(0, bankAccount02.getBalance(), 0.001);
        //Exception Tests
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(8.333));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-300.432));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid("a@b.com"));
        assertTrue(BankAccount.isEmailValid("email@example.com"));
        assertTrue(BankAccount.isEmailValid("firstname.lastname@example.com"));
        assertTrue(BankAccount.isEmailValid("email@example.museum"));
        assertTrue(BankAccount.isEmailValid("firstname-lastname@example.com"));

        assertFalse(BankAccount.isEmailValid("a@")); // should be False
        assertFalse(BankAccount.isEmailValid("a@d")); // should be False
        assertFalse(BankAccount.isEmailValid("@")); // should be False
        assertFalse(BankAccount.isEmailValid(".email@example.com")); // should be False
        assertFalse(BankAccount.isEmailValid("email..email@example.com")); // should be False
        assertFalse(BankAccount.isEmailValid("email@example.com (Joe Smith)")); // should be False
        assertFalse(BankAccount.isEmailValid("email@example")); // should be False
        assertFalse(BankAccount.isEmailValid("")); // empty string
        
    }

    @Test 
    void amountValidTest(){
        //true cases
        assertTrue(BankAccount.isAmountValid(13.0));
        assertTrue(BankAccount.isAmountValid(3.1));
        assertTrue(BankAccount.isAmountValid(5.5));
        assertTrue(BankAccount.isAmountValid(9.32));
        //false cases 
        assertFalse(BankAccount.isAmountValid(-18));
        assertFalse(BankAccount.isAmountValid(-945));
        assertFalse(BankAccount.isAmountValid(80.567));
        assertFalse(BankAccount.isAmountValid(3.2222));
        

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        //doubles will throw exceptions 
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(21.333));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-40));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-15.848));
    }

}