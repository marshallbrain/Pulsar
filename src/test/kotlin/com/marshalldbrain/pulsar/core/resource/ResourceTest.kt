package com.marshalldbrain.pulsar.core.resource

import com.marshalldbrain.pulsar.core.modifiers.Modifiers
import com.marshalldbrain.pulsar.core.resource.types.ResourceMineral
import com.marshalldbrain.pulsar.core.util.HelperClasses
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe

class ResourceTest : FunSpec({
	
	test("Add resources") {
		
		val resourceBucket = ResourceBucket()
		resourceBucket.add(ResourceMineral, 5)
		resourceBucket.add(ResourceMineral, 5)
		
		resourceBucket.get(ResourceMineral) shouldBe 10
		
	}
	
	test("Add all resources") {
		
		val resourceBucket = ResourceBucket()
		resourceBucket.add(ResourceMineral, 5)
		resourceBucket.add(ResourceMineral, 5)
		
		resourceBucket.get(ResourceMineral) shouldBe 10
		
	}
	
	test("Take resources") {
		
		val resourceBucket = ResourceBucket()
		resourceBucket.add(ResourceMineral, 5)
		resourceBucket.take(ResourceMineral, 5)
		
		resourceBucket.get(ResourceMineral) shouldBe 0
		
	}
	
	test("Take all resources") {
		
		val resourceBucket = ResourceBucket()
		resourceBucket.add(ResourceMineral, 5)
		resourceBucket.add(ResourceMineral, 5)
		
		resourceBucket.get(ResourceMineral) shouldBe 10
		
	}
	
	test("Apply modifiers") {
		
		val scope = HelperClasses.TestScope()
		val group = Modifiers.createGroup(scope)
		val resourceBucket = ResourceBucket()
		Modifiers.createModifier(scope, ResourceMineral).multiplier = 0.1
		resourceBucket.add(ResourceMineral, 10)
		
		resourceBucket.applyModifiers(group, emptySet())
		
		resourceBucket.resourceBucket shouldContain Pair(ResourceMineral, 11)
		resourceBucket.resourceBucket.size shouldBe 1
		
	}
	
})
