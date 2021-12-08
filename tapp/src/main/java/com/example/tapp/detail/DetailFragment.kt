package com.example.tapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tapp.R
import com.example.tapp.databinding.FragmentDetailBinding
import com.example.tapp.main.MainActivity
import com.example.tapp.model.SitioItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var sitio: SitioItem
    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)?.showIcon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)

        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sitio = args.sitio

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        with(detailBinding){
            authorPhotoTextView.text  = sitio.authorPhoto
            //descriptionImgTextView.text = sitio.descriptionImg
            distanceAirportTextView.text = sitio.distanceAirport
            locationTextView.text = sitio.location
            longDescriptionTextView.text = sitio.longDescription
            nameTextView.text = sitio.name
            //scoreTextView.text = sitio.score
            shortDescriptionTextView.text = sitio.shortDescription
            temperatureTextView.text = sitio.temperature
            Picasso.get().load(sitio.urlPicture).into(pictureImageView)

            mapButton.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMapsFragment(sitio))
            }
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val sitio = args.sitio

        val positionSitio = LatLng(sitio.latitude, sitio.longitude)
        googleMap.addMarker(
            MarkerOptions()
                .position(positionSitio)
                .title(sitio.name)
                .snippet("Temperatura: " + sitio.temperature))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positionSitio, 16F))

    }

}