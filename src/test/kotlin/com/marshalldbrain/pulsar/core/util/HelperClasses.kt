package com.marshalldbrain.pulsar.core.util

import com.marshalldbrain.pulsar.core.empire.colony.construction.Buildable
import com.marshalldbrain.pulsar.core.modifiers.Modifiable
import com.marshalldbrain.pulsar.core.scope.modifier.ScopeMod

object HelperClasses {
	
	class TestScope: ScopeMod
	class TestModifiable(override val id: String): Modifiable
	class TestBuildable(override val cost: Int): Buildable
	
}