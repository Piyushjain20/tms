package customer;

import exception.InvalidFormatException;
import exception.InvalidLineException;
import utility.Utility;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomerDB {
    private static final int MIN_REQUIRED_DATA_POINTS = 3;
    public static String  customerFilePath;
    public static final Map<Integer, CustomerEntity> currentCustomers = new HashMap<>();

    private static CustomerEntity parseCustomerRow(String[] customerFields) throws InvalidLineException, InvalidFormatException {
        if(customerFields.length < MIN_REQUIRED_DATA_POINTS){
            throw new InvalidLineException("Invalid Customer Files.");
        }
        if(!Utility.isParsableToInteger(customerFields[0])) {
            throw new InvalidFormatException("Customer Id is in incorrect format.");
        }
        return new CustomerEntity(Integer.parseInt(customerFields[0]), customerFields[1], customerFields[2]);
    }
    public static void readAllCustomers() throws FileNotFoundException {
        if(!Utility.validateFilePath(customerFilePath)) {
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
                    currentCustomers.put(c.getId(), c);
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
                    System.out.println("My Exception in closing customer reader connection"+e.getMessage());
                }
            }
        }
    }

    private static Integer getNextNewCustomerId(){
        return Collections.max(currentCustomers.keySet()) + 1;
    }
    private static String[] stringifyCustomer(CustomerEntity customer){
        return new String[]{customer.getId().toString(), customer.getName(), customer.getPassword()};
    }

    public static CustomerEntity addToCustomerDB(CustomerEntity customer){
        customer.setId(getNextNewCustomerId());
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(customerFilePath, true));
            writer.write(String.join("," , stringifyCustomer(customer)));
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
        currentCustomers.put(customer.getId(), customer);
        return customer;
    }

    public static boolean hasCustomerWithId(Integer customerId){
        return currentCustomers.containsKey(customerId);
    }

    public static CustomerEntity getCustomerWithId(Integer customerId){
        return currentCustomers.get(customerId);
    }
}
