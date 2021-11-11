package com.marshalldbrain.pulsar.core.resource

import com.marshalldbrain.pulsar.core.resources.types.ResourceMineral
import io.kotest.core.spec.style.FunSpec
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
	
})
