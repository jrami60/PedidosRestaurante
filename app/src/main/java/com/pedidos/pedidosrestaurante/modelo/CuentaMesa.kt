package com.pedidos.restaurante.modelo

class CuentaMesa {
    private val items = mutableListOf<ItemMesa>()

    fun agregarItem(item: ItemMesa) {
        items.add(item)
    }

    fun calcularTotalSinPropina(): Int = items.sumOf { it.calcularSubtotal() }
    fun calcularPropinaTotal(): Int = items.sumOf { it.calcularPropina() }
    fun calcularTotalFinal(): Int = calcularTotalSinPropina() + calcularPropinaTotal()
}