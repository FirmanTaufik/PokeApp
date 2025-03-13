package firmanpoke.com.apps.ui.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import firmanpoke.com.apps.databinding.ItemBinding
import firmanpoke.com.apps.helper.Constant
import firmanpoke.com.apps.helper.startNewActivity
import firmanpoke.com.apps.ui.presentation.detail.DetailActivity

class ItemAdapter(private val items: List<HomeModelResponse.Pokemons>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeModelResponse.Pokemons) {
            binding.tvTitle.text = item.name
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.apply {
            bind(items[position])
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constant.Pokemon.NAME, items[position].name)
                bundle.putString(Constant.Pokemon.URL, items[position].url)
                itemView.context.startNewActivity<DetailActivity>(bundle)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
