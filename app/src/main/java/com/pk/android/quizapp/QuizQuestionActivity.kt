package com.pk.android.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity(val clicklistener: Unit) : AppCompatActivity() {
    var currentQuestion = 0
    val questionsList = Constants.getQuestions()
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        ProgressBar.progress = ((currentQuestion + 1) * 100) / questionsList.size
        val question: Question? = questionsList[currentQuestion]
        txtQuestion.text = question!!.question
        IVFlags.setImageResource(question!!.image)
        txtProgressText.text = "${question!!.id} / ${questionsList.size}"
        txtOptionOne.text = question!!.optionOne
        txtOptionTwo.text = question!!.optionTwo
        txtOptionThree.text = question!!.optionThree
        txtOptionFour.text = question!!.optionFour


        btnSubmit.setOnClickListener {
          nextQuestion()
        }

    }
    fun nextQuestion(){
        if (currentQuestion < questionsList.size - 1) {
            currentQuestion += 1
        }else {
            currentQuestion = 0
        }
        ProgressBar.progress = ((currentQuestion + 1) * 100) / questionsList.size
        val question: Question? = questionsList[currentQuestion]
        txtQuestion.text = question!!.question
        IVFlags.setImageResource(question!!.image)
        txtProgressText.text = "${question!!.id} / ${questionsList.size}"
        txtOptionOne.text = question!!.optionOne
        txtOptionTwo.text = question!!.optionTwo
        txtOptionThree.text = question!!.optionThree
        txtOptionFour.text = question!!.optionFour
    }

    fun onClick(view: View) {
            when(view.getId()){
                R.id.txtOptionOne ->
                    if (questionsList[currentQuestion].correctAnswer == 1){
                        txtOptionOne.text = "Correct Answer"
                    }
                    else{
                        txtOptionOne.text = "Oops! Wrong"
                    }
                R.id.txtOptionTwo ->
                    if (questionsList[currentQuestion].correctAnswer == 2){
                        txtOptionTwo.text = "Correct Answer"
                    }
                    else{
                        txtOptionTwo.text = "Oops! Wrong"
                    }
                R.id.txtOptionThree ->
                    if (questionsList[currentQuestion].correctAnswer == 3){
                        txtOptionThree.text = "Correct Answer"
                    }
                    else{
                        txtOptionThree.text = "Oops! Wrong"
                    }
                R.id.txtOptionFour ->
                    if (questionsList[currentQuestion].correctAnswer == 4){
                        txtOptionFour.text = "Correct Answer"
                    }
                    else{
                        txtOptionFour.text = "Oops! Wrong"
                    }
                else -> Error()
            }
        }
    }
