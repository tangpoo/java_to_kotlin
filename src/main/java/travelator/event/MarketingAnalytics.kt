package travelator.event

import java.util.function.Function
import java.util.stream.Collectors

class MarketingAnalytics(
    private val eventStore: EventStore
) {
    fun averageNumberOfEventsPerCompletedBooking(
        timeRange: String
    ): Double {
        val eventsForSuccessfulBookings =
            eventStore
                .queryAsStream("type=CompletedBooking&timerange=$timeRange")
                .flatMap { event ->
                    val interactionId = event["interactionId"] as String?
                    eventStore.queryAsStream("interactionId=$interactionId")
                }
        val bookingEventsByInteractionId =
            eventsForSuccessfulBookings.collect(
                Collectors.groupingBy { event -> event["interactionId"] as String? }
            )
        val value = bookingEventsByInteractionId.values
        return value.sumOf { it.size } / value.size.toDouble()
    }
}
