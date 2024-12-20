package travelator.marketing

import dev.forkhandles.result4k.*
import java.util.*


fun Sequence<String>.toHighValueCustomerReport(
    onErrorLine: (ParseFailure) -> Unit = {}
): Sequence<String> {
    val valuableCustomers = this
        .withoutHeader()
        .map { line ->
            line.toCustomerData().recover {
                onErrorLine(it)
                null
            }
        }
        .filterNotNull()
        .filter { it.score >= 10 }
        .sortedBy(CustomerData::score)
        .toList()
    return sequenceOf("ID\tName\tSpend") +
            valuableCustomers.map(CustomerData::outputLine) +
            valuableCustomers.summarised()
}

private fun Sequence<String>.withoutHeader() = drop(1)

private fun List<CustomerData>.summarised(): String =
    sumOf { it.spend }.let { total ->
        "\tTOTAL\t" + total.toMoneyString()
    }

fun String.toCustomerData(): Result<CustomerData, ParseFailure> =
    split("\t").let { parts ->
        parts
            .takeUnless { it.size < 4 }
            .asResultOr { NotEnoughFieldsFailure(this) }
            .flatMap { parts ->
                parts[3].toIntOrNull()
                    .asResultOr { ScoreIsNotAnIntFailure(this) }
                    .flatMap { score: Int ->
                        (if (parts.size == 4) 0.0
                        else parts[4].toDoubleOrNull())
                            .asResultOr { SpendIsNotADoubleFailure(this) }
                            .flatMap { spend ->
                                Success(
                                    CustomerData(
                                        id = parts[0],
                                        givenName = parts[1],
                                        familyName = parts[2],
                                        score = score,
                                        spend = spend
                                    )
                                )
                            }
                    }
            }
    }

private val CustomerData.outputLine: String
    get() = "$id\t$marketingName\t${spend.toMoneyString()}"

private fun Double.toMoneyString() = this.formattedAs("%#.2f")

private fun Any?.formattedAs(format: String) = String.format(format, this)

private val CustomerData.marketingName: String
    get() = "${familyName.uppercase(Locale.getDefault())}, $givenName"

sealed class ParseFailure(open val line: String)
data class NotEnoughFieldsFailure(override val line: String) :
        ParseFailure(line)
data class ScoreIsNotAnIntFailure(override val line: String) :
        ParseFailure(line)
data class SpendIsNotADoubleFailure(override val line: String) :
        ParseFailure(line)