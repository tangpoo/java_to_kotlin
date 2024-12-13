package travelator.opentosealed.itineraray

import travelator.opentosealed.Id
import travelator.opentosealed.Location
import travelator.opentosealed.geo.PointOverlay
import travelator.opentosealed.geo.StandardIcons
import travelator.opentosealed.money.Money

data class Attraction(
    override val id: Id<Attraction>,
    val location: Location,
    val notes: String
) : ItineraryItem {
    override val description
        get() =
            location.userReadableName
    override val costs
        get() =
            emptyList<Money>()
    override val mapOverlay
        get() =
            PointOverlay(
                position = location.position,
                text = description,
                icon = StandardIcons.ATTRACTION,
                id = id
            )
}