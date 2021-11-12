package com.marshalldbrain.pulsar.core.resource.types

import com.marshalldbrain.pulsar.core.modifiers.Modifiable

interface ResourceType: Modifiable {
	override val id: String
}