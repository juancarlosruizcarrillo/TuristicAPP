package com.example.tapp.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tapp.model.Sitio
import com.example.tapp.model.SitioItem
import com.google.gson.Gson
import java.io.InputStream

class ListViewModel : ViewModel() {
    private var sitiosLoad : MutableLiveData<ArrayList<SitioItem>> = MutableLiveData()
    val onSitiosLoad : LiveData<ArrayList<SitioItem>> = sitiosLoad

    fun loadMockSitiosFromJson(sitioString : InputStream?) {
        val sitioString = sitioString?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        sitiosLoad.value = gson.fromJson(sitioString, Sitio::class.java)
    }
}