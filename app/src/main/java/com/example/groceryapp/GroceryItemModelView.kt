package com.example.groceryapp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryItemModelView(application: Application) :AndroidViewModel(application) {

    var getAllItem : LiveData<List<GroceryItem>>
    var repository : GroceryItemRepository

    init {
        var dao = GroceryItemDatabase.getDatabase(application).groceryItemDao()
        repository = GroceryItemRepository(dao)
        getAllItem = repository.getAllItem
    }
    fun insert(groceryItem: GroceryItem) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(groceryItem)
    }
    fun delete(groceryItem: GroceryItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(groceryItem)
    }
}



class GroceryViewModelFactory(private var application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroceryItemModelView(application) as T
    }

}