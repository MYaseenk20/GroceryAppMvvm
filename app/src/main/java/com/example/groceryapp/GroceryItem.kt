package com.example.groceryapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_item")
class GroceryItem (
        @ColumnInfo(name = "ItemName")
        var ItemName : String,
        @ColumnInfo(name = "ItemQuantity")
        var ItemQuantity : Int,
        @ColumnInfo(name = "ItemPrice")
        var ItemPrice : Int,
        ){
        @PrimaryKey(autoGenerate = true)
        var ItemId : Int? = null
}