package com.marshalldbrain.pulsar.core.empire.colony.construction

import com.marshalldbrain.pulsar.core.util.HelperClasses
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class BuildTest : FunSpec({
	
	context("Single order") {
		
		test("Add order") {
			
			val buildable = BuildOrder(HelperClasses.TestBuildable(1))
			val build = BuildCoordinator {}
			
			build.addOrder(buildable)
			
			build.buildQueue.size shouldBe 1
			
		}
		
		test("Build single") {
			
			val buildable = BuildOrder(HelperClasses.TestBuildable(1))
			val build = BuildCoordinator {}
			
			build.addOrder(buildable)
			build.process(1)
			
			buildable.remaining shouldBe 0
			build.buildQueue.size shouldBe 0
			
		}
		
		test("Build incomplete") {
			
			val buildable = BuildOrder(HelperClasses.TestBuildable(2))
			val build = BuildCoordinator {}
			
			build.addOrder(buildable)
			build.process(1)
			
			buildable.progress shouldBe 1
			buildable.remaining shouldBe 1
			build.buildQueue.size shouldBe 1
			
		}
		
		test("Overbuild") {
			
			val buildable = BuildOrder(HelperClasses.TestBuildable(2),2)
			val build = BuildCoordinator {}
			
			build.addOrder(buildable)
			build.process(3)
			
			buildable.progress shouldBe 1
			
		}
		
		test("Multiple amount") {
			
			val buildable = BuildOrder(HelperClasses.TestBuildable(1), 2)
			val build = BuildCoordinator {}
			
			build.addOrder(buildable)
			build.process(3)
			
			build.buildQueue.size shouldBe 0
			
		}
		
		test("Multiple amount overbuild") {
			
			val buildable = BuildOrder(HelperClasses.TestBuildable(1), 3)
			val build = BuildCoordinator {}
			
			build.addOrder(buildable)
			build.process(2)
			
			build.buildQueue.size shouldBe 1
			
		}
		
	}
	
	context("Multiple orders") {
		
		test("Build single") {
			
			val buildable1 = BuildOrder(HelperClasses.TestBuildable(1))
			val buildable2 = BuildOrder(HelperClasses.TestBuildable(1))
			val build = BuildCoordinator {}
			
			build.addOrder(buildable1)
			build.addOrder(buildable2)
			build.process(1)
			
			build.buildQueue.size shouldBe 1
			
		}
		
		test("Overbuild single") {
			
			val buildable1 = BuildOrder(HelperClasses.TestBuildable(1))
			val buildable2 = BuildOrder(HelperClasses.TestBuildable(2))
			val build = BuildCoordinator {}
			
			build.addOrder(buildable1)
			build.addOrder(buildable2)
			build.process(2)
			
			buildable2.progress shouldBe 1
			
		}
		
		test("Build multiple") {
			
			
			val buildable1 = BuildOrder(HelperClasses.TestBuildable(1))
			val buildable2 = BuildOrder(HelperClasses.TestBuildable(1))
			val build = BuildCoordinator {}
			
			build.addOrder(buildable1)
			build.addOrder(buildable2)
			build.process(2)
			
			build.buildQueue.size shouldBe 0
			
		}
		
	}

})
