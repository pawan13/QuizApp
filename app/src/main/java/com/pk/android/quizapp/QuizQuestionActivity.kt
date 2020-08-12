package com.pk.android.quizapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity :AppCompatActivity() {
    var currentQuestionNumber = 0
    var mSelectedOptionPosition = 0
    var questionsList = Constants.getQuestions()
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        setQuestion()

        var value:String = intent.getStringExtra("username")

        txtOptionOne.setOnClickListener {
           selectedOptionView(txtOptionOne, 1)
        }
        txtOptionTwo.setOnClickListener {
            selectedOptionView(txtOptionTwo, 2)
        }
        txtOptionThree.setOnClickListener {
           selectedOptionView(txtOptionThree, 3)
        }
        txtOptionFour.setOnClickListener {
           selectedOptionView(txtOptionFour, 4)
        }

        btnSubmit.setOnClickListener {
            wrongOrRight()
            if(mSelectedOptionPosition == 0){
                Toast.makeText(this, "Please Select One",Toast.LENGTH_SHORT).show()
            }else{
                nextQuestion()
            }

            if (btnSubmit.text == "FINISHED"){
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("username", value)
                intent.putExtra("Score",score)
                startActivity(intent)
            }
        }

    }
    private fun wrongOrRight(){
        if (mSelectedOptionPosition == questionsList[currentQuestionNumber].correctAnswer){
            score += 1
        }
    }
    private fun nextQuestion(){
        if (currentQuestionNumber < questionsList.size - 1) {
            currentQuestionNumber += 1
            defaultOptionsView()
        }else if(currentQuestionNumber  == questionsList.size -1){
            btnSubmit.setText("FINISHED") as? Button
        }

        setQuestion()
    }
   private fun setQuestion(){
        ProgressBar.progress = ((currentQuestionNumber + 1) * 100) / questionsList.size
        val question: Question? = questionsList[currentQuestionNumber]
        txtQuestion.text = question!!.question
        IVFlags.setImageResource(question!!.image)
        txtProgressText.text = "${question!!.id} / ${questionsList.size}"
        txtOptionOne.text = question!!.optionOne
        txtOptionTwo.text = question!!.optionTwo
        txtOptionThree.text = question!!.optionThree
        txtOptionFour.text = question!!.optionFour
    }


    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD_ITALIC)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, txtOptionOne)
        options.add(1, txtOptionTwo)
        options.add(2, txtOptionThree)
        options.add(3, txtOptionFour)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
        mSelectedOptionPosition = 0
    }
}
