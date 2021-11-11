package com.marshalldbrain.pulsar.core.empire.colony.districts.types

import com.marshalldbrain.pulsar.core.resources.types.ResourceType

interface DistrictType {
	val id: String
	val production: Map<ResourceType, Int>
}