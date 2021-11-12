package com.marshalldbrain.pulsar.core.empire.colony

import com.marshalldbrain.pulsar.core.empire.colony.construction.BuildCoordinator
import com.marshalldbrain.pulsar.core.empire.colony.construction.BuildOrder
import com.marshalldbrain.pulsar.core.empire.colony.construction.Buildable
import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictType
import com.marshalldbrain.pulsar.core.resource.types.ResourceType

class Colony(
	districts: Map<DistrictType, Int> = mapOf()
) {
	
	private val builder = BuildCoordinator(this::build)
	private val districts = districts.toMutableMap()
	
	var population = 0
	
	fun createBuildOrder(buildable: Buildable, amount: Int) {
		builder.addOrder(BuildOrder(buildable, amount))
	}
	
	fun tick(delta: Int) {
		builder.process(delta)
	}
	
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
	
	private fun build(buildable: Buildable) {
		when(buildable) {
			is DistrictType -> districts[buildable] =
				districts.getOrDefault(buildable, 0) + 1
		}
	}
	
	companion object {
		private val maxPopulation = 10
		private val maxDistricts = 4
		private val maxBuildings = 10
	}
	
}