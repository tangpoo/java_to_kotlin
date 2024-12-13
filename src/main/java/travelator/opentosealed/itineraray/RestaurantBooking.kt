package travelator.opentosealed.itineraray

import travelator.opentosealed.Id
import travelator.opentosealed.Location
import travelator.opentosealed.geo.PointOverlay
import travelator.opentosealed.geo.StandardIcons
import travelator.opentosealed.money.Money
import java.time.ZonedDateTime

data class RestaurantBooking(
    override val id: Id<RestaurantBooking>,
    val location: Location,
    val time: ZonedDateTime
) : ItineraryItem {
    override val description get() = location.userReadableName
    override val costs get() = emptyList<Money>()
    override val mapOverlay
        get() =
            PointOverlay(
                id = id,
                position = location.position,
                text = location.userReadableName,
                icon = StandardIcons.RESTAURANT
            )
}