package com.marshalldbrain.pulsar.core.empire.colony.districts.types

import com.marshalldbrain.pulsar.core.empire.colony.construction.Buildable
import com.marshalldbrain.pulsar.core.resource.types.ResourceType

sealed interface DistrictType: Buildable {
	val id: String
	val production: Map<ResourceType, Int>
}