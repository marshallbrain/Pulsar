package com.marshalldbrain.pulsar.core.empire.colony.districts.types

import com.marshalldbrain.pulsar.core.resources.types.ResourceMineral

object DistrictMining : DistrictType(
	"mining",
	mapOf(
		Pair(ResourceMineral, 4)
	)
)