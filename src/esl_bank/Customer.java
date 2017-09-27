package esl_bank;

/*
 * CSC-225 - Advanced JAVA Programming
 * Project 1 - ES&L Bank program
 * Class Description : This is the Customer object class of the ESL_Bank program.
 * Author            : Benjamin Kleynhans
 * Date              : September 24, 2017
 * Filename          : BankDriver.java
 */

import java.text.DecimalFormat;
import java.util.*;

/**
 * <h1>
 * CSC-225 - Advanced JAVA Programming - ESL_Bank Customer class
 * </h1>
 * <p>
 * This is the Customer object class of the ESL_Bank program.
 * </p>
 *
 * @version 1.0
 * @since September 24, 2017
 */

/*
 * Start of Customer class
 */

public final class Customer implements Comparable<Customer> {

    private String name = new String();
    private String idNumber = new String();
    private String phoneNumber = new String();
    
    private double balance = 0;

    private static final double INTEREST = 0.045;                                               // interest in percentage divided by 100.  ie: (4.5/100)
    private static final double TRANSACTION_FEE = 1.50;                                         // fee paid per transaction in dollar amount

    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("#,##0.00");
    
    private static int customerCounter;
    
    /**
     * Default Empty Constructor
     */
    
    public Customer() {

        this.setName("DefaultUser");
        this.setID("000000");
        this.setPhoneNumber("000-000-0000");
        this.setBalance(0.00);

        addCustomer();

    }
    
    /**
     * Alternate Constructor accepting customer name, ID number, balance and phone number
     *
     * @param nameInput             Customer name
     * @param idNumberInput         Customer id number
     * @param balanceInput          Customer opening balance
     * @param phoneNumberInput      Customer phone number
     */

    public Customer(String nameInput, String idNumberInput, double balanceInput, String phoneNumberInput) {

        this.setName(nameInput);
        this.setID(idNumberInput);
        this.setPhoneNumber(phoneNumberInput);
        this.setBalance(balanceInput);

        addCustomer();

    }
    
    /*
     * Add one to the customer counter
     */
    
    private static void addCustomer() {
    
        customerCounter++;
    
    }
    
    /*
     * Remove one from the customer counter
     */
    
    private static void removeCustomer() {
    
        customerCounter--;
    
    }
    
    /**
     * Returns a value representing the number of customers in the database
     *
     * @return customerCounter - Number of customer objects
     */
    
    public static int getNumberOfCustomers() {
    
        return customerCounter;
    
    }

    /**
     * Set the name of the customer
     * 
     * @param nameInput Name of customer being created
     */
    
    public void setName(String nameInput) {

        this.name = nameInput;

    }

    /**
     * Return the name of the customer
     * 
     * @return name
     */
    
    public String getCustomerName() {

        return this.name;

    }

    /**
     * Set the id number of the customer
     * 
     * @param idNumberInput ID Number of customer being created
     */
    
    public void setID(String idNumberInput) {

        this.idNumber = idNumberInput;

    }

    /**
     * Return the id number of the customer
     * 
     * @return idNumber
     */
    
    public String getCustomerNumber() {

        return this.idNumber;

    }

    /**
     * Set the phone number of the customer
     * 
     * @param inputPhoneNumber Phone number of customer being created
     */
    
    public void setPhoneNumber(String inputPhoneNumber) {

        this.phoneNumber = inputPhoneNumber;

    }

    /**
     * Return the phone number of the customer
     *
     * @return phoneNumber
     */
    
    public String getPhoneNumber() {

        return this.phoneNumber;

    }

    /**
     * Set the balance of the customer
     *
     * @param inputBalance Opening balance of user being created
     */
    
    public void setBalance(double inputBalance) {

        this.balance = inputBalance;

    }

    /**
     * Return the balance of the customer
     *
     * @return balance
     */
    
    public double getBalance() {

        return this.balance;

    }

    /**
     * Add interest to the balance, based on the value of the INTEREST constant
     */
    
    public void addInterest() {

        this.balance = (this.balance * (1 + INTEREST));                         // Add interest to balance

    }

    /**
     * Subtract transaction fee and withdrawal amount from balance
     *
     * @param withdrawalAmount  Amount of money to be withdrawn from account
     * @return balance Balance of account after money is withdrawn
     */
    
    public double withdraw(double withdrawalAmount) {

        this.balance -= TRANSACTION_FEE;                                        // Subtract transaction fee
        this.balance -= withdrawalAmount;                                       // Subtract withdraw amount

        return this.balance;

    }

    /**
     * Add deposit amount to balance
     *
     * @param depositAmount Amount to be deposited into account
     * @return Balance of account after deposit
     */
    public double deposit(double depositAmount) {

        this.balance += depositAmount;                                          // Add deposit to balance

        return this.balance;

    }

    /**
     * Remove a customer from the arraylist
     *
     * @param custArray Arraylist containing all the customer objects
     * @param name name of the customer that needs to be deleted
     */
    
    public static void deleteCustomer(List<Customer> custArray, String name) {

        custArray.remove(findIndex(custArray, name));                           // Remove customer from arraylist

        removeCustomer();

    }
    
    /**
     * Sort the arraylist of customers
     *
     * @param custArray Arraylist containing all the customer objects
     */
    
    public static void nameSort(List<Customer> custArray) {
    
        Collections.sort(custArray);                                            // Sort arraylist
    
    }

    /**
     * Find the index of a customer based on the customer name
     *
     * @param custArray Arraylist containing all the customer objects
     * @param name name of the customer that is being sought
     * 
     * @return the arraylist index if the customer is found, or -1 if the customer is not found
     */
    
    public static int findIndex(List<Customer> custArray, String name) {

        if (custArray.isEmpty()) {

            return -1;                                                          // Return -1 if the arraylist is empty

        } else {

            for (int i = 0; i < custArray.size(); i++) {
                if (custArray.get(i).getCustomerName().equals(name)) {
                    return i;                                                   // Return index of customer if found
                }
            }
        }

        return -1;

    }

    /** Overriding the compareTo method to sort arraylist objects
     * 
     * @param customer Name of customer that is being compared for alphabetical ordering
     * @return 0 if strings are equal, alternatively a +1 or -1 is returned
     */
    
    @Override
    public int compareTo(Customer customer) {
        
        return (this.getCustomerName()).compareTo(customer.getCustomerName());  // Return result of customer name comparison
        
    }
    
    /**
     * Returns a summary of the information in the object
     * 
     * @return toString String value of records
     */

    @Override
    public String toString() {

        return String.format(                                                   // Display contents of object using toString method
                "<html><tr>"
                + "<td align='left' width='300'>%s</td>"
                + "<td align='right' width='200'>%s</td>"
                + "<td align='right' width='200'>%s</td>"
                + "<td align='right' width='200'>%s</td>"
                + "</tr><br>",
                this.getCustomerName(),
                this.getCustomerNumber(),
                CURRENCY_FORMAT.format(this.getBalance()),
                this.getPhoneNumber(),
                "</html>"
        );
    }
}
