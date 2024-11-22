package travelator.leg

import java.time.Duration
import java.util.*

object Legs {

    @JvmStatic
    fun findLongestLegOver(
            legs: List<Leg>,
            duration: Duration
    ): Optional<Leg> {
        return Optional.ofNullable(legs.longestLegOver(duration))
    }

    fun List<Leg>.longestLegOver(duration: Duration): Leg? {
        val longestLeg = maxByOrNull(Leg::plannedDuration)

        return when {
            longestLeg == null -> null
            longestLeg.plannedDuration > duration -> longestLeg
            else  -> null
        }
    }


private fun Leg.isLongerThan(duration: Duration) =
        plannedDuration > duration

}
