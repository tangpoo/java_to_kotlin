@file:JvmName("Shortlists")

package travelator.choice

import java.util.stream.Collectors
import java.util.stream.Stream


fun <T> sorted(
        shortlist: List<T>,
        ordering: Comparator<in T>?
): List<T> {
    return shortlist.stream()
            .sorted(ordering)
            .collect(Collectors.toUnmodifiableList())
}

fun <T> removeItemAt(shortlist: List<T>, index: Int): List<T> {
    return Stream.concat(
            shortlist.stream().limit(index.toLong()),
            shortlist.stream().skip((index + 1).toLong())
    ).collect(Collectors.toUnmodifiableList())
}


fun byRating(): Comparator<HasRating> {
    return Comparator.comparingDouble(HasRating::rating).reversed()
}


fun byPriceLowToHigh(): Comparator<HasPrice> {
    return Comparator.comparing(HasPrice::price)
}


fun <T> byValue(): Comparator<T> where T : HasPrice?, T : HasRating? {
    return Comparator.comparingDouble { t: T -> t!!.rating / t.price }.reversed()
}


fun byRelevance(): Comparator<HasRelevance> {
    return Comparator.comparingDouble(HasRelevance::relevance).reversed()
}

