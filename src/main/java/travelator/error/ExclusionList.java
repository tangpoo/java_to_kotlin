package travelator.error;

import travelator.error.handlers.RegistrationData;

public interface ExclusionList {
    boolean exclude(RegistrationData registrationData);
}
