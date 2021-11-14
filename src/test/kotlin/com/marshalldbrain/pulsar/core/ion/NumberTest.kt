package com.marshalldbrain.pulsar.core.ion

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NumberTest : FunSpec({
	
	context("round") {
		
		test("no precision") {
			314159265.358979323846.round() shouldBe 314159265
		}
		
		test("precision") {
			314159.265358979323846.round(3) shouldBe 314159.265
		}
		
	}
	
})
