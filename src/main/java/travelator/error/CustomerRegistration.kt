package travelator.error

import dev.forkhandles.result4k.*
import travelator.error.handlers.RegistrationData

class CustomerRegistration(
    private val customers: Customers,
    private val exclusionList: ExclusionList
) : IRegisterCustomers {
    override fun register(data: RegistrationData): Result<Customer, RegistrationProblem> =
        when {
            exclusionList.exclude(data) -> Failure(Excluded)
            else -> customers.add(data.name, data.email)
                .mapFailure { exception: DuplicateException ->
                    Duplicate(exception.message)
                }
        }
}
