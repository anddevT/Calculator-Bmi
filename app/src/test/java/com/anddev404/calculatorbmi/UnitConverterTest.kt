package com.anddev404.calculatorbmi

import com.anddev404.calculatorbmi.data.model.HeightUnit
import com.anddev404.calculatorbmi.data.model.WeightUnit
import com.anddev404.calculatorbmi.tools.UnitConverter
import org.junit.Assert.*
import org.junit.Test

class UnitConverterTest {

    @Test
    fun convertWeight() {

        assertEquals("0.0 kg", UnitConverter.convertWeight(0f, WeightUnit.KG))
        assertEquals("0.01 kg", UnitConverter.convertWeight(0.01f, WeightUnit.KG))
        assertEquals("0.1 kg", UnitConverter.convertWeight(0.1f, WeightUnit.KG))
        assertEquals("1.0 kg", UnitConverter.convertWeight(1.0f, WeightUnit.KG))
        assertEquals("10.0 kg", UnitConverter.convertWeight(10f, WeightUnit.KG))
        assertEquals("10.1 kg", UnitConverter.convertWeight(10.1f, WeightUnit.KG))
        assertEquals("100.0 kg", UnitConverter.convertWeight(100f, WeightUnit.KG))

        assertEquals("0 lb", UnitConverter.convertWeight(0f, WeightUnit.LB))
        assertEquals("0 lb", UnitConverter.convertWeight(0.01f, WeightUnit.LB))
        assertEquals("0 lb", UnitConverter.convertWeight(0.1f, WeightUnit.LB))
        assertEquals("2 lb", UnitConverter.convertWeight(1f, WeightUnit.LB))
        assertEquals("22 lb", UnitConverter.convertWeight(10f, WeightUnit.LB))
        assertEquals("220 lb", UnitConverter.convertWeight(100f, WeightUnit.LB))

        assertEquals("0 st 0 lb", UnitConverter.convertWeight(0f, WeightUnit.ST_LB))
        assertEquals("0 st 0 lb", UnitConverter.convertWeight(0.01f, WeightUnit.ST_LB))
        assertEquals("0 st 0 lb", UnitConverter.convertWeight(0.1f, WeightUnit.ST_LB))
        assertEquals("0 st 2 lb", UnitConverter.convertWeight(1f, WeightUnit.ST_LB))
        assertEquals("1 st 8 lb", UnitConverter.convertWeight(10f, WeightUnit.ST_LB))
        assertEquals("15 st 10 lb", UnitConverter.convertWeight(100f, WeightUnit.ST_LB))

    }

    @Test
    fun convertHeight() {

        assertEquals("0.0 cm", UnitConverter.convertHeight(0f, HeightUnit.CM))
        assertEquals("0.01 cm", UnitConverter.convertHeight(0.01f, HeightUnit.CM))
        assertEquals("0.1 cm", UnitConverter.convertHeight(0.1f, HeightUnit.CM))
        assertEquals("1.0 cm", UnitConverter.convertHeight(1.0f, HeightUnit.CM))
        assertEquals("10.0 cm", UnitConverter.convertHeight(10f, HeightUnit.CM))
        assertEquals("10.1 cm", UnitConverter.convertHeight(10.1f, HeightUnit.CM))
        assertEquals("100.0 cm", UnitConverter.convertHeight(100f, HeightUnit.CM))

        assertEquals("0 in", UnitConverter.convertHeight(0f, HeightUnit.IN))
        assertEquals("0 in", UnitConverter.convertHeight(0.01f, HeightUnit.IN))
        assertEquals("0 in", UnitConverter.convertHeight(0.1f, HeightUnit.IN))
        assertEquals("0 in", UnitConverter.convertHeight(1f, HeightUnit.IN))
        assertEquals("4 in", UnitConverter.convertHeight(10f, HeightUnit.IN))
        assertEquals("39 in", UnitConverter.convertHeight(100f, HeightUnit.IN))

        assertEquals("0 ft 0 in", UnitConverter.convertHeight(0f, HeightUnit.FT_IN))
        assertEquals("0 ft 0 in", UnitConverter.convertHeight(0.01f, HeightUnit.FT_IN))
        assertEquals("0 ft 0 in", UnitConverter.convertHeight(0.1f, HeightUnit.FT_IN))
        assertEquals("0 ft 0 in", UnitConverter.convertHeight(1f, HeightUnit.FT_IN))
        assertEquals("0 ft 4 in", UnitConverter.convertHeight(10f, HeightUnit.FT_IN))
        assertEquals("3 ft 3 in", UnitConverter.convertHeight(100f, HeightUnit.FT_IN))

    }

}