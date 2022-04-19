package com.example.films_otus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.films_otus.databinding.ActivityDetails3Binding
import com.example.films_otus.databinding.ActivityMainBinding

class Details3 : AppCompatActivity() {
    lateinit var binding: ActivityDetails3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetails3Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btback.setOnClickListener {

            val check3 = binding.checkBox.isChecked
            val text3 = binding.textView7.text

            val intent3 = Intent()

            intent3.putExtra("keyboolean3", "$check3")
            intent3.putExtra("keytext3", "$text3")

            setResult(RESULT_OK, intent3)
            finish()
        }
    }
}