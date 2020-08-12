package com.pk.android.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var value:String = intent.getStringExtra("username")
        txtUsername.text = value
        var score:Int = intent.getIntExtra("Score", 0)
        txtScore.text = score.toString()

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if (score < 5){
            txtWinningMessage.text = "Sorry, You couldn't do better!"
            IVresult.setImageResource(R.drawable.oops)
        }else{
            txtWinningMessage.text = "Hey, Congratulation!, Take your Cup"
        }
    }
}
