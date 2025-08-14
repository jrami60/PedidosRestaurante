package com.pedidos.pedidosrestaurante

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pedidos.pedidosrestaurante.domain.PedidoMesa
import com.pedidos.pedidosrestaurante.modelo.Platillo

class MainActivity : AppCompatActivity() {

    private lateinit var editTextCantidadCazuela: EditText
    private lateinit var editTextCantidadPastel: EditText
    private lateinit var switchPropina: Switch
    private lateinit var textSubtotal: TextView
    private lateinit var textTotalFinal: TextView

    private val precioCazuela = 10000
    private val precioPastel = 12500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextCantidadCazuela = findViewById(R.id.editTextCantidadCazuela)
        editTextCantidadPastel = findViewById(R.id.editTextCantidadPastel)
        switchPropina = findViewById(R.id.switchPropinaGeneral)
        textSubtotal = findViewById(R.id.textSubtotalGeneral)
        textTotalFinal = findViewById(R.id.textTotalFinal)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calcularTotales()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        editTextCantidadCazuela.addTextChangedListener(textWatcher)
        editTextCantidadPastel.addTextChangedListener(textWatcher)
        switchPropina.setOnCheckedChangeListener { _, _ -> calcularTotales() }

        calcularTotales()
    }

    private fun calcularTotales() {
        val cantidadCazuela = editTextCantidadCazuela.text.toString().toIntOrNull() ?: 0
        val cantidadPastel = editTextCantidadPastel.text.toString().toIntOrNull() ?: 0

        val pedidos = listOf(
            Platillo("Cazuela", precioCazuela, cantidadCazuela),
            Platillo("Pastel de Choclo", precioPastel, cantidadPastel)
        )

        val pedidoMesa = PedidoMesa(pedidos, switchPropina.isChecked)

        val subtotal = pedidoMesa.calcularSubtotal()
        val totalFinal = pedidoMesa.calcularTotal()

        textSubtotal.text = "Subtotal: $$subtotal"
        textTotalFinal.text = "Total final: $$totalFinal"
    }
}