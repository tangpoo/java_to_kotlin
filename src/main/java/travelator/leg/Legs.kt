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

    fun longestLegOver(legs: List<Leg>, duration: Duration): Leg? =
        legs.maxByOrNull(Leg::plannedDuration)?.takeIf { longestLeg ->
            longestLeg.plannedDuration > duration
        }


private fun Leg.isLongerThan(duration: Duration) =
        plannedDuration > duration

}
