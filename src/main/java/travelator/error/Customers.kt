package travelator.error

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import java.util.*

interface Customers {
    fun add(name: String, email: String):Result<Customer, DuplicateException>
    fun find(id: String): Optional<Customer>
}