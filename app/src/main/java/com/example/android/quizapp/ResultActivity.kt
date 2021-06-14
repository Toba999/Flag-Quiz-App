package com.example.android.quizapp

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.Toast
import com.example.android.quizapp.databinding.ActivityMainBinding
import com.example.android.quizapp.databinding.ResultActivityBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ResultActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text=intent.getStringExtra(Constans.User_Name)
        val rTotalQuestion=intent.getIntExtra(Constans.Total_Questions,0)
        val rCorrectQuestions=intent.getIntExtra(Constans.Correct_Answers,0)
        binding.tvScore.text="Your Score is $rCorrectQuestions out of $rTotalQuestion"
        binding.btnStartAgain.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        binding.btnFinish.setOnClickListener {
            finish()
        }

    }
}

