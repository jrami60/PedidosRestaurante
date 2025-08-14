package com.pedidos.pedidosrestaurante.domain

import com.pedidos.pedidosrestaurante.modelo.Platillo


class PedidoMesa(
    private val pedidos: List<Platillo>,
    private val incluirPropina: Boolean
) {

    fun calcularSubtotal(): Int {
        return pedidos.sumOf { it.precio * it.cantidad }
    }

    fun calcularTotal(): Int {
        val subtotal = calcularSubtotal()
        return if (incluirPropina) (subtotal * 1.1).toInt() else subtotal
    }
}