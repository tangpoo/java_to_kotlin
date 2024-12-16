package travelator.error

import travelator.error.handlers.RegistrationData

interface IRegisterCustomers {
    @Throws(ExcludedException::class, DuplicateException::class)
    fun register(data: RegistrationData): Customer
}
