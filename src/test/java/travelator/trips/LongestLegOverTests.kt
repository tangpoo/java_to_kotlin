package travelator.trips

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import travelator.leg.Leg
import travelator.leg.Legs.longestLegOver
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.ThreadLocalRandom

class LongestLegOverTests {
    private val legs: List<Leg> = java.util.List.of(
            leg("one hour", Duration.ofHours(1)),
            leg("one day", Duration.ofDays(1)),
            leg("two hours", Duration.ofHours(2))
    )
    private val oneDay: Duration = Duration.ofDays(1)

    @Test
    fun `is absent when no legs`() {
        assertNull(longestLegOver(emptyList(), Duration.ZERO))
    }

    @Test
    fun `is absent when no legs long enough`() {
        assertNull(longestLegOver(legs, oneDay))
    }

    @Test
    fun `is longest leg when one match`() {
        assertEquals(
                "one day",
                longestLegOver(legs, Duration.ofMillis(1))
                        !!.description
        )
    }

    @Test
    fun `is longest leg when more than one match`() {
        assertEquals(
                "one day",
                longestLegOver(legs, Duration.ofMinutes(59))
                        ?.description
        )
    }

    companion object {
        private fun leg(description: String, duration: Duration): Leg {
            val start = ZonedDateTime.ofInstant(
                    Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt().toLong()),
                    ZoneId.of("UTC"))
            return Leg(description, start, start.plus(duration))
        }
    }
}
