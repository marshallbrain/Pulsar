package com.marshalldbrain.pulsar.core.empire.colony.districts.types

import com.marshalldbrain.pulsar.core.resource.types.ResourceMineral
import com.marshalldbrain.pulsar.core.resource.types.ResourceType

object DistrictMining: DistrictType {
	override val id = "mining"
	override val production: Map<ResourceType, Int> = mapOf(
		Pair(ResourceMineral, 4)
	)
	override val cost: Int = 1
	
}