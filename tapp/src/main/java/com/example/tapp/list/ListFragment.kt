package com.example.tapp.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tapp.databinding.FragmentListBinding
import com.example.tapp.main.MainActivity
import com.example.tapp.model.SitioItem


class ListFragment : Fragment() {

    private  var listSitios: ArrayList<SitioItem> = arrayListOf()
    private lateinit var sitiosAdapter: SitiosAdapter
    private lateinit var listBinding: FragmentListBinding
    private lateinit var listViewModel : ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()
        listViewModel.loadMockSitiosFromJson(context?.assets?.open("sitios.json"))
        listViewModel.onSitiosLoad.observe(viewLifecycleOwner, { result ->
            onSitioLoaderSubscribe(result)
        })

        sitiosAdapter = SitiosAdapter(listSitios, onItemClicked = { onSitioClicked(it) } )
        listBinding.sitiosRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sitiosAdapter
            setHasFixedSize(false)
        }
    }

    private fun onSitioLoaderSubscribe(result: ArrayList<SitioItem>?) {
        result?.let{ listSitios ->
            sitiosAdapter.appendItems(listSitios)
        }
    }

    private fun onSitioClicked(sitio: SitioItem) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(sitio = sitio))
        //findNavController().navigate(ListFragmentDirections.actionListFragmentToSettingsFragment())
    }
}