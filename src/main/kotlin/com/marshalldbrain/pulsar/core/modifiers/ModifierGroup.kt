package com.marshalldbrain.pulsar.core.modifiers

import kotlin.math.max

class ModifierGroup (
	modifierBuilder: (MutableMap<String, MutableMap<String, Double>>) -> Unit
) {
	
	val modifierList = buildMap<String, MutableMap<String, Double>>(modifierBuilder).toMutableMap()
	
	fun reduce(targetModifier: String, amount: Double): Double {
		return ModifierReducer.reduce(modifierList, targetModifier, amount)
	}
	
}

fun MutableMap<String, MutableMap<String, Double>>.create(key: String, value: Double) {
	
	val index = max(key.lastIndexOf("."), 0)
	val limiters = this.getOrElse(key.drop(index).removePrefix(".")) {
		mutableMapOf(Pair("", 0.0)).also { this[key.drop(index).removePrefix(".")] = it }
	}
	limiters.compute(key.take(index)) {
			_, v -> if (v == null) value else v + value
	}
	
}