package com.eduardoagabes.investeasy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contribuicionMensual = findViewById<TextInputEditText>(R.id.tie_contribuicion_mensual)
        val cantidadMeses = findViewById<TextInputEditText>(R.id.tie_cantidad_meses)
        val interes = findViewById<TextInputEditText>(R.id.tie_interes)

        val btnCalculate = findViewById<Button>(R.id.btn_calcular)

        btnCalculate.setOnClickListener {
            val totalMensualStr = contribuicionMensual.text
            val mesesStr = cantidadMeses.text
            val totalInteresStr = interes.text

            if (totalMensualStr?.isEmpty() == true || mesesStr?.isEmpty() == true ||
                totalInteresStr?.isEmpty() == true
            ) {
                Snackbar.make(
                    contribuicionMensual,
                    "Rellene todos los campos",
                    Snackbar.LENGTH_LONG
                ).show()

            } else {
                val totalMensual = totalMensualStr.toString().toFloat()
                val meses = mesesStr.toString().toInt()
                val totalInteres = totalInteresStr.toString().toFloat()

                val interes: Float = totalInteres / 100
                val cantidadMensual: Float = (1f + interes).pow(meses) - 1
                val resultado: Float = totalMensual * (cantidadMensual / interes) * (1f + interes)

                val totalInvertido = totalMensual * meses
                val totalIngresos = resultado - totalInvertido

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("resultado", resultado)
                intent.putExtra("totalIngresos", totalIngresos)
                intent.putExtra("totalMensual", totalMensual)
                intent.putExtra("meses", meses)
                intent.putExtra("totalInteres", totalInteres)

                startActivity(intent)

            }
        }

        val totalMensualInput = findViewById<TextInputEditText>(R.id.tie_contribuicion_mensual)
        val mesesInput = findViewById<TextInputEditText>(R.id.tie_cantidad_meses)
        val interesInput = findViewById<TextInputEditText>(R.id.tie_interes)

        val btnLimpiar = findViewById<Button>(R.id.btn_limpiar)

        btnLimpiar.setOnClickListener {
            mesesInput.setText("")
            totalMensualInput.setText("")
            interesInput.setText("")
        }
    }
}


