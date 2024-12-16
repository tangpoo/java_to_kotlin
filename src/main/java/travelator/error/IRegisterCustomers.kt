package travelator.error;

import travelator.error.handlers.RegistrationData;

public interface IRegisterCustomers {
    Customer register(RegistrationData data)
        throws ExcludedException, DuplicateException;
}
