package com.marshalldbrain.pulsar.core.modifiers

import com.marshalldbrain.pulsar.core.resource.ResourceBucket
import com.marshalldbrain.pulsar.core.scope.modifier.ScopeMod
import com.marshalldbrain.pulsar.core.util.HelperClasses
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ModifiersTest : FunSpec({
	
	val scope1 = HelperClasses.TestScope()
	val modRoot = HelperClasses.TestModifiable("root")
	val mod1 = HelperClasses.TestModifiable("mod 1")
	val mod2 = HelperClasses.TestModifiable("mod 2")
	val mod3 = HelperClasses.TestModifiable("mod 3")
	
	context("No limiters") {
		
		test("Correct scope") {
			
			val group = Modifiers.createGroup(scope1)
			val cell = Modifiers.createModifier(scope1, modRoot)
			cell.multiplier = 0.1
			
			group.applyModifiers(10.0, modRoot, emptySet()) shouldBe 11
			
		}
		
		test("Incorrect scope") {
			
			val scope2 = HelperClasses.TestScope()
			
			val group = Modifiers.createGroup(scope2)
			Modifiers.createModifier(scope1, modRoot).multiplier = 0.1
			
			group.applyModifiers(10.0, modRoot, emptySet()) shouldBe 10
			
		}
		
	}
	
	context("Limiters") {
		
		test("Single apply") {
			
			val group = Modifiers.createGroup(scope1)
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1, mod2, mod3
			)).multiplier = 0.1
			
			group.applyModifiers(10.0, modRoot, setOf(mod1, mod2, mod3)) shouldBe 11
			
		}
		
		test("All limiters") {
			
			val group = Modifiers.createGroup(scope1)
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1, mod2
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod2, mod3
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1, mod2, mod3
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot).multiplier = 0.1
			
			group.applyModifiers(10.0, modRoot, setOf(mod1, mod2, mod3)) shouldBe 15
			
		}
		
		test("Some apply") {
			
			val group = Modifiers.createGroup(scope1)
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1, mod2
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod2, mod3
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1, mod2, mod3
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot).multiplier = 0.1
			
			group.applyModifiers(10.0, modRoot, setOf(mod1, mod2)) shouldBe 13
			
		}
		
		test("None apply") {
			
			val group = Modifiers.createGroup(scope1)
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1, mod2
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod2, mod3
			)).multiplier = 0.1
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1, mod2, mod3
			)).multiplier = 0.1
			
			group.applyModifiers(10.0, modRoot, emptySet()) shouldBe 10
			
		}
		
	}
	
	context("Parent groups") {
		
		val scope2 = HelperClasses.TestScope()
		
		test("Correct application") {
			
			val group1 = Modifiers.createGroup(scope1)
			val group2 = group1.createChildGroup(scope2)
			
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1
			)).multiplier = 0.1
			Modifiers.createModifier(scope2, modRoot, setOf(
				mod2
			)).multiplier = 0.1
			
			group2.applyModifiers(10.0, modRoot, setOf(
				mod1, mod2
			)) shouldBe 12
			
		}
		
		test("Parents not modified") {
			
			val group1 = Modifiers.createGroup(scope1)
			group1.createChildGroup(scope2)
			
			Modifiers.createModifier(scope1, modRoot, setOf(
				mod1
			)).multiplier = 0.1
			Modifiers.createModifier(scope2, modRoot, setOf(
				mod2
			)).multiplier = 0.1
			
			group1.applyModifiers(10.0, modRoot, setOf(
				mod1, mod2
			)) shouldBe 11
			
		}
		
	}
	
})
