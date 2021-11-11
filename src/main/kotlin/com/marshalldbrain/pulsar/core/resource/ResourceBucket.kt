package com.marshalldbrain.pulsar.core.resource

import com.marshalldbrain.pulsar.core.modifiers.ModifierGroup
import com.marshalldbrain.pulsar.core.resources.types.ResourceType

class ResourceBucket {

	private val _resourceBucket = mutableMapOf<ResourceType, Int>()
	val resourceBucket: Map<ResourceType, Int>
		get() = _resourceBucket
	
	operator fun plus(pair: Pair<ResourceType, Int>) {
		add(pair.first, pair.second)
	}
	
	operator fun plus(resources: Map<ResourceType, Int>) {
		addAll(resources)
	}
	
	operator fun minus(pair: Pair<ResourceType, Int>) {
		take(pair.first, pair.second)
	}
	
	operator fun minus(resources: Map<ResourceType, Int>) {
		takeAll(resources)
	}
	
	fun get(key: ResourceType): Int? {
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
	
}