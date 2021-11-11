package com.marshalldbrain.pulsar.core.util

import com.marshalldbrain.pulsar.core.modifiers.Modifiable
import com.marshalldbrain.pulsar.core.scope.modifier.ScopeMod

object HelperClasses {
	
	class TestScope: ScopeMod
	
	class TestModifiable(override val modId: String) : Modifiable
	
}