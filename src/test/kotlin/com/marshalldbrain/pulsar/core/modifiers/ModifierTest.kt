package com.marshalldbrain.pulsar.core.modifiers

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ModifierTest : FunSpec({
	
	test("Basic") {
		
		val group = ModifierGroup {
			it.create("pop", 0.1)
		}
		
		group.reduce("pop", 10.0) shouldBe 11
		
	}
	
	test("Empty") {
		
		val group = ModifierGroup {
		}
		
		group.reduce("pop", 10.0) shouldBe 10
	
	}
	
	context("Multiple") {
		
		test("All") {
			
			val group = ModifierGroup {
				it.create("pop", 0.1)
				it.create("star_blue.pop", 0.1)
				it.create("planet_desert.pop", 0.1)
				it.create("star_blue.planet_desert.pop", 0.1)
			}
			
			group.reduce("star_blue.planet_desert.pop", 10.0) shouldBe 14
			
		}
		
		test("Some") {
			
			val group = ModifierGroup {
				it.create("planet_tropical.pop", 0.1)
				it.create("star_blue.pop", 0.1)
				it.create("planet_desert.pop", 0.1)
				it.create("star_blue.planet_desert.pop", 0.1)
				it.create("star_purple.pop", 0.1)
				it.create("star_purple.planet_tropical.pop", 0.1)
				it.create("pop", 0.1)
			}
			
			group.reduce("star_blue.planet_desert.pop", 10.0) shouldBe 14
			
		}
		
		test("None") {
			
			val group = ModifierGroup {
				it.create("star_purple.pop", 0.1)
				it.create("star_purple.planet_tropical.pop", 0.1)
				it.create("planet_tropical.pop", 0.1)
				it.create("star_red.pop", 0.1)
				it.create("district.job.mine", 0.1)
			}
			
			group.reduce("pop", 10.0) shouldBe 10
			
		}
		
	}
	
})
