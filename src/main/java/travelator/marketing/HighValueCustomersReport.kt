package travelator.marketing

import java.io.BufferedReader
import java.io.IOException
import java.io.Reader
import java.io.Writer
import java.util.*
import java.util.stream.Collectors


@Throws(IOException::class)
fun generate(reader: Reader?, writer: Writer) {
    val valuableCustomers = BufferedReader(reader).lines()
            .skip(1) // header
            .map { line: String -> line.toCustomerData() }
            .filter { customerData: CustomerData -> customerData.score >= 10 }
            .sorted(Comparator.comparing { customerData: CustomerData -> customerData.score })
            .collect(Collectors.toList())
    writer.append("ID\tName\tSpend\n")
    for (customerData in valuableCustomers) {
        writer.append(lineFor(customerData)).append("\n")
    }
    writer.append(valuableCustomers.toSummary())
}

private fun List<CustomerData>.toSummary(): String =
    sumOf { it.spend }.let { total ->
        "\tTOTAL\t" + total.toMoneyString() }



fun String.toCustomerData(): CustomerData =
        split("\t").let { parts ->
            CustomerData(
                    id = parts[0],
                    givenName = parts[1],
                    familyName = parts[2],
                    score = parts[3].toInt(),
                    spend = if (parts.size == 4) 0.0 else parts[4].toDouble()
            )
        }

private fun lineFor(customer: CustomerData): String {
    return customer.id + "\t" + customer.marketingName + "\t" +
            customer.spend.toMoneyString()
}

private fun Double.toMoneyString() = this.formattedAs("%#.2f")

private fun Any?.formattedAs(format: String) = String.format(format, this)

private val CustomerData.marketingName: String
    get() = "${familyName.uppercase(Locale.getDefault())}, $givenName"

