package com.marshalldbrain.pulsar.core.empire.colony.construction

import com.marshalldbrain.pulsar.core.ion.round

class BuildCoordinator(
	private val onComplete: (BuildType, Buildable) -> Unit
) {

	private val queue = mutableListOf<BuildOrderImpl>()
	val buildQueue: List<BuildOrder>
		get() = queue.toList()
	
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
		val remaining: Double
	}
	
	private class BuildOrderImpl(
		override val type: BuildType,
		override val buildable: Buildable,
		amount: Int = 1
	): BuildOrder {
		
		override val remaining: Double
			get() = amount - (progress.toDouble()/type.getBuildTime(buildable)).round(3)
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