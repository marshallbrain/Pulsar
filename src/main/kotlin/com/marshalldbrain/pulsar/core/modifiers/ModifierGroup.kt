package com.marshalldbrain.pulsar.core.modifiers

import com.marshalldbrain.pulsar.core.scope.modifier.ScopeMod

class ModifierGroup(
	parentList: List<MutableMap<Modifiable, MutableMap<Set<Modifiable>, ModifierCalc>>>
) {
	
	private val modifierList = mutableMapOf<Modifiable, MutableMap<Set<Modifiable>, ModifierCalc>>()
	private val parentModifiers = parentList.toMutableList().also {
		it.add(modifierList)
	}
	
	fun createChildGroup(scope: ScopeMod): ModifierGroup {
		return Modifiers.createGroup(
			scope,
			parentModifiers.toMutableList()
		)
	}
	
	fun createModifier(root: Modifiable, limiters: Set<Modifiable>): ModifierCalc.MutableCell {
		val calc = modifierList.getOrPut(root) {
			mutableMapOf()
		}.getOrPut(limiters) {ModifierCalc()}
		return calc.createCell()
	}
	
	fun applyModifiers(
		amount: Number,
		target: Modifiable,
		properties: Set<Modifiable>
	): Double {
		
		return parentModifiers.sumOf { map ->
			map.getOrDefault(
				target, mutableMapOf()
			).filterKeys {
				properties.containsAll(it)
			}.values.fold(ModifierCalc.Cell()) { c, v ->
				c + v.cell
			} * amount
		} + amount.toDouble()
		
	}
	
	fun apply(
		properties: Set<Modifiable>
	): Pair<ModifierGroup, Set<Modifiable>> {
		return Pair(this, properties)
	}
	
}