package travelator.opentosealed.itineraray

import travelator.opentosealed.Id
import travelator.opentosealed.geo.MapOverlay
import travelator.opentosealed.money.Money

interface ItineraryItem {
    val id: Id<ItineraryItem>
    val description: String
    val costs: List<Money>
    val mapOverlay: MapOverlay
}