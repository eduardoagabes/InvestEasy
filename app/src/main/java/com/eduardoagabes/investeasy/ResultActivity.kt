package com.eduardoagabes.investeasy

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultado = intent.getFloatExtra("resultado", 0f)
        val totalIngresos = intent.getFloatExtra("totalIngresos", 0f)
        val totalMensual = intent.getFloatExtra("totalMensual", 0f)
        val meses = intent.getIntExtra("meses", 0)
        val totalInteres = intent.getFloatExtra("totalInteres", 0f)


        val formattedResult = NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(resultado)
        val formattedIngresos = NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(totalIngresos)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvIngresos = findViewById<TextView>(R.id.tv_valor_ingresos)
        val tvContribucionMensual = findViewById<TextView>(R.id.tv_contribucion_mensual)
        val tvCantidadMeses = findViewById<TextView>(R.id.tv_cantidad_meses)
        val tvInteres = findViewById<TextView>(R.id.tv_interes)

        tvResult.text = formattedResult.toString()
        tvIngresos.text = formattedIngresos.toString()
        tvContribucionMensual.text = totalMensual.toString()
        tvCantidadMeses.text = meses.toString()
        tvInteres.text = totalInteres.toString()



    }
}