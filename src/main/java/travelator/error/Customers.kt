package travelator.error

import java.util.*

interface Customers {
    @Throws(DuplicateException::class)
    fun add(name: String, email: String): Customer
    fun find(id: String): Optional<Customer>
}