package com.marshalldbrain.pulsar.core.empire.colony.districts.types

import com.marshalldbrain.pulsar.core.ion.ID
import com.marshalldbrain.pulsar.core.resources.types.ResourceMineral
import com.marshalldbrain.pulsar.core.resources.types.ResourceType

object DistrictMining: DistrictType {
	override val id = "mining"
	override val production: Map<ResourceType, Int> = mapOf(
		Pair(ResourceMineral, 4)
	)
	
}