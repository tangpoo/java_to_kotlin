package travelator.error

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.orThrow
import travelator.error.handlers.RegistrationData

class CustomerRegistration(
    private val customers: Customers,
    private val exclusionList: ExclusionList
) : IRegisterCustomers {
    @Throws(ExcludedException::class, DuplicateException::class)
    override fun register(data: RegistrationData): Customer {
        when {
            exclusionList.exclude(data) -> throw ExcludedException()
            else -> return customers.addToo(data.name, data.email).orThrow()
        }
    }
}
