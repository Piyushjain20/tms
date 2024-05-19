package customer;

import exception.IncorrectPasswordException;
import exception.InvalidFormatException;
import exception.InvalidLineException;
import exception.NotFoundException;
import utility.Utility;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static final int MIN_REQUIRED_DATA_POINTS = 3;
    private String customerFilePath;
    private static Map<Integer, CustomerEntity> currentCustomers = new HashMap<>();

    public CustomerService(String customerFilePath) {
        this.customerFilePath = customerFilePath;
    }

    public String getCustomerFilePath() {
        return customerFilePath;
    }

    public Map<Integer, CustomerEntity> getCurrentCustomers() {
        return currentCustomers;
    }

    private CustomerEntity parseCustomerRow(String[] customerFields) throws InvalidLineException, InvalidFormatException {
        if(customerFields.length < MIN_REQUIRED_DATA_POINTS){
            throw new InvalidLineException("Invalid Customer Files.");
        }
        try{
            Integer id = Integer.parseInt(customerFields[0]);
            return new CustomerEntity(id, customerFields[1], customerFields[2]);
        } catch (NumberFormatException e){
            throw new InvalidFormatException("Customer Id is in incorrect format.");
        }
    }
    public void readAllCustomers() throws FileNotFoundException {
        if(!Utility.validateFilePath(this.customerFilePath)) {
            throw new FileNotFoundException(String.format("%s (No such file or directory)", customerFilePath));
        }
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(customerFilePath));
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                try{
                    CustomerEntity c = parseCustomerRow(fields);
                    if(c != null) currentCustomers.put(c.getId(), c);
                }catch (InvalidLineException | InvalidFormatException e){
                    System.out.println(e.getMessage() + "  Skipping this line.");
                }
            }
        }catch (IOException e){
            System.out.println("My Exception in reading customer file"+ e.getMessage());
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("My Exception in closing reader connection"+e.getMessage());
                }
            }
        }

    }
    public CustomerEntity loginCustomer(Integer customerId, String password) throws NotFoundException, IncorrectPasswordException {
        if(currentCustomers.containsKey(customerId)){
            if(password.equals(currentCustomers.get(customerId).getPassword())){
                return currentCustomers.get(customerId);
            }
            throw new IncorrectPasswordException("Incorrect Password. Terminating Program");
        }
        throw new NotFoundException("Customer does not exist. Terminating Program");
    }
    private String[] stringifyCustomer(CustomerEntity customer){
        return new String[]{customer.getId().toString(), customer.getName(), customer.getPassword()};
    }
    public CustomerEntity createCustomer(String name, String password){
        Integer customerId = Collections.max(currentCustomers.keySet()) + 1 ;
        CustomerEntity createdCustomer = new CustomerEntity(customerId, name, password);
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(this.customerFilePath, true));
            writer.write(String.join("," , stringifyCustomer(createdCustomer)));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("My Exception in writing customer file"+ e.getMessage());
        }finally {
            if(writer != null){
                try {
                    writer.close();
                }catch (IOException e){
                    System.out.println("My Exception in closing writer connection"+e.getMessage());
                }
            }
        }
        currentCustomers.put(customerId, createdCustomer);
        return createdCustomer;
    }
}
