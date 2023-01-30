package com.anddev404.calculatorbmi.tools

class CalculatorTools {

    // height in [cm]
    // weight in [kg]
    companion object {

        fun calculateBmi(height: Float, weight: Float): Float {
            return if (height > 0) weight / ((height * height) / 10000)
            else 0f
        }

        fun calculateUndeOverWeight(height: Float, weight: Float): Float {
            if (height <= 0f || weight <= 0f) return 0f

            val minWeight = calculateMinWeight(height)
            val maxWeight = calculateMaxWeight(height)

            return when {
                weight > maxWeight -> weight - maxWeight
                weight < minWeight -> weight - minWeight
                else -> 0f
            }
        }

        fun calculateMinWeight(height: Float): Float {
            return if (height > 0) 18.5f * ((height / 100) * (height / 100))
            else 0f
        }

        fun calculateMaxWeight(height: Float): Float {
            return if (height > 0) 25f * ((height / 100) * (height / 100))
            else 0f
        }

        fun calculateIdealWeight(height: Float): Float {
            return if (height > 0) 21.75f * ((height / 100) * (height / 100))
            else 0f
        }
    }
}