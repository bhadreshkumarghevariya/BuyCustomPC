package com.ibhadresh.buycustompc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportActionBar
//        title = "Main"
        val btnBuildYourOwn = findViewById<Button>(R.id.btnBuildYourOwn)
        btnBuildYourOwn.setOnClickListener {
            val intent = Intent(this,MotherBoardSelection::class.java)
            startActivity(intent)
        }
        val imgView : ImageView = findViewById(R.id.imageView)
        val imgURI = "https://firebasestorage.googleapis.com/v0/b/buycustompc.appspot.com/o/logo_pc_builder.png?alt=media&token=3e93fb1d-9b75-4fb4-99ff-ef80078db31d"

        Glide.with(this)
            .load(imgURI)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imgView)
    }
}