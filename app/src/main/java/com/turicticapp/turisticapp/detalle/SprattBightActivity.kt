package com.turicticapp.turisticapp.detalle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.turicticapp.turisticapp.databinding.ActivitySprattBightBinding
import com.turicticapp.turisticapp.model.SitioItem

class SprattBightActivity : AppCompatActivity() {

    private lateinit var sprattBightBinding: ActivitySprattBightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sprattBightBinding = ActivitySprattBightBinding.inflate(layoutInflater)
        setContentView(sprattBightBinding.root)

        val sitio: SitioItem = intent.extras?.getSerializable("sitio") as SitioItem

        with(sprattBightBinding){
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