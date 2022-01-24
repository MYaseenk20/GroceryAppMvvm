package com.example.groceryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grocery_rv_item.view.*

class GroceryItemAdaptor(val listener : onClick):RecyclerView.Adapter<GroceryItemViewHolder>() {
    var GroceryItemList = ArrayList<GroceryItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_item,parent,false)
        return GroceryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryItemViewHolder, position: Int) {
        val currentPostion = GroceryItemList[position]
        holder.Itemname.text = currentPostion.ItemName
        holder.Quantity.text = currentPostion.ItemQuantity.toString()
        holder.Rate.text = "Rs "+currentPostion.ItemPrice.toString()
        val TotalAmount : Int = currentPostion.ItemQuantity * currentPostion.ItemPrice
        holder.totalAmt.text = "Rs" + TotalAmount.toString()
        holder.DeleteItem.setOnClickListener {
            listener.OnItemClick(currentPostion)
        }


    }

    override fun getItemCount(): Int {
        return GroceryItemList.size
    }
    fun OngroceryItem(updateList: List<GroceryItem>){
        GroceryItemList.clear()
        GroceryItemList.addAll(updateList)
        notifyDataSetChanged()
    }
}
interface onClick{
    fun OnItemClick(item :  GroceryItem)
}
class GroceryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val Itemname = itemView.itemName
    val Quantity = itemView.quantity
    val Rate = itemView.rate
    val DeleteItem = itemView.deleteItem
    val totalAmt = itemView.TotalAmt
}