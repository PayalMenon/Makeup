package com.example.orchid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.orchid.R
import com.example.orchid.data.Product
import com.example.orchid.databinding.ProductItemBinding

class ProductAdapter (private val clickListener: (Product) -> Unit) : Adapter<ProductViewHolder>() {

    var items: List<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false).root
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(items[position], clickListener)
    }

    fun setProducts(list: List<Product>) {
        val oldItems = items
        items = list
        val result = DiffUtil.calculateDiff(ItemsDiffCallback(oldItems, list))
        result.dispatchUpdatesTo(this)
    }
}

class ProductViewHolder(private val itemView: View) : ViewHolder(itemView) {

    private val binding: ProductItemBinding = DataBindingUtil.bind(itemView)!!

    fun bindView(product: Product, clickListener: (Product) -> Unit){
        binding.name = product.brand
        binding.type = product.product_type
        Glide.with(itemView.context)
            .load(product.image_link)
            .dontAnimate()
            .into(binding.productImage)
        binding.productParent.setOnClickListener {
            clickListener(product)
        }
    }
}

class ItemsDiffCallback(
    private val oldList: List<Product>,
    private val newList: List<Product>,
): DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}