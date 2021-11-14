package com.marshalldbrain.pulsar.core.empire.colony.construction

enum class BuildType {
	BUILD {
		override fun getBuildTime(buildable: Buildable): Int = buildable.buildTime
		override fun normalizeAmount(amount: Int): Int = amount
	},
	DESTROY {
		override fun getBuildTime(buildable: Buildable): Int = TODO("Not yet implemented")
		override fun normalizeAmount(amount: Int): Int = TODO("Not yet implemented")
	},
	REPLACE {
		override fun getBuildTime(buildable: Buildable): Int = TODO("Not yet implemented")
		override fun normalizeAmount(amount: Int): Int = TODO("Not yet implemented")
	},
	UPGRADE {
		override fun getBuildTime(buildable: Buildable): Int = TODO("Not yet implemented")
		override fun normalizeAmount(amount: Int): Int = TODO("Not yet implemented")
	},
	DOWNGRADE {
		override fun getBuildTime(buildable: Buildable): Int = TODO("Not yet implemented")
		override fun normalizeAmount(amount: Int): Int = TODO("Not yet implemented")
	},
	TOOL {
		override fun getBuildTime(buildable: Buildable): Int = TODO("Not yet implemented")
		override fun normalizeAmount(amount: Int): Int = TODO("Not yet implemented")
	};
	
	abstract fun getBuildTime(buildable: Buildable): Int
	abstract fun normalizeAmount(amount: Int): Int
	
}