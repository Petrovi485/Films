package com.example.films_otus

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.films_otus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var launcher: ActivityResultLauncher<Intent>? = null
    private var launcher2: ActivityResultLauncher<Intent>? = null
    private var launcher3: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->

            if (result.resultCode == RESULT_OK){
                val boolean1 = result.data?.getStringExtra("keyboolean1")
                val text1 = result.data?.getStringExtra("keytext1")
                val namefilm = binding.textView1.text

                if (boolean1 == "true"){

                Log.d("MyLog",  "Пользователю нравится фильм $namefilm")
                Log.d("MyLog",  "Пользователь оставил следующий отзыв $text1")}
                else {
                    Log.d("MyLog",  "Пользователю не нравится фильм $namefilm")
                    Log.d("MyLog",  "Пользователь оставил следующий отзыв: $text1")}

            }

        }


        launcher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->

            if (result.resultCode == RESULT_OK){
                val boolean2 = result.data?.getStringExtra("keyboolean2")
                val text2 = result.data?.getStringExtra("keytext2")
                val namefilm2 = binding.textView2.text

                if (boolean2 == "true"){

                    Log.d("MyLog",  "Пользователю нравится фильм $namefilm2")
                    Log.d("MyLog",  "Пользователь оставил следующий отзыв: $text2")}
                else {
                    Log.d("MyLog",  "Пользователю не нравится фильм $namefilm2")
                    Log.d("MyLog",  "Пользователь оставил следующий отзыв: $text2")}

            }

        }


        launcher3 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->

            if (result.resultCode == RESULT_OK){
                val boolean3 = result.data?.getStringExtra("keyboolean3")
                val text3 = result.data?.getStringExtra("keytext3")
                val namefilm3 = binding.textView3.text

                if (boolean3 == "true"){

                    Log.d("MyLog",  "Пользователю нравится фильм $namefilm3")
                    Log.d("MyLog",  "Пользователь оставил следующий отзыв: $text3")}
                else {
                    Log.d("MyLog",  "Пользователю не нравится фильм $namefilm3")
                    Log.d("MyLog",  "Пользователь оставил следующий отзыв: $text3")}

            }

        }


    }

    fun open(view: View) {

        when (view){

            binding.bd1 -> {
                binding.textView1.setTextColor(Color.RED)
                launcher?.launch(Intent(this, Details::class.java))

            }

            binding.bd2 -> {
                binding.textView2.setTextColor(Color.RED)
                launcher2?.launch(Intent(this, Details2::class.java))

            }

            binding.bd3 -> {
                binding.textView3.setTextColor(Color.RED)
                launcher3?.launch(Intent(this, Details3::class.java))

            }

            binding.bshare -> {

                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Привет! Смотри какое я сделал приложение!");
                startActivity(Intent.createChooser(shareIntent, "Описание чегото"))

            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("KEY", binding.textView1.currentTextColor)

        }

        super.onSaveInstanceState(outState)



    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        binding.textView1.setTextColor(savedInstanceState.getInt("KEY"))

    }

}






