package travelator.opentosealed.itineraray

import travelator.opentosealed.Id
import travelator.opentosealed.Location
import travelator.opentosealed.geo.PointOverlay
import travelator.opentosealed.geo.StandardIcons
import travelator.opentosealed.money.Money
import java.time.Period
import java.time.ZonedDateTime

data class Accommodation(
    override val id: Id<Accommodation>,
    val location: Location,
    val checkInFrom: ZonedDateTime,
    val checkOutBefore: ZonedDateTime,
    val pricePerNight: Money
) : ItineraryItem {
    val nights = Period.between(checkInFrom.toLocalDate(), checkOutBefore.toLocalDate()).days
    val totalPrice: Money = pricePerNight * nights
    override val description
        get() = "$nights nights at ${location.userReadableName}"
    override val costs
        get() = listOf(totalPrice)
    override val mapOverlay
        get() = PointOverlay(
            id = id,
            position = location.position,
            text = location.userReadableName,
            icon = StandardIcons.HOTEL
        )
}