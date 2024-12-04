package travelator.event

import java.util.stream.Collectors
import java.util.stream.Collectors.groupingBy
import kotlin.streams.asSequence

class MarketingAnalytics(
    private val eventStore: EventStore
) {
    fun averageNumberOfEventsPerCompletedBooking(
        timeRange: String
    ): Double {
        val eventsForSuccessfulBookings = eventStore
                .queryAsSequence("type=CompletedBooking&timerange=$timeRange")
                .flatMap { event ->
                    val interactionId = event["interactionId"] as String?
                    eventStore
                        .queryAsSequence("interactionId=$interactionId")
                }
        val bookingEventsByInteractionId = eventsForSuccessfulBookings
            .groupBy { event ->
                event["interactionId"] as String
            }
        return bookingEventsByInteractionId.values.averageBy {it.size}
    }

    private fun <T> Collection<T>.averageBy(selector: (T) -> Int): Double =
        sumOf(selector) / size.toDouble()
}

fun EventStore.queryAsSequence(query: String) =
    this.queryAsStream(query).asSequence()
