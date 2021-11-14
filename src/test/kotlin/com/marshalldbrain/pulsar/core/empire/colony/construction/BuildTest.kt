package com.marshalldbrain.pulsar.core.empire.colony.construction

import com.marshalldbrain.pulsar.core.util.HelperClasses
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class BuildTest : FunSpec({
	
	lateinit var builder: BuildCoordinator
	
	beforeEach {
		builder = BuildCoordinator { _, _ ->}
	}
	
	context("Single order") {
		
		test("Add order") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			
			builder.buildQueue.size shouldBe 1
		}
		
		test("Build single") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			builder.process(1)
			
			builder.buildQueue.size shouldBe 0
		}
		
		test("Build incomplete") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(2), 1)
			builder.process(1)
			
			builder.buildQueue.size shouldBe 1
			builder.buildQueue.first().remaining shouldBeGreaterThan 0.0
		}
		
		test("Overbuild") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			builder.process(2)
			
			builder.buildQueue.size shouldBe 0
		}
		
		test("Multiple amount") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 2)
			builder.process(2)
			
			builder.buildQueue.size shouldBe 0
		}
		
		test("Multiple amount incomplete") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 2)
			builder.process(1)
			
			builder.buildQueue.size shouldBe 1
			builder.buildQueue.first().remaining shouldBe 1
		}
		
	}
	
	context("Multiple orders") {
		
		test("Build single") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			builder.process(1)
			
			builder.buildQueue.size shouldBe 1
		}
		
		test("Overbuild single") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(2), 1)
			builder.process(2)
			
			builder.buildQueue.first().remaining shouldBe .5
		}
		
		test("Build multiple") {
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			builder.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			builder.process(2)
			
			builder.buildQueue.size shouldBe 0
		}
		
	}
	
	context("Callbacks") {
		
		test("On complete") {
			var callback = false
			val complete = BuildCoordinator { _, _ -> callback = true }
			
			complete.createOrder(BuildType.BUILD, HelperClasses.TestBuildable(1), 1)
			complete.process(1)
			
			callback shouldBe true
			
		}
		
	}

})
