package com.marshalldbrain.pulsar.core.modifiers

import com.marshalldbrain.pulsar.core.scope.modifier.ScopeMod

object Modifiers {
	
	private val modifierGroups = mutableMapOf<ScopeMod, ModifierGroup>()
	
	fun createModifier(
		scope: ScopeMod,
		amount: Double,
		root: Modifiable,
		limiters: Set<Modifiable> = emptySet()
	) {
			modifierGroups[scope]?.createModifier(amount, root, limiters)
	}
	
	fun createGroup(
		scope: ScopeMod,
		parentModifiers: List<MutableMap<Modifiable, MutableMap<Set<Modifiable>, Double>>> = mutableListOf()
	): ModifierGroup {
		return ModifierGroup(parentModifiers).also {
			modifierGroups[scope] = it
		}
	}
	
}