package com.marshalldbrain.pulsar.core.empire.colony

import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictType
import com.marshalldbrain.pulsar.core.resource.types.ResourceType

class Colony(
	val districts: Map<DistrictType, Int>
) {
	
	val maxPopulation = 0
	val maxDistricts = 0
	val maxBuildings = 0
	
	var population = 0
	
	fun getProduction(): Map<ResourceType, Int> {
		
		val resourceMap = mutableMapOf<ResourceType, Int>()
		districts.forEach { district ->
			district.key.production.forEach { resource ->
				resourceMap.compute(resource.key) { _, v ->
					if (v == null)
						district.value * resource.value
					else
						v + district.value * resource.value
				}
			}
		}
		return resourceMap
	}
	
}