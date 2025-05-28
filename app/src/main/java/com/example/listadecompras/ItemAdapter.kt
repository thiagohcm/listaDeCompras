package com.example.listadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val items = mutableListOf<ItemModel>()

    fun addItem(newItem: ItemModel) {
        items.add(newItem)
        sortItems()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            items.removeAt(position)
            sortItems()
            notifyDataSetChanged()
        }
    }

    private fun sortItems() {
        items.sortBy { it.name }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.deleteButton.setOnClickListener {
            removeItem(holder.bindingAdapterPosition)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewNumero: TextView = itemView.findViewById(R.id.textViewNumero)
        private val textViewNome: TextView = itemView.findViewById(R.id.textViewNome)
        private val textViewQuantidade: TextView = itemView.findViewById(R.id.textViewQuantidade)
        private val textViewPreco: TextView = itemView.findViewById(R.id.textViewPreco)
        private val textViewDescricao: TextView = itemView.findViewById(R.id.textViewDescricao)
        val deleteButton: ImageButton = itemView.findViewById(R.id.buttonDelete)

        fun bind(item: ItemModel) {
            textViewNumero.text = "Número: ${item.numero}"
            textViewNome.text = "Nome: ${item.name}"
            textViewQuantidade.text = "Quantidade: ${item.quantidade}"
            textViewPreco.text = "Preço: R$ ${item.preco}"
            textViewDescricao.text = "Descrição: ${item.descricao}"
        }
    }
}
