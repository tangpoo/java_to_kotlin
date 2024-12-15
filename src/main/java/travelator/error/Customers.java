package travelator.error;

import java.util.Optional;

public interface Customers {
    Customer add(String name, String email) throws DuplicateException;
    Optional<Customer> find(String id);
}