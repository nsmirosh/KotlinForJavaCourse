package collectionPractice.coursera

data class TaxiPark(
    val allDrivers: Set<Driver>,
    val allPassengers: Set<Passenger>,
    val trips: List<Trip>
)

data class Driver(val name: String)
data class Passenger(val name: String)

data class Trip(
    val driver: Driver,
    val passengers: Set<Passenger>,
    // the trip duration in minutes
    val duration: Int,
    // the trip distance in km
    val distance: Double,
    // the percentage of discount (in 0.0..1.0 if not null)
    val discount: Double? = null
) {
    // the total cost of the trip
    val cost: Double
        get() = (1 - (discount ?: 0.0)) * (duration + distance)
}

fun main() {
    provideFakeTaxiPark()
}

fun provideFakeTaxiPark() {
    /*  val taxiPark = taxiPark(
          0..3, 0..7,
          trip(1, listOf(2, 6), duration = 23, distance = 23.0, discount = 0.4),
          trip(1, listOf(3, 2, 7), duration = 29, distance = 27.0, discount = 0.1),
          trip(0, listOf(7), duration = 13, distance = 28.0, discount = 0.1),
          trip(1, listOf(5), duration = 5, distance = 0.0),
          trip(0, listOf(3, 2), duration = 12, distance = 19.0),
          trip(1, listOf(4, 3, 6, 7), duration = 18, distance = 9.0, discount = 0.3),
          trip(0, listOf(1, 3), duration = 3, distance = 32.0),
          trip(0, listOf(6, 5, 0), duration = 9, distance = 7.0),
          trip(2, listOf(6, 2, 7), duration = 19, distance = 18.0),
          trip(0, listOf(7), duration = 32, distance = 31.0, discount = 0.1),
          trip(3, listOf(4, 2, 5, 6), duration = 22, distance = 28.0),
          trip(3, listOf(3, 7, 2), duration = 36, distance = 20.0),
          trip(1, listOf(1, 3), duration = 16, distance = 5.0, discount = 0.3),
          trip(0, listOf(6), duration = 18, distance = 27.0),
          trip(3, listOf(3, 7), duration = 0, distance = 10.0),
          trip(0, listOf(2, 1, 6), duration = 9, distance = 8.0),
          trip(0, listOf(6, 4), duration = 35, distance = 31.0, discount = 0.3),
          trip(1, listOf(7), duration = 23, distance = 7.0),
          trip(1, listOf(0, 2, 3, 5), duration = 33, distance = 14.0, discount = 0.2),
          trip(0, listOf(5, 3), duration = 8, distance = 1.0)
      )*/


    /*val taxiPark = taxiPark(1..5, 1..4,
        trip(1, 1, 20, 20.0),
        trip(1, 2, 20, 20.0),
        trip(1, 3, 20, 20.0),
        trip(1, 4, 20, 20.0),
        trip(2, 1, 20, 21.0))*/


    val taxiPark = taxiPark(
        0..4, 0..9,
        trip(4, listOf(7, 1, 0), duration = 3, distance = 3.0, discount = 0.1),
        trip(3, listOf(2), duration = 14, distance = 27.0, discount = 0.2),
        trip(3, listOf(6), duration = 25, distance = 21.0),
        trip(1, listOf(3), duration = 9, distance = 0.0),
        trip(0, listOf(7, 3, 2, 0), duration = 4, distance = 11.0, discount = 0.4),
        trip(3, listOf(9, 1, 2, 3), duration = 28, distance = 17.0),
        trip(3, listOf(3, 1), duration = 5, distance = 17.0, discount = 0.1),
        trip(3, listOf(0, 1, 2), duration = 22, distance = 26.0),
        trip(3, listOf(3, 8), duration = 15, distance = 21.0),
        trip(3, listOf(6, 5), duration = 21, distance = 6.0, discount = 0.1),
        trip(3, listOf(4), duration = 10, distance = 13.0),
        trip(3, listOf(2, 7), duration = 13, distance = 22.0, discount = 0.1),
        trip(1, listOf(4, 9), duration = 22, distance = 5.0, discount = 0.4),
        trip(4, listOf(8, 7, 9), duration = 10, distance = 19.0, discount = 0.4),
        trip(4, listOf(6), duration = 2, distance = 26.0, discount = 0.3)
    )

    println(taxiPark.findSmartPassengers2())
}


/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    allDrivers - trips.map { it.driver }


/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {

    //build a map of passenger along with the amount of trips they did
    val passengerMap = mutableMapOf<String, Int>()
    trips.forEach {
        it.passengers.forEach {
            var previousCount = passengerMap[it.name] ?: 0
            passengerMap[it.name] = ++previousCount
        }
    }


    //corner case for when the passenger didn't make any trips at all
    if (minTrips == 0) {
        allPassengers.forEach {
            if (!(it.name in passengerMap.keys)) {
                passengerMap[it.name] = 0
            }
        }
    }

    // filter out the passenger based on minTrips
    return passengerMap
        .filter { it.value >= minTrips }
        .map { mapEntry -> Passenger(mapEntry.key) }
        .sortedBy { passenger -> passenger.name }
        .toSet()
}

fun TaxiPark.findFaithfulPassengers2(minTrips: Int): Set<Passenger> =
    trips
        .flatMap(Trip::passengers)
        .groupBy { passenger -> passenger }
        .filterValues { group -> group.size >= minTrips }
        .keys

fun TaxiPark.findFaithfulPassengers3(minTrips: Int): Set<Passenger> =
    allPassengers
        .filter { p ->
            trips.count { p in it.passengers } >= minTrips
        }
        .toSet()


/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    trips
        .filter { it.driver == driver }
        .flatMap { it.passengers }
        .groupBy { it.name }
        .filterValues { group -> group.size > 1 }
        .map { Passenger(it.key) }
        .toSet()


fun TaxiPark.findFrequentPassengers2(driver: Driver): Set<Passenger> =
    trips
        .filter { trip -> trip.driver == driver }
        .flatMap(Trip::passengers)
        .groupBy { passenger -> passenger }
        .filterValues { group -> group.size > 1 }
        .keys

fun TaxiPark.findFrequentPassengers3(driver: Driver): Set<Passenger> =
    allPassengers
        .filter { passenger ->
            trips.count { driver == it.driver && passenger in it.passengers } > 1
        }
        .toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {

    val withAndWithoutDiscounts = trips.partition { it.discount != null }

    val discountTripPassengers = withAndWithoutDiscounts.first.flatMap {
        it.passengers
    }
    val noDiscountTripPassengers = withAndWithoutDiscounts.second.flatMap {
        it.passengers
    }

    return mutableSetOf<Passenger>().apply {
        allPassengers.forEach { passenger ->
            val discountTripsHad = discountTripPassengers.count { it == passenger }
            val noDiscountTripsHad = noDiscountTripPassengers.count { it == passenger }
            if (discountTripsHad > noDiscountTripsHad) this += passenger
        }
    }
}

fun TaxiPark.findSmartPassengers1(): Set<Passenger> {
    val (tripsWithDiscount, tripsWithoutDiscount) = trips.partition {
        it.discount != null
    }


    return allPassengers.filter { passenger ->
        tripsWithDiscount.count { passenger in it.passengers } >
                tripsWithoutDiscount.count { passenger in it.passengers }
    }.toSet()
}

fun TaxiPark.findSmartPassengers2(): Set<Passenger> {

    val result = allPassengers.associate { p ->
        p to trips.filter { t -> p in t.passengers }
    }

    return result.filterValues { group ->
        val (withDiscount, withoutDiscount) = group.partition { it.discount != null }
        withDiscount.size > withoutDiscount.size
    }.keys
}


/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {

    fun createRangeOfTen(number: Int): IntRange {
        val trunc = number / 10
        val lowerBound = trunc * 10
        val upperBound = lowerBound + 9

        return lowerBound..upperBound
    }

    return trips
        .groupBy { createRangeOfTen(it.duration) }
        .maxByOrNull { it.value.size }
        ?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    val driversThatMakeMoneySorted = trips
        .map { Pair(it.driver.name, it.cost) }
        .groupBy({ it.first }, { it.second })
        .map { Pair(Driver(it.key), it.value.sum()) }
        .sortedByDescending { it.second }

    val driversThatMadeMoney = driversThatMakeMoneySorted.map {
        it.first
    }

    val missingDrivers = (allDrivers - driversThatMadeMoney)
    val mutableSortedDrivers = driversThatMakeMoneySorted.toMutableList()

    missingDrivers.forEach {
        mutableSortedDrivers.add(Pair(it, 0.0))
    }

    var upperBound = (allDrivers.size * 0.2).toInt()

    if (upperBound != 0) {
        upperBound = upperBound.dec()
    }

    val twentyPercent = 0..upperBound

    return mutableSortedDrivers
        .slice(twentyPercent)
        .sumOf { it.second } /
            mutableSortedDrivers
                .sumOf { it.second } >= 0.8
}




/*

fun TaxiPark.checkParetoPrinciple1(): Boolean {
    if (trips.isEmpty()) return false

    val totalIncome = trips.sumByDouble(TODO())
    val sortedDriversIncome: List<Double> = trips
        .groupBy(TODO())
        .map { (_, tripsByDriver) -> tripsByDriver.sumByDouble(TODO()) }
        .sortedDescending()

    val numberOfTopDrivers = (0.2 * allDrivers.size).toInt()
    val incomeByTopDrivers = sortedDriversIncome
        .take(TODO())
        .sum()

    return incomeByTopDrivers >= 0.8 * totalIncome
}*/
