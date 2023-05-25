package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    var percentual:Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
        supportActionBar?.hide()

        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            // Se o modo noturno está ativado, aplica o estilo do modo noturno
            setTheme(R.style.Theme_AlcoolOuGasolina)
        }
        savedInstanceState?.let {
            percentual = it.getDouble("percentual")
        }
        val btCalc = findViewById<Button>(R.id.btCalcular)
        val swPercentual = findViewById<Switch>(R.id.swPercentual)
        btCalc.setOnClickListener{btnCalcular(it)}
        swPercentual.setOnCheckedChangeListener { _, isChecked ->
            percentual = if (isChecked) 0.75 else 0.70
        }
        Log.d("PDM23","No onCreate, $percentual")



    }
override fun onResume(){
    super.onResume()
    Log.d("PDM23","No onResume, $percentual")
}
override fun onStart(){
    super.onStart()
    Log.d("PDM23","No onResume")
}
override fun onPause(){
    super.onPause()
    Log.d("PDM23","No onResume")
}
override fun onStop(){
    super.onStop()
    Log.d("PDM23","No onResume")
}
override fun onDestroy(){
    super.onDestroy()
    Log.d("PDM23","No onResume")
}
override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)

    outState.putDouble("percentual", percentual)
}
    fun btnCalcular(v: View) {
        val valGasolina = findViewById<EditText>(R.id.edGasolina)
        val valAlcool = findViewById<EditText>(R.id.edAlcool)
        val resultado = valGasolina.text.toString().toDouble() * percentual
        val isRentavel = if (resultado >= valAlcool.text.toString().toDouble()) "É rentável" else "Não é rentável"
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        Log.d("PDM23", "$isRentavel, Resultado: R$ ${String.format("%.2f", resultado)}")
        if (isRentavel == "É rentável") {
            tvResultado.setText(String.format("R$ %.2f - %s", resultado, isRentavel))
            tvResultado.setTextColor(getColor(R.color.nice_green))
        } else {
            tvResultado.setText(String.format("R$ %.2f - %s", resultado, isRentavel))
            tvResultado.setTextColor(getColor(R.color.nice_red))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Inicialize os elementos de interface do usuário novamente
        initializeUI()
    }

    private fun initializeUI() {
        val btCalc = findViewById<Button>(R.id.btCalcular)
        val swPercentual = findViewById<Switch>(R.id.swPercentual)

        btCalc.setOnClickListener { btnCalcular(it) }

        swPercentual.setOnCheckedChangeListener { _, isChecked ->
            percentual = if (isChecked) 0.75 else 0.70
        }
    }
}