package customer;

import exception.IncorrectPasswordException;
import exception.NotFoundException;

public class CustomerService {

    public CustomerEntity loginCustomer(Integer customerId, String password) throws NotFoundException, IncorrectPasswordException {
        if(CustomerDB.hasCustomerWithId(customerId)){
            CustomerEntity currentCustomer = CustomerDB.getCustomerWithId(customerId);
            if(password.equals(currentCustomer.getPassword())){
                return currentCustomer;
            }
            throw new IncorrectPasswordException("Incorrect Password. Terminating Program");
        }
        throw new NotFoundException("Customer does not exist. Terminating Program");
    }

    public CustomerEntity createCustomer(String name, String password){
        return CustomerDB.addToCustomerDB(new CustomerEntity(name, password));
    }
}
