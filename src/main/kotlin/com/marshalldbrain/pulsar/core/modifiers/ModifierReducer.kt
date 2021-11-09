package com.marshalldbrain.pulsar.core.modifiers

object ModifierReducer {
	
	fun reduce(
		modifierList: Map<String, Map<String, Double>>,
		targetModifier: String,
		amount: Double
	) : Double {
		
		val modifiers = modifierList.run {
			val targetSplit = targetModifier.split(".")
			getOrDefault(targetSplit.last(), emptyMap())
				.filter {
					val split = it.key.split(".")
					targetSplit.dropLast(1).containsAll(split) || it.key.isEmpty()
				}
				.values
		}
		
		return modifiers.fold (amount) { t, v ->
			amount*v + t
		}
		
	}
	
	/*
	
	{
		modifier {
			limiter {
				#
			}
			limiter {
				#
			}
			{
				#
			}
		}
	}
	
	 */
	
}