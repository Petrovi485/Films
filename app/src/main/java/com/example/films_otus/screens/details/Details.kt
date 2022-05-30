package com.example.films_otus.screens.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.films_otus.databinding.ActivityDetailsBinding
import com.example.films_otus.databinding.ActivityMainBinding


class Details : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btback.setOnClickListener {

            val check1 = binding.checkBox.isChecked
            val text1 = binding.textView7.text

            val intent = Intent()

                intent.putExtra("keyboolean1", "$check1")
                intent.putExtra("keytext1", "$text1")

            setResult(RESULT_OK, intent)
            finish()
        }



    }




}