package com.marshalldbrain.pulsar.core.empire.colony.construction

class BuildCoordinator {

	private val _buildQueue = mutableListOf<BuildOrder>()
	val buildQueue: List<BuildOrder>
		get() = _buildQueue
	
	fun addOrder(order: BuildOrder) {
		_buildQueue.add(order)
	}
	
	fun process(amount: Int) {
		var remaining = amount
		while (remaining > 0 && _buildQueue.isNotEmpty()) {
			remaining = _buildQueue[0].process(remaining)
			if (remaining >= 0) {
				if(_buildQueue[0].build()) {
					_buildQueue.removeFirst()
				}
			}
		}
	}

}