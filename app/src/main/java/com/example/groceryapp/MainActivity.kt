package com.example.groceryapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.addbtn
import kotlinx.android.synthetic.main.grocery_add_dialog.*

class MainActivity : AppCompatActivity(), onClick {
    lateinit var madaptor : GroceryItemAdaptor
    lateinit var modelView: GroceryItemModelView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#17388E")))
        rvList.layoutManager = LinearLayoutManager(this)
        madaptor = GroceryItemAdaptor(this)
        rvList.adapter = madaptor
        modelView = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GroceryItemModelView::class.java)
        modelView.getAllItem.observe(this,{list->
            list?.let {
                madaptor.OngroceryItem(list)

            }
        })

        addbtn.setOnClickListener {
            openDailog()
            Toast.makeText(this,"Kindly Filled All",Toast.LENGTH_SHORT).show()
        }

    }

    private fun openDailog() {
        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
        dialog.setContentView(R.layout.grocery_add_dialog)
        dialog.show()

        val cancelBtn = dialog.findViewById(R.id.cancelbtn) as Button
        val addBtn = dialog.findViewById(R.id.addbtn) as Button
        val Edititemname = dialog.findViewById(R.id.editItemName) as EditText
        val EditQuantity = dialog.findViewById(R.id.editQuantity) as EditText
        val EdititemPrice = dialog.findViewById(R.id.editItemPrice) as EditText

        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemname = Edititemname.text.toString()
            val Quantity = EditQuantity.text.toString()
            val itemPrice = EdititemPrice.text.toString()

            val qty : Int = Quantity.toInt()
            val price : Int = itemPrice.toInt()
            if (itemname.isNotEmpty() && Quantity.isNotEmpty() && itemPrice.isNotEmpty()){
                val item = GroceryItem(itemname,qty,price)
                modelView.insert(item)
                Toast.makeText(this,"ItemAdded",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }else{
                Toast.makeText(this,"Kindly Filled All",Toast.LENGTH_SHORT).show()
            }


        }

    }

    override fun OnItemClick(item: GroceryItem) {
        modelView.delete(item)
    }
}