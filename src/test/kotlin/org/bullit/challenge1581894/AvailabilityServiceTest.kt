package org.bullit.challenge1581894

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AvailabilityServiceTest {

    @Test
    fun testCheckAvailability() {
        val availabilityService = AvailabilityService()

        val data = mapOf(
            176 to listOf(
                Room(
                    120,
                    setOf(Feature.BREAKFAST, Feature.REFUNDABLE),
                    5
                )
            ),
            177 to listOf(
                Room(
                    130,
                    setOf(Feature.BREAKFAST),
                    1
                ),
                Room(
                    140,
                    setOf(Feature.BREAKFAST, Feature.REFUNDABLE, Feature.WIFI),
                    3
                )
            ),
            178 to listOf(
                Room(
                    130,
                    setOf(Feature.BREAKFAST),
                    2
                ),
                Room(
                    140,
                    setOf(Feature.BREAKFAST, Feature.REFUNDABLE, Feature.WIFI),
                    10
                )
            )
        )

        val input = AvailabilityInput(
            176,
            178,
            setOf(Feature.BREAKFAST),
            1
        )

        val expectedOutput = listOf(
            AvailabilityOutput(
                250,
                features = setOf(Feature.BREAKFAST),
                1
            ),
            AvailabilityOutput(
                260,
                features = setOf(Feature.BREAKFAST, Feature.REFUNDABLE),
                3
            )
        )

        val actualOutput = availabilityService.checkAvailability(
            data, input
        )

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testCartesianProduct() {
        val availabilityService = AvailabilityService()

        val data = listOf(
            listOf(
                Room(
                    120,
                    setOf(Feature.BREAKFAST, Feature.REFUNDABLE),
                    5
                )
            ),
            listOf(
                Room(
                    130,
                    setOf(Feature.BREAKFAST),
                    1
                ),
                Room(
                    140,
                    setOf(Feature.BREAKFAST, Feature.REFUNDABLE, Feature.WIFI),
                    3
                )
            )
        )

        val expectedOutput = listOf(
            AvailabilityOutput(
                250,
                setOf(Feature.BREAKFAST),
                1
            ),
            AvailabilityOutput(
                260,
                setOf(Feature.BREAKFAST, Feature.REFUNDABLE),
                3
            )
        )
        val actualOutput = availabilityService.cartesianProduct(emptyList(), data)

        assertEquals(expectedOutput, actualOutput)
    }
}