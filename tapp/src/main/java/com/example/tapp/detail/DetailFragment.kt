package com.example.tapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.tapp.databinding.FragmentDetailBinding
import com.example.tapp.main.MainActivity
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

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
        val sitio = args.sitio

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
        }
    }

}