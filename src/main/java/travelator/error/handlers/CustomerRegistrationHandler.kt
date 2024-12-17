package travelator.error.handlers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import travelator.error.DuplicateException
import travelator.error.ExcludedException
import travelator.error.IRegisterCustomers
import travelator.error.http.Request
import travelator.error.http.Response
import java.net.HttpURLConnection

class CustomerRegistrationHandler(private val registration: IRegisterCustomers) {
    private val objectMapper = ObjectMapper()
    fun handle(request: Request): Response {
        return try {
            val data = objectMapper.readValue(
                request.body,
                RegistrationData::class.java
            )
            val customer = registration.register(data)
            Response(
                HttpURLConnection.HTTP_CREATED,
                objectMapper.writeValueAsString(customer)
            )
        } catch (x: JsonProcessingException) {
            Response(HttpURLConnection.HTTP_BAD_REQUEST)
        } catch (x: ExcludedException) {
            Response(HttpURLConnection.HTTP_FORBIDDEN)
        } catch (x: DuplicateException) {
            Response(HttpURLConnection.HTTP_CONFLICT)
        } catch (x: Exception) {
            Response(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }
    }
}
