package travelator.leg

import java.time.Duration
import java.util.*

object Legs {

    @JvmStatic
    fun findLongestLegOver(
            legs: List<Leg>,
            duration: Duration
    ): Optional<Leg> {
        return Optional.ofNullable(longestLegOver(legs, duration))
    }

    fun longestLegOver(legs: List<Leg>, duration: Duration): Leg? {
        val longestLeg = legs.maxByOrNull(Leg::plannedDuration)

        return when {
            longestLeg == null -> null
            longestLeg.plannedDuration > duration -> longestLeg
            else -> null
        }
    }
}