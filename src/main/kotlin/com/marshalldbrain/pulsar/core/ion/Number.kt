package com.marshalldbrain.pulsar.core.ion

import com.marshalldbrain.pulsar.core.empire.colony.construction.BuildType
import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictMining
import java.math.MathContext
import kotlin.reflect.typeOf

fun Double.round(): Double {
	return this.toBigDecimal(MathContext(0)).toDouble()
}

fun Double.round(precision: Int): Double {
	return this.toBigDecimal(MathContext(precision)).toDouble()
}