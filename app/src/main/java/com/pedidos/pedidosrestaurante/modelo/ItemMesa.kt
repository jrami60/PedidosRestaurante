package com.pedidos.restaurante.modelo

class ItemMesa(val itemMenu: ItemMenu) {
    var cantidad: Int = 0

    fun calcularSubtotal(): Int = cantidad * itemMenu.precio
    fun calcularPropina(): Int = (calcularSubtotal() * 0.1).toInt()
    fun calcularTotalFinal(conPropina: Boolean): Int =
        calcularSubtotal() + if (conPropina) calcularPropina() else 0
}