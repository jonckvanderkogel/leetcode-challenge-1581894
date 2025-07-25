package org.bullit.challenge1581894

class AvailabilityService {
    fun checkAvailability(
        data: Map<Int, List<Room>>,
        input: AvailabilityInput
    ): List<AvailabilityOutput> {
        val availableRooms = data
            .filter {(key, value) ->
                key >= input.checkin && key < input.checkout
            }
            .map {(key, value) ->
                value.filter {
                    it.features.containsAll(input.features) && it.availability >= input.rooms
                }
            }
        return if (verifyAvailability(availableRooms, input)) {
            cartesianProduct(emptyList(), availableRooms)
        } else {
            emptyList()
        }
    }

    private fun verifyAvailability(
        availableRooms: List<List<Room>>,
        input: AvailabilityInput
    ): Boolean =
        availableRooms.size == input.checkout - input.checkin &&
                availableRooms.size == availableRooms
                    .count {
                        it.isNotEmpty()
                    }

    tailrec fun cartesianProduct(accumulator: List<AvailabilityOutput>, rooms: List<List<Room>>): List<AvailabilityOutput> {
        return if (rooms.isEmpty()) {
            accumulator
        } else {
            val nextDay = rooms[0]
            val newAvailabilityOutput = nextDay
                .flatMap {
                    it.calculateAvailability(accumulator)
                }
            cartesianProduct(newAvailabilityOutput, rooms.subList(1, rooms.size))
        }
    }

    private fun Room.calculateAvailability(availabilityOutput: AvailabilityOutput) =
        AvailabilityOutput(
            this.price + availabilityOutput.price,
            availabilityOutput.features.intersect(this.features),
            minOf(this.availability, availabilityOutput.availability)
        )

    private fun Room.calculateAvailability(availabilityOutput: List<AvailabilityOutput>) =
        if (availabilityOutput.isEmpty()) {
            listOf(
                AvailabilityOutput(
                    this.price,
                    this.features,
                    this.availability
                )
            )
        } else {
            availabilityOutput
                .map {
                    this.calculateAvailability(it)
                }
        }
}