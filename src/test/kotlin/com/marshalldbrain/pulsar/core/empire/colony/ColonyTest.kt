package com.marshalldbrain.pulsar.core.empire.colony

import com.marshalldbrain.pulsar.core.empire.colony.construction.BuildType
import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictMining
import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictType
import com.marshalldbrain.pulsar.core.resource.types.ResourceMineral
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe

class ColonyTest : FunSpec({
	
	context("Colony creation") {
		
		test("district") {
			val colony = Colony(mapOf(Pair(DistrictMining, 1)))
			colony.tick(1)
			colony.getProduction() shouldContainExactly mapOf(
				Pair(ResourceMineral, 4)
			)
		}
		
	}
	
	context("District orders") {
		
		test("Build district") {
			val colony = Colony()
			colony.createBuildOrder(BuildType.BUILD, DistrictMining, 1)
			colony.tick(1)
			colony.getProduction() shouldContainExactly mapOf(
				Pair(ResourceMineral, 4)
			)
		}
		
	}

})
