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
        val longestLeg: Leg? = legs.maxByOrNull(Leg::plannedDuration)
        return if (longestLeg != null && longestLeg.plannedDuration > duration)
            longestLeg
        else
            null
    }


    private fun Leg.isLongerThan(duration: Duration) =
            plannedDuration > duration

}
