package org.bullit.challenge1581894

enum class Feature {
    BREAKFAST, REFUNDABLE, WIFI
}

data class Room(
    val price: Int,
    val features: Set<Feature>,
    val availability: Int
)

data class AvailabilityInput(
    val checkin: Int,
    val checkout: Int,
    val features: Set<Feature>,
    val rooms: Int
)

data class AvailabilityOutput(
    val price: Int,
    val features: Set<Feature>,
    val availability: Int
)