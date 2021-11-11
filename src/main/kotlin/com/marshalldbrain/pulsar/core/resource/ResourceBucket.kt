package com.marshalldbrain.pulsar.core.resource

import com.marshalldbrain.pulsar.core.modifiers.Modifiable
import com.marshalldbrain.pulsar.core.modifiers.ModifierGroup
import com.marshalldbrain.pulsar.core.resource.types.ResourceType

class ResourceBucket {

	private val _resourceBucket = mutableMapOf<ResourceType, Int>()
	val resourceBucket: Map<ResourceType, Int>
		get() = _resourceBucket
	
	fun get(key: ResourceType): Int {
		return _resourceBucket.getOrDefault(key, 0)
	}
	
	fun add(key: ResourceType, value: Int): Int {
		val newAmount = _resourceBucket.getOrDefault(key, 0) + value
		_resourceBucket[key] = newAmount
		return newAmount
	}
	
	fun addAll(from: Map<out ResourceType, Int>) {
		from.forEach() {
			add(it.key, it.value)
		}
	}
	
	fun take(key: ResourceType, value: Int): Int {
		val newAmount = _resourceBucket.getOrDefault(key, 0) - value
		_resourceBucket[key] = newAmount
		return newAmount
	}
	
	fun takeAll(from: Map<out ResourceType, Int>) {
		from.forEach() {
			add(it.key, it.value)
		}
	}
	
	fun applyModifiers(group: ModifierGroup, modifiables: Set<Modifiable>) {
		_resourceBucket.replaceAll() { type, amount ->
			group.applyModifiers(amount, type, modifiables).toInt()
		}
	}
	
}