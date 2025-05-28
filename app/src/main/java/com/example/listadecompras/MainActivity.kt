package com.example.listadecompras

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var itemCount = 0  // Para controlar o número sequencial dos itens

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemAdapter()
        recyclerView.adapter = itemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val editTextNome = findViewById<EditText>(R.id.editTextNome)
        val editTextQuantidade = findViewById<EditText>(R.id.editTextQuantidade)
        val editTextPreco = findViewById<EditText>(R.id.editTextPreco)
        val editTextDescricao = findViewById<EditText>(R.id.editTextDescricao)

        val button = findViewById<Button>(R.id.buttonAdicionar)
        button.setOnClickListener {
            val nome = editTextNome.text.toString()
            val quantidade = editTextQuantidade.text.toString()
            val preco = editTextPreco.text.toString()
            val descricao = editTextDescricao.text.toString()

            if (nome.isEmpty() || quantidade.isEmpty() || preco.isEmpty() || descricao.isEmpty()) {
                if (nome.isEmpty()) editTextNome.error = "Preencha o nome"
                if (quantidade.isEmpty()) editTextQuantidade.error = "Preencha a quantidade"
                if (preco.isEmpty()) editTextPreco.error = "Preencha o preço"
                if (descricao.isEmpty()) editTextDescricao.error = "Preencha a descrição"
                return@setOnClickListener
            }

            itemCount++

            val item = ItemModel(
                numero = itemCount,
                name = nome,
                quantidade = quantidade.toInt(),
                preco = preco.toDouble(),
                descricao = descricao
            )

            itemsAdapter.addItem(item)

            editTextNome.text.clear()
            editTextQuantidade.text.clear()
            editTextPreco.text.clear()
            editTextDescricao.text.clear()
        }
    }
}
