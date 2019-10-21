package es.iessaladillo.pedrojoya.tipcalculator.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TipCalculatorTest {

    @DisplayName("Should throw IllegalArgumentException when bill is negative")
    @Test
    fun `should throw IllegalArgumentException when bill is negative`() {
        assertThrows(IllegalArgumentException::class.java) { TipCalculator(-1f, 10f, 1) }
    }

    @DisplayName("Should throw IllegalArgumentException when percentage is negative")
    @Test
    fun `should throw IllegalArgumentException when percentage is negative`() {
        assertThrows(IllegalArgumentException::class.java) { TipCalculator(1f, -10f, 1) }
    }

    @DisplayName("Should throw IllegalArgumentException when diners is not positive")
    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `should throw IllegalArgumentException when diners is not positive`(diners: Int) {
        assertThrows(IllegalArgumentException::class.java) { TipCalculator(1f, 10f, -1) }
    }

    @DisplayName("Should calculate total properly")
    @Test
    fun `should calculate total properly`() {
        assertEquals(120.0.toFloat(), TipCalculator(100f, 20f, 20).calculateTotal())
    }

    @DisplayName("Should calculate total properly with bill 0")
    @Test
    fun `should calculate total properly with bill 0`() {
        assertEquals(0.0.toFloat(), TipCalculator(0f, 20f, 20).calculateTotal())
    }

    @DisplayName("Should calculate total properly with percentage 0")
    @Test
    fun `should calculate total properly with percentage 0`() {
        assertEquals(100.0.toFloat(), TipCalculator(100f, 0f, 20).calculateTotal())
    }

    @DisplayName("Should calculate perDiner properly")
    @Test
    fun `should calculate perDiner properly`() {
        assertEquals(6.0.toFloat(), TipCalculator(100f, 20f, 20).calculatePerDiner())

    }

    @DisplayName("Should calculate perDinerRounded properly")
    @Test
    fun `should calculate perDinerRounded properly`() {
        assertEquals(6.0.toFloat(), TipCalculator(100f, 20f, 20).calculatePerDinerRounded())
    }

    @DisplayName("Should calculate perDinerRounded properly when perDinerRounded has 00 as cents")
    @Test
    fun `should calculate perDinerRounded properly when perDinerRounded has 00 as cents`() {
        assertEquals(6.00.toFloat(), TipCalculator(100f, 20f, 20).calculatePerDinerRounded())
    }

}