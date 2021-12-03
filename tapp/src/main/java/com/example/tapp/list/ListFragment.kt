package com.example.tapp.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tapp.databinding.FragmentListBinding
import com.example.tapp.main.MainActivity
import com.example.tapp.model.Sitio
import com.example.tapp.model.SitioItem
import com.google.gson.Gson

class ListFragment : Fragment() {

    private lateinit var listSitios: ArrayList<SitioItem>
    private lateinit var sitiosAdapter: SitiosAdapter
    private lateinit var listBinding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)

        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()
        listSitios = loadMockSitiosFromJson()
        sitiosAdapter = SitiosAdapter(listSitios, onItemClicked = { onSitioClicked(it) } )
        listBinding.sitiosRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sitiosAdapter
            setHasFixedSize(false)
        }

    }

    private fun onSitioClicked(sitio: SitioItem) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(sitio = sitio))
        //findNavController().navigate(ListFragmentDirections.actionListFragmentToSettingsFragment())
    }

    private fun loadMockSitiosFromJson(): ArrayList<SitioItem> {
        val sitiosString: String = context?.assets?.open("sitios.json")?.bufferedReader().use { it!!.readText() }  //TODO fix this !!
        val gson = Gson()
        val sitiosList = gson.fromJson(sitiosString, Sitio::class.java)
        return sitiosList
    }
}