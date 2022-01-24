package com.example.groceryapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(groceryItem: GroceryItem)

    @Delete
    suspend fun delete(groceryItem: GroceryItem)

    @Query("Select * From grocery_item ")
    fun getAllItem() : LiveData<List<GroceryItem>>
}