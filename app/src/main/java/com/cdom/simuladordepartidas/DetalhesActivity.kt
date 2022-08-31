package com.cdom.simuladordepartidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cdom.simuladordepartidas.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}