package com.example.android.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            if (binding.textName.getText().toString().isEmpty()){
                Toast.makeText(this,"the Name Field is Empty",Toast.LENGTH_SHORT).show()
            }else{
                val intent= Intent(this,QuizQuestionActivity::class.java)
                intent.putExtra(Constans.User_Name,binding.textName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }



}