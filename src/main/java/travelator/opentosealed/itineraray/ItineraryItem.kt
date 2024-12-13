package travelator.opentosealed.itineraray

import travelator.opentosealed.Id
import travelator.opentosealed.Location
import travelator.opentosealed.geo.*
import travelator.opentosealed.money.Money
import java.time.Period
import java.time.ZonedDateTime

sealed class ItineraryItem {
    abstract val id: Id<ItineraryItem>
}

data class Accommodation(
    override val id: Id<Accommodation>,
    val location: Location,
    val checkInFrom: ZonedDateTime,
    val checkOutBefore: ZonedDateTime,
    val pricePerNight: Money
) : ItineraryItem() {
    val nights = Period.between(checkInFrom.toLocalDate(), checkOutBefore.toLocalDate()).days
    val totalPrice: Money = pricePerNight * nights
}

data class Attraction(
    override val id: Id<Attraction>,
    val location: Location,
    val notes: String
) : ItineraryItem()

data class Journey(
    override val id: Id<Journey>,
    val travelMethod: TravelMethod,
    val departsFrom: Location,
    val departureTime: ZonedDateTime,
    val arrivesAt: Location,
    val arrivalTime: ZonedDateTime,
    val price: Money,
    val path: List<Position> = listOf(departsFrom.position, arrivesAt.position),
) : ItineraryItem()

data class RestaurantBooking(
    override val id: Id<RestaurantBooking>,
    val location: Location,
    val time: ZonedDateTime
) : ItineraryItem()

