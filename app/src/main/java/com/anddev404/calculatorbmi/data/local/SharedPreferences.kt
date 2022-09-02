package com.anddev404.calculatorbmi.data.local

import android.content.Context
import android.content.ContextWrapper
import com.anddev404.calculatorbmi.data.model.HeightUnit
import com.anddev404.calculatorbmi.data.model.WeightUnit
import com.pixplicity.easyprefs.library.Prefs

class SharedPreferences(context: Context) : LocalDataSource {

    init {
        Prefs.Builder()
            .setContext(context)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(context.packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    override suspend fun getHeightInKg(): Int {
        return Prefs.getInt(keyHeight, defaultHeight)
    }

    override suspend fun saveHeightInKg(height: Int): Boolean {
        Prefs.putInt(keyHeight, height)
        return true
    }

    override suspend fun getHeightUnit(): HeightUnit {
        return when (Prefs.getInt(keyHeightUnit, HeightUnit.CM.id)) {
            HeightUnit.CM.id -> HeightUnit.CM
            HeightUnit.FT_IN.id -> HeightUnit.FT_IN
            HeightUnit.IN.id -> HeightUnit.IN
            else -> HeightUnit.CM
        }
    }

    override suspend fun saveHeightUnit(heightUnit: HeightUnit): Boolean {
        return when (heightUnit) {
            HeightUnit.CM -> {
                Prefs.putInt(keyHeightUnit, heightUnit.id)
                true
            }
            HeightUnit.FT_IN -> {
                Prefs.putInt(keyHeightUnit, heightUnit.id)
                true
            }
            HeightUnit.IN -> {
                Prefs.putInt(keyHeightUnit, heightUnit.id)
                true
            }
        }
    }

    override suspend fun getWeightUnit(): WeightUnit {
        return when (Prefs.getInt(keyWeightUnit, WeightUnit.KG.id)) {
            WeightUnit.KG.id -> WeightUnit.KG
            WeightUnit.LB.id -> WeightUnit.LB
            WeightUnit.ST_LB.id -> WeightUnit.ST_LB
            else -> WeightUnit.KG
        }
    }

    override suspend fun saveWeightUnit(weightUnit: WeightUnit): Boolean {
        return when (weightUnit) {
            WeightUnit.KG -> {
                Prefs.putInt(keyWeightUnit, weightUnit.id)
                true
            }
            WeightUnit.LB -> {
                Prefs.putInt(keyWeightUnit, weightUnit.id)
                true
            }
            WeightUnit.ST_LB -> {
                Prefs.putInt(keyWeightUnit, weightUnit.id)
                true
            }
        }
    }

    fun clearSharedPreferences() {
        Prefs.clear()
    }

    private companion object {
        const val defaultHeight = 0

        const val keyHeight = "height"

        const val keyHeightUnit = "height_unit"
        const val keyWeightUnit = "weight_unit"
    }
}