package com.marshalldbrain.pulsar.core.modifiers

import com.marshalldbrain.pulsar.core.scope.modifier.ScopeMod

class ModifierGroup(
	parentList: List<MutableMap<Modifiable, MutableMap<Set<Modifiable>, Double>>>
) {
	
	private val modifierList = mutableMapOf<Modifiable, MutableMap<Set<Modifiable>, Double>>()
	private val parentModifiers = parentList.toMutableList().also {
		it.add(modifierList)
	}
	
	fun createChildGroup(scope: ScopeMod): ModifierGroup {
		return Modifiers.createGroup(
			scope,
			parentModifiers.toMutableList()
		)
	}
	
	fun createModifier(amount: Double, root: Modifiable, limiters: Set<Modifiable>) {
		modifierList.getOrPut(root) {
			mutableMapOf()
		}[limiters] = amount
	}
	
	fun applyModifiers(
		amount: Double,
		target: Modifiable,
		properties: Set<Modifiable>
	): Double {
		
		return parentModifiers.sumOf { map ->
			map.getOrDefault(
				target, mutableMapOf()
			).filterKeys {
				properties.containsAll(it)
			}.values.sum() * amount
		} + amount
		
	}
	
}