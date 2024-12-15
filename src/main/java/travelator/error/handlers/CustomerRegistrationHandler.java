package travelator.error.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import travelator.error.Customer;
import travelator.error.DuplicateException;
import travelator.error.ExcludedException;
import travelator.error.IRegisterCustomers;
import travelator.error.http.Request;
import travelator.error.http.Response;

import static java.net.HttpURLConnection.*;


public class CustomerRegistrationHandler {
    private final IRegisterCustomers registration;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public CustomerRegistrationHandler(IRegisterCustomers registration) {
        this.registration = registration;
    }
    public Response handle(Request request) {
        try {
            RegistrationData data = objectMapper.readValue(
                request.getBody(),
                RegistrationData.class
            );
            Customer customer = registration.register(data);
            return new Response(HTTP_CREATED,
                objectMapper.writeValueAsString(customer)
            );
        } catch (JsonProcessingException x) {
            return new Response(HTTP_BAD_REQUEST);
        } catch (ExcludedException x) {
            return new Response(HTTP_FORBIDDEN);
        } catch (DuplicateException x) {
            return new Response(HTTP_CONFLICT);
        } catch (Exception x) {
            return new Response(HTTP_INTERNAL_ERROR);
        }
    }
}
