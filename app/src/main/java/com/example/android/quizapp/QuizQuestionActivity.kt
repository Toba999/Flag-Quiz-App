package com.example.android.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.android.quizapp.databinding.ActivityQuizQuestionBinding

class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener {

    private var mCurrentPosition=1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOptionPosition=0
    private var mCorrectAnswer:Int=0
    private var mUserName:String?=null


    private lateinit var binding: com.example.android.quizapp.databinding.ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName=intent.getStringExtra(Constans.User_Name)

        setQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }


    private fun setQuestion(){

        mQuestionsList=Constans.getQuestions()
        val question:Question?= mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if(mCurrentPosition==mQuestionsList!!.size){
            binding.btnSubmit.text="Finish"
        }else{
            binding.btnSubmit.text="Submit"
        }

        binding.progressBar.progress=mCurrentPosition
        binding.tvProgressBar.text="$mCurrentPosition"+"/"+binding.progressBar.max
        binding.tvQuestionId.text=question!!.question
        binding.ivImage.setImageResource(question.image)

        binding.tvOptionOne.text=question.optionOne
        binding.tvOptionTwo.text=question.optionTwo
        binding.tvOptionThree.text=question.optionThree
        binding.tvOptionFour.text=question.optionFour
    }


    private fun defaultOptionsView(){
        val options=ArrayList<TextView>()
        options.add(0,binding.tvOptionOne)
        options.add(1,binding.tvOptionTwo)
        options.add(2,binding.tvOptionThree)
        options.add(3,binding.tvOptionFour)

        for (options in options){
            options.setTextColor(Color.parseColor("#7A8089"))
            options.typeface= Typeface.DEFAULT
            options.background=ContextCompat.getDrawable(
                    this,
                    R.drawable.default_option_bg
            )
        }
    }


    override fun onClick(v: View?) {

        when(v?.id){
           R.id.tv_option_one->{
                selectedOptionsView(binding.tvOptionOne,1)
            }
            R.id.tv_option_two->{
                selectedOptionsView(binding.tvOptionTwo,2)
            }
            R.id.tv_option_three->{
                selectedOptionsView(binding.tvOptionThree,3)
            }
            R.id.tv_option_four->{
                selectedOptionsView(binding.tvOptionFour,4)
            }
            R.id.btn_submit->{
                if (mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition<= mQuestionsList?.size!! -> {
                            setQuestion()
                        }else->{
                        val intent=Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constans.User_Name,mUserName)
                        intent.putExtra(Constans.Correct_Answers,mCorrectAnswer)
                        intent.putExtra(Constans.Total_Questions,mQuestionsList!!.size)
                        startActivity(intent)
                        finish()

                        }
                    }
                }else{
                    val question=mQuestionsList?.get(mCurrentPosition-1)
                    if (question!!.correctAnswer!=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_bg)

                    if(mCurrentPosition==mQuestionsList!!.size){
                        binding.btnSubmit.text="Finish"
                    }else{
                        binding.btnSubmit.text="Go To Next Question"
                    }
                    mSelectedOptionPosition=0
                }

            }
        }

    }

    private fun answerView(answer:Int,drawableView:Int){

        when(answer){
            1->{
                binding.tvOptionOne.background=ContextCompat.getDrawable(
                        this,drawableView)
            }
            2->{
                binding.tvOptionTwo.background=ContextCompat.getDrawable(
                        this,drawableView)
            }
            3->{
                binding.tvOptionThree.background=ContextCompat.getDrawable(
                        this,drawableView)
            }
            4->{
                binding.tvOptionFour.background=ContextCompat.getDrawable(
                        this,drawableView)
            }
        }
    }

    private fun selectedOptionsView(tv:TextView,selectedOptionNumber:Int){

        defaultOptionsView()
        mSelectedOptionPosition=selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface= Typeface.DEFAULT_BOLD
        tv.background=ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_bg
        )
    }
}