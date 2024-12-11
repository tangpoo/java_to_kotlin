package travelator.interfacetofun.domain

interface DistanceCalculator {
    fun distanceInMetersBetween(
        start: Location,
        end: Location
    ): Int
    fun travelTimeInSecondsBetween(
        start: Location,
        end: Location
    ): Int
}