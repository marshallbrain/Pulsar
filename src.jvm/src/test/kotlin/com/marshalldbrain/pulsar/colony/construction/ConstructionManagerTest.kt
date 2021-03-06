package com.marshalldbrain.pulsar.colony.construction

import com.marshalldbrain.pulsar.resources.ResourceBucket
import com.marshalldbrain.pulsar.resources.ResourceTeller
import com.marshalldbrain.pulsar.resources.ResourceType
import io.kotlintest.DisplayName
import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.collections.shouldNotBeEmpty
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.mockk.every
import io.mockk.mockk


class ConstructionManagerTest : FunSpec({
	
	context("Process time") {
		
		test("Equal time passed") {
		
			val cm = ConstructionManager(mockk(relaxUnitFun = true))
			val task = ConstructionTask("", emptyMap(), 5, 1)
			
			cm.addToQueue(task)
			
			cm.active.shouldContainExactly(task)
			cm.tick(5)
			cm.active.shouldBeEmpty()
			
		}
		
		test("0 time instantly finishes") {
			
			val cm = ConstructionManager(mockk(relaxUnitFun = true))
			val task = ConstructionTask("", emptyMap(), 0, 1)
			
			cm.addToQueue(task)
			
			cm.active.shouldBeEmpty()
			
		}
		
		test("Task with multiple amount") {
			
			val cm = ConstructionManager(mockk(relaxUnitFun = true))
			val task = ConstructionTask("", emptyMap(), 5, 4)
			
			cm.addToQueue(task)
			cm.tick(18)
			
			cm.queue.shouldBeEmpty()
			cm.active.shouldHaveSize(1)
			cm.active[0].amountRemaining shouldBe 1
			cm.active[0].timeRemaining shouldBe 2
			
		}
		
		test("Time rolled over") {
			
			val cm = ConstructionManager(mockk(relaxUnitFun = true))
			val tasks = listOf(
				ConstructionTask("", emptyMap(), 5, 1),
				ConstructionTask("", emptyMap(), 5, 1)
			)
			
			tasks.forEach { cm.addToQueue(it) }
			cm.tick(7)
			
			cm.queue.shouldBeEmpty()
			cm.active.shouldHaveSize(1)
			cm.active[0].timeRemaining shouldBe 3
			
		}
		
	}
	
	test("OnComplete triggers") {
		
		var complete = false
		
		val cm = ConstructionManager(mockk(relaxUnitFun = true))
		val task = ConstructionTask("", emptyMap(), 5, 1)
		{ complete = true }
		
		cm.addToQueue(task)
		cm.tick(5)
		
		cm.active.shouldBeEmpty()
		complete shouldBe true
		
	}
	
	test("OnComplete triggers multiple times") {
		
		var number = 0
		
		val cm = ConstructionManager(mockk(relaxUnitFun = true))
		val task = ConstructionTask("", emptyMap(), 5, 4)
		{ number++ }
		
		cm.addToQueue(task)
		cm.tick(20)
		
		cm.active.shouldBeEmpty()
		number shouldBe 4
		
	}
	
})