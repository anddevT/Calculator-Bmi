package com.anddev404.calculatorbmi

import com.anddev404.calculatorbmi.tools.CalculatorTools
import org.junit.Assert.*
import org.junit.Test

class CalculatorToolsTest {

    @Test
    fun calculateBmi() {

        assertEquals(51.9f, CalculatorTools.calculateBmi(170f, 150f), 1f)
        assertEquals(34.6f, CalculatorTools.calculateBmi(170f, 100f), 1f)
        assertEquals(17.3f, CalculatorTools.calculateBmi(170f, 50f), 1f)
        assertEquals(3.5f, CalculatorTools.calculateBmi(170f, 10f), 1f)
        assertEquals(0f, CalculatorTools.calculateBmi(170f, 1f), 1f)
        assertEquals(0f, CalculatorTools.calculateBmi(170f, 0.1f), 1f)
        assertEquals(0f, CalculatorTools.calculateBmi(170f, 0.01f), 1f)

        assertEquals(0f, CalculatorTools.calculateBmi(170f, 0f))
        assertEquals(0f, CalculatorTools.calculateBmi(0f, 0f))
        assertEquals(0f, CalculatorTools.calculateBmi(0f, 100f))

        assertEquals(10000000000f, CalculatorTools.calculateBmi(0.01f, 100f), 10f)
        assertEquals(100000000f, CalculatorTools.calculateBmi(0.1f, 100f), 10f)
        assertEquals(1000000f, CalculatorTools.calculateBmi(1f, 100f), 1f)
        assertEquals(10000f, CalculatorTools.calculateBmi(10f, 100f), 1f)
        assertEquals(400f, CalculatorTools.calculateBmi(50f, 100f), 1f)
        assertEquals(100f, CalculatorTools.calculateBmi(100f, 100f), 1f)
        assertEquals(44.4f, CalculatorTools.calculateBmi(150f, 100f), 1f)
        assertEquals(34.6f, CalculatorTools.calculateBmi(170f, 100f), 1f)

    }

    @Test
    fun calculateMinWeight() {

        assertEquals(74f, CalculatorTools.calculateMinWeight(200f), 1f)
        assertEquals(42f, CalculatorTools.calculateMinWeight(150f), 1f)
        assertEquals(0f, CalculatorTools.calculateMinWeight(1f), 1f)
        assertEquals(0f, CalculatorTools.calculateMinWeight(0.01f), 1f)

        assertEquals(0f, CalculatorTools.calculateMinWeight(0f), 1f)

        assertEquals(0f, CalculatorTools.calculateMinWeight(-0.01f), 0f)
        assertEquals(0f, CalculatorTools.calculateMinWeight(-1f), 0f)
        assertEquals(0f, CalculatorTools.calculateMinWeight(-100f), 0f)
        assertEquals(0f, CalculatorTools.calculateMinWeight(-200f), 0f)
    }

    @Test
    fun calculateMaxWeight() {

        assertEquals(100f, CalculatorTools.calculateMaxWeight(200f), 1f)
        assertEquals(56f, CalculatorTools.calculateMaxWeight(150f), 1f)
        assertEquals(0f, CalculatorTools.calculateMaxWeight(1f), 1f)
        assertEquals(0f, CalculatorTools.calculateMaxWeight(0.01f), 1f)

        assertEquals(0f, CalculatorTools.calculateMaxWeight(0f), 1f)

        assertEquals(0f, CalculatorTools.calculateMaxWeight(-0.01f), 0f)
        assertEquals(0f, CalculatorTools.calculateMaxWeight(-1f), 0f)
        assertEquals(0f, CalculatorTools.calculateMaxWeight(-100f), 0f)
        assertEquals(0f, CalculatorTools.calculateMaxWeight(-200f), 0f)
    }

    @Test
    fun calculateIdealWeight() {

        assertEquals(87f, CalculatorTools.calculateIdealWeight(200f), 1f)
        assertEquals(49f, CalculatorTools.calculateIdealWeight(150f), 1f)
        assertEquals(0f, CalculatorTools.calculateIdealWeight(1f), 1f)
        assertEquals(0f, CalculatorTools.calculateIdealWeight(0.01f), 1f)

        assertEquals(0f, CalculatorTools.calculateIdealWeight(0f), 0f)

        assertEquals(0f, CalculatorTools.calculateIdealWeight(-0.01f), 0f)
        assertEquals(0f, CalculatorTools.calculateIdealWeight(-1f), 0f)
        assertEquals(0f, CalculatorTools.calculateIdealWeight(-100f), 0f)
        assertEquals(0f, CalculatorTools.calculateIdealWeight(-200f), 0f)
    }

    @Test
    fun calculateUndeOverWeight() {

        assertEquals(0f, CalculatorTools.calculateUndeOverWeight(0f, 0f), 0f)

        assertEquals(0f, CalculatorTools.calculateUndeOverWeight(0f, 100f), 0f)
        assertEquals(0f, CalculatorTools.calculateUndeOverWeight(150f, 0f), 0f)

        assertEquals(44f, CalculatorTools.calculateUndeOverWeight(150f, 100f), 1f)
        assertEquals(0f, CalculatorTools.calculateUndeOverWeight(150f, 50f), 1f)
        assertEquals(-40f, CalculatorTools.calculateUndeOverWeight(150f, 1f), 1f)
        assertEquals(-41f, CalculatorTools.calculateUndeOverWeight(150f, 0.01f), 1f)
        assertEquals(0f, CalculatorTools.calculateUndeOverWeight(150f, 0f), 0f)

        assertEquals(44f, CalculatorTools.calculateUndeOverWeight(150f, 100f), 1f)
        assertEquals(94f, CalculatorTools.calculateUndeOverWeight(50f, 100f), 1f)
        assertEquals(99.9f, CalculatorTools.calculateUndeOverWeight(1f, 100f), 1f)
        assertEquals(100f, CalculatorTools.calculateUndeOverWeight(0.01f, 100f), 1f)
        assertEquals(0f, CalculatorTools.calculateUndeOverWeight(0f, 100f), 0f)

    }
}