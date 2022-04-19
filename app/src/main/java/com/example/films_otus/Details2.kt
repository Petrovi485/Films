package com.example.films_otus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.films_otus.databinding.ActivityDetails2Binding
import com.example.films_otus.databinding.ActivityDetailsBinding
import com.example.films_otus.databinding.ActivityMainBinding

class Details2 : AppCompatActivity() {
    lateinit var binding: ActivityDetails2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btback.setOnClickListener {

            val check2 = binding.checkBox.isChecked
            val text2 = binding.textView7.text

            val intent2 = Intent()

            intent2.putExtra("keyboolean2", "$check2")
            intent2.putExtra("keytext2", "$text2")

            setResult(RESULT_OK, intent2)
            finish()
        }


    }
}