package com.example.quizapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private var name:String ?= null
    private var score:Int = 0

    private var currentPosition: Int = 1
    private var questionList: ArrayList<QuestionData>? = null
    private var selectedOption: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        name= intent.getStringExtra(setData.name)

        questionList = setData.getQuestion()
        setQuestion()
        option_one.setOnClickListener {
            selectedOptionStyle(option_one, 1)
        }

        option_two.setOnClickListener {
            selectedOptionStyle(option_two, 2)
        }

        option_three.setOnClickListener {
            selectedOptionStyle(option_three, 3)
        }

        option_four.setOnClickListener {
            selectedOptionStyle(option_four, 4)
        }
        submit.setOnClickListener {
            if (selectedOption != 0) {
                val question = questionList!![currentPosition - 1]
                if (selectedOption != question.correct_ans) {
                    setColor(selectedOption, R.drawable.warning_question_option)
                }else{
                    score++
                }
                setColor(question.correct_ans,R.drawable.correct_question_option)
                if (currentPosition == questionList!!.size)
                    submit.text = "Finish"
                else
                    submit.text = "Go to next"
            }else{
                currentPosition ++
                when{
                    currentPosition<= questionList!!.size->{
                        setQuestion()
                    }
                    else->{
                        var intent = Intent(this,Result::class.java)
                        intent.putExtra(setData.name,name.toString())
                        intent.putExtra(setData.score,score.toString())
                        intent.putExtra("total size",questionList!!.size.toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }

            selectedOption=0
        }

    }

    fun setColor(opt: Int, color: Int) {
        when (opt) {
            1 -> {
                option_one.background = ContextCompat.getDrawable(this, color)
            }
            2 -> {
                option_two.background = ContextCompat.getDrawable(this, color)
            }
            3 -> {
                option_three.background = ContextCompat.getDrawable(this, color)
            }
            4 -> {
                option_four.background = ContextCompat.getDrawable(this, color)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setQuestion() {

        var question = questionList!![currentPosition - 1]
        setOptionStyle()
        progress_bar.progress = currentPosition
        progress_bar.max = questionList!!.size
        progress_text.text = "${currentPosition}" + "/" + "${questionList!!.size}"

        question_text.text = question.question
        option_one.text = question.question_one
        option_two.text = question.question_two
        option_three.text = question.question_three
        option_four.text = question.question_four

    }

    fun setOptionStyle() {
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, option_one)
        optionList.add(1, option_two)
        optionList.add(2, option_three)
        optionList.add(3, option_four)

        for (op in optionList) {
            op.setTextColor(Color.parseColor("#555155"))
            op.background = ContextCompat.getDrawable(this, R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }
    }

    fun selectedOptionStyle(view: TextView, opt: Int) {
        setOptionStyle()
        selectedOption = opt
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))
    }
}