package com.eduardoagabes.investeasy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eduardoagabes.investeasy.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow


class MainActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contribuicionMensual = binding.tieContribuicionMensual
        val cantidadMeses = binding.tieCantidadMeses
        val interes = binding.tieInteres

        val btnCalculate = binding.btnCalcular

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

                val interesPorcentual: Float = totalInteres / 100
                val cantidadMensual: Float = (1f + interesPorcentual).pow(meses) - 1
                val resultado: Float = totalMensual * (cantidadMensual / interesPorcentual) * (1f + interesPorcentual)

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

        val totalMensualInput = binding.tieContribuicionMensual
        val mesesInput = binding.tieCantidadMeses
        val interesInput = binding.tieInteres

        val btnLimpiar = binding.btnLimpiar

        btnLimpiar.setOnClickListener {
            mesesInput.setText("")
            totalMensualInput.setText("")
            interesInput.setText("")
        }
    }
}


