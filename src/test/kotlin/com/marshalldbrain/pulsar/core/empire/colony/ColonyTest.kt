package com.marshalldbrain.pulsar.core.empire.colony

import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictMining
import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictType
import com.marshalldbrain.pulsar.core.resource.types.ResourceMineral
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe

class ColonyTest : FunSpec({
	
	test("District production") {
		
		val colony = Colony(
			mapOf(Pair(DistrictMining, 4))
		)
		
		val production = colony.getProduction()
		
		production.shouldContainExactly(
			mapOf(
				Pair(ResourceMineral, 16)
			)
		)
		
	}
	
	test("Build district") {
		
		val colony = Colony()
		colony.createBuildOrder(DistrictMining, 4)
		colony.tick(4)
		val production = colony.getProduction()
		
		production.shouldContainExactly(
			mapOf(
				Pair(ResourceMineral, 16)
			)
		)
		
	}

})
