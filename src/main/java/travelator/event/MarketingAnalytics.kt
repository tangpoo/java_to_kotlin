package travelator.event

import kotlin.streams.asSequence

class MarketingAnalytics(
    private val eventStore: EventStore
) {
    fun averageNumberOfEventsPerCompletedBooking(
        timeRange: String
    ): Double {
        return allEventsInSameInteractions(
            eventStore
                .queryAsSequence("type=CompletedBooking&timerange=$timeRange")
        )
            .groupBy { event ->
                event["interactionId"] as String
            }
            .values
            .averageBy {it.size}
    }

    private fun allEventsInSameInteractions(
        sequence: Sequence<MutableMap<String, Any>>
                                            ): Sequence<MutableMap<String, Any>> = sequence
        .flatMap { event ->
            val interactionId = event["interactionId"] as String
            eventStore
                .queryAsSequence("interactionId=$interactionId")
        }

    private fun <T> Collection<T>.averageBy(selector: (T) -> Int): Double =
        sumOf(selector) / size.toDouble()
}

fun EventStore.queryAsSequence(query: String) =
    this.queryAsStream(query).asSequence()
