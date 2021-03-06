package com.marshalldbrain.pulsar.resources

interface ResourceTeller {
	
	val incomeUpdater: ResourceUpdater
	
	fun deposit(amount: Pair<ResourceType, Int>)
	fun depositAll(amount: Map<ResourceType, Int>)
	fun withdraw(amount: Pair<ResourceType, Int>)
	fun withdrawAll(amount: Map<ResourceType, Int>)
	
}