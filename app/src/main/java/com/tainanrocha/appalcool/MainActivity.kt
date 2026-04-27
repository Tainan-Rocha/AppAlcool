package com.tainanrocha.appalcool

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    // Declarando componentes que serao utilizados na interface do app
    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var botaoCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Funcao para inicializar componentes da interface e atribuirem as variaveis que criadas
        inicializarComponentesInterface()

        // Ao clicar no botao "Calcular" irá executar a acao abaixo de calcular precos
        botaoCalcular.setOnClickListener { calcularMelhorPreco() }

    }

    private fun calcularMelhorPreco() {

        // Convertendo inputs do usuario para string
        val precoAlcool = editAlcool.text.toString()
        val precoGasolina = editGasolina.text.toString()

        // Validacao se usuario digitou campos
        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)

        // Logica do calculo
        if (resultadoValidacao) {
            // Covertendo para double as strings capturadas
            val resultado = precoAlcool.toDouble() / precoGasolina.toDouble()

            if(resultado >= 0.7) {
                textResultado.text = "Compensa utilizar Gasolina"
            }else{
                textResultado.text = "Compensa utilizar Alcool"
            }
        }
    }

    private fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {

        textInputAlcool.error = null
        textInputGasolina.error = null

        // Ira apresentar excecao caso esteja vazio os campos e usuario tenha clicado em "Calcular"
        if(precoAlcool.isEmpty()) {
            textInputAlcool.error = "Digite o preco do alcool"
            return false

        }else if(precoGasolina.isEmpty()) {
            textInputGasolina.error = "Digite o preco da gasolina"
            return false
        }
        return true
    }

    // Inicializando e associando variaveis com seus respectivos componentes da interface
    private fun inicializarComponentesInterface() {

        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        botaoCalcular = findViewById(R.id.botao_calcular)
        textResultado = findViewById(R.id.texto_resultado)
    }

}