package org.bullit

import org.bullit.Feature.*
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
                    setOf(BREAKFAST, REFUNDABLE),
                    5
                )
            ),
            177 to listOf(
                Room(
                    130,
                    setOf(BREAKFAST),
                    1
                ),
                Room(
                    140,
                    setOf(BREAKFAST, REFUNDABLE, WIFI),
                    3
                )
            ),
            178 to listOf(
                Room(
                    130,
                    setOf(BREAKFAST),
                    2
                ),
                Room(
                    140,
                    setOf(BREAKFAST, REFUNDABLE, WIFI),
                    10
                )
            )
        )

        val input = AvailabilityInput(
            176,
            178,
            setOf(BREAKFAST),
            1
        )

        val expectedOutput = listOf(
            AvailabilityOutput(
                250,
                features = setOf(BREAKFAST),
                1
            ),
            AvailabilityOutput(
                260,
                features = setOf(BREAKFAST, REFUNDABLE),
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
                    setOf(BREAKFAST, REFUNDABLE),
                    5
                )
            ),
            listOf(
                Room(
                    130,
                    setOf(BREAKFAST),
                    1
                ),
                Room(
                    140,
                    setOf(BREAKFAST, REFUNDABLE, WIFI),
                    3
                )
            )
        )

        val expectedOutput = listOf(
            AvailabilityOutput(
                250,
                setOf(BREAKFAST),
                1
            ),
            AvailabilityOutput(
                260,
                setOf(BREAKFAST, REFUNDABLE),
                3
            )
        )
        val actualOutput = availabilityService.cartesianProduct(emptyList(), data)

        assertEquals(expectedOutput, actualOutput)
    }
}