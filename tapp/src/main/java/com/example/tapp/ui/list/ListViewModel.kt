package com.example.tapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tapp.data.SitiosRepository
import com.example.tapp.model.Sitio
import com.example.tapp.model.SitioItem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream

class ListViewModel : ViewModel() {
    private var sitiosLoad : MutableLiveData<ArrayList<SitioItem>> = MutableLiveData()
    val onSitiosLoad : LiveData<ArrayList<SitioItem>> = sitiosLoad

    private val repository = SitiosRepository()

    fun getSitiosFromServer(){
        GlobalScope.launch(Dispatchers.IO) {
            sitiosLoad.postValue(repository.getSitios())
        }
    }

    fun loadMockSitiosFromJson(sitioString : InputStream?) {
        val sitioString = sitioString?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        sitiosLoad.value = gson.fromJson(sitioString, Sitio::class.java)
    }
}