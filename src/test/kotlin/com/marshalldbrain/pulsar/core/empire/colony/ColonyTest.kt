package com.marshalldbrain.pulsar.core.empire.colony

import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictMining
import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictType
import com.marshalldbrain.pulsar.core.resource.types.ResourceMineral
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe

class ColonyTest : FunSpec({
	
	test("District creation") {
		
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

})
