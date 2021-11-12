package com.marshalldbrain.pulsar.core.modifiers

class ModifierCalc {
	
	private val modifiers = mutableListOf<MutableCellImpl>()
	var cell: Cell = Cell()
		private set
	
	private fun onModify() {
		cell = modifiers.fold(Cell()) { c, v -> v + c}
	}
	
	fun createCell(): MutableCell {
		return MutableCellImpl().also { modifiers.add(it) }
	}
	
	class Cell (
		val multiplier: Double = 0.0,
		val additive: Int = 0
	) {
		operator fun plus(cell: Cell): Cell {
			return Cell(
				multiplier + cell.multiplier,
				additive + cell.additive
			)
		}
		
		operator fun times(value: Number): Double {
			return (additive + value.toDouble()) * multiplier
		}
	}
	
	interface MutableCell {
		
		var multiplier: Double
		var additive: Int
		
	}
	
	private inner class MutableCellImpl: MutableCell {
		
		override var multiplier: Double = 0.0
			set(value) {
				field = value
				onModify()
			}
		override var additive: Int = 0
			set(value) {
				field = value
				onModify()
			}
		
		operator fun plus(cell: Cell): Cell {
			return Cell(
				multiplier + cell.multiplier,
				additive + cell.additive
			)
		}
		
	}
	
}