package com.marshalldbrain.pulsar.core.modifiers

import com.marshalldbrain.pulsar.core.scope.modifier.ScopeMod

object Modifiers {
	
	private val modifierGroups = mutableMapOf<ScopeMod, ModifierGroup>()
	
	fun createModifier(
		scope: ScopeMod,
		root: Modifiable,
		limiters: Set<Modifiable> = emptySet()
	): ModifierCalc.MutableCell {
			return modifierGroups[scope]!!.createModifier(root, limiters)
	}
	
	fun createGroup(
		scope: ScopeMod,
		parentModifiers: List<MutableMap<Modifiable, MutableMap<Set<Modifiable>, ModifierCalc>>> = mutableListOf()
	): ModifierGroup {
		return ModifierGroup(parentModifiers).also {
			modifierGroups[scope] = it
		}
	}
	
}