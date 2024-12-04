package travelator.event

import kotlin.streams.asSequence

class MarketingAnalytics(
    private val eventStore: EventStore
) {
    fun averageNumberOfEventsPerCompletedBooking(
        timeRange: String
    ): Double = eventStore
        .queryAsSequence("type=CompletedBooking&timerange=$timeRange")
        .allEventsInSameInteractions()
        .groupBy(Event::interactionId)
        .values
        .averageBy {it.size}

    private fun Sequence<MutableMap<String, Any>>.allEventsInSameInteractions(): Sequence<MutableMap<String, Any>> = flatMap { event ->
            val interactionId = event["interactionId"] as String
            eventStore
                .queryAsSequence("interactionId=$interactionId")
        }

    private fun <T> Collection<T>.averageBy(selector: (T) -> Int): Double =
        sumOf(selector) / size.toDouble()
}

fun EventStore.queryAsSequence(query: String) =
    this.queryAsStream(query).asSequence()

typealias Event = Map<String, Any?>

val Event.interactionId: String? get() =
    this["interactionId"] as? String