package com.anddev404.calculatorbmi.data.local

import com.anddev404.calculatorbmi.data.model.HeightUnit
import com.anddev404.calculatorbmi.data.model.WeightUnit

interface LocalDataSource {

    suspend fun getHeightInKg(): Int
    suspend fun saveHeightInKg(height: Int): Boolean

    suspend fun getHeightUnit(): HeightUnit
    suspend fun saveHeightUnit(heightUnit: HeightUnit): Boolean

    suspend fun getWeightUnit(): WeightUnit
    suspend fun saveWeightUnit(weightUnit: WeightUnit): Boolean
}