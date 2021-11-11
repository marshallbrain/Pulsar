package com.marshalldbrain.pulsar.core.empire.colony.construction

import kotlin.math.max

class BuildOrder(
	val buildable: Buildable,
	amount: Int = 1
) {
	
	var remaining: Int = amount
		private set
	var progress: Int = 0
		private set
	
	fun process(build: Int): Int {
		progress += build
		return progress - buildable.cost
	}
	
	fun build(): Boolean {
		progress = 0
		return --remaining == 0
	}
	
}