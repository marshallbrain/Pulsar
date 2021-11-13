package com.marshalldbrain.pulsar.core.empire.colony.construction

import com.marshalldbrain.pulsar.core.empire.colony.districts.types.DistrictType

class BuildCoordinator(
	private val onComplete: (BuildType, Buildable) -> Unit
) {

	private val queue = mutableListOf<BuildOrderImpl>()
	
	fun createOrder(type: BuildType, buildable: Buildable, amount: Int) {
		queue.add(BuildOrderImpl(type, buildable, amount))
	}
	
	fun process(amount: Int) {
		var remaining = amount
		while (remaining > 0 && queue.isNotEmpty()) {
			remaining = queue[0].process(remaining)
			if (remaining >= 0) {
				onComplete.invoke(queue[0].type, queue[0].buildable)
				if(queue[0].build()) {
					queue.removeFirst()
				}
			}
		}
	}
	
	interface BuildOrder {
		val type: BuildType
		val buildable: Buildable
	}
	
	private class BuildOrderImpl(
		override val type: BuildType,
		override val buildable: Buildable,
		amount: Int = 1
	): BuildOrder {
		
		private var amount: Int = type.normalizeAmount(amount)
		private var progress: Int = 0
		
		fun process(build: Int): Int {
			progress += build
			return progress - type.getBuildTime(buildable)
		}
		
		fun build(): Boolean {
			progress = 0
			return --amount == 0
		}
		
	}
	
}