package com.example.groceryapp

import androidx.lifecycle.LiveData

class GroceryItemRepository (private val groceryItemDao : GroceryItemDao) {
    val getAllItem : LiveData<List<GroceryItem>> = groceryItemDao.getAllItem()

    suspend fun insert(groceryItem: GroceryItem){
        return groceryItemDao.insert(groceryItem)
    }
    suspend fun delete(groceryItem: GroceryItem){
        return groceryItemDao.delete(groceryItem)
    }
}