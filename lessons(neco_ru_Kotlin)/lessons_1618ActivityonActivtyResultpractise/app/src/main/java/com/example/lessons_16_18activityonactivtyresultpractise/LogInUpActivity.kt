package com.example.lessons_16_18activityonactivtyresultpractise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import com.example.lessons_16_18activityonactivtyresultpractise.databinding.ActivityLogInUpBinding

class LogInUpActivity : AppCompatActivity() {

    lateinit var logInUpActBinding: ActivityLogInUpBinding
    private var MY_LOG = "MyLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInUpActBinding = ActivityLogInUpBinding.inflate(layoutInflater)
        setContentView(logInUpActBinding.root)

        val sign_state = intent.getStringExtra(Constants.SIGN_STATE)
        when(sign_state) {
            Constants.SIGN_IN_STATE -> {
                logInUpActBinding.ivAvatar.visibility = View.GONE
                logInUpActBinding.bAvatar.visibility = View.GONE
                logInUpActBinding.edFstName.visibility = View.GONE
                logInUpActBinding.edSndName.visibility = View.GONE
                logInUpActBinding.edFrsName.visibility = View.GONE
                logInUpActBinding.bSingInUp.text = getString(R.string.signInState)  //!!!
                logInUpActBinding.bSingInUp.setMarginTop(16) // try this!`
            }
            Constants.SIGN_UP_STATE -> {
                logInUpActBinding.ivAvatar.visibility = View.INVISIBLE // cuz of photo
                logInUpActBinding.bAvatar.visibility = View.VISIBLE
                logInUpActBinding.edFstName.visibility = View.VISIBLE
                logInUpActBinding.edSndName.visibility = View.VISIBLE
                logInUpActBinding.edFrsName.visibility = View.VISIBLE
                logInUpActBinding.bSingInUp.text = getString(R.string.signUpState)  //!!!
            }
        }
    }

    fun onClickAvatar(view: View){
        logInUpActBinding.ivAvatar.setImageResource(R.drawable.person)
        logInUpActBinding.ivAvatar.visibility = View.VISIBLE
    }

    fun onClickSignInUp(view: View){
        when(intent.getStringExtra(Constants.SIGN_STATE)){
            Constants.SIGN_IN_STATE -> {
                Log.d(MY_LOG,"signing in")

                intent.putExtra(Constants.LOGIN, logInUpActBinding.edLogin.text.toString())
                intent.putExtra(Constants.PASSWORD, logInUpActBinding.edPassword.text.toString())
                intent.putExtra(Constants.SIGN_STATE,Constants.SIGN_IN_STATE)
                setResult(RESULT_OK,intent)
                finish()
            }
            Constants.SIGN_UP_STATE -> {
                Log.d(MY_LOG,"signing up")

                intent.putExtra(Constants.LOGIN, logInUpActBinding.edLogin.text.toString())
                intent.putExtra(Constants.PASSWORD, logInUpActBinding.edPassword.text.toString())
                intent.putExtra(Constants.FSTNAME, logInUpActBinding.edFstName.text.toString())
                intent.putExtra(Constants.SNDNAME, logInUpActBinding.edSndName.text.toString())
                intent.putExtra(Constants.FRSNAME, logInUpActBinding.edFrsName.text.toString())
                if(logInUpActBinding.ivAvatar.isVisible)
                    intent.putExtra(Constants.AVATAR_ID, R.drawable.person.toString())
                intent.putExtra(Constants.SIGN_STATE,Constants.SIGN_UP_STATE)
                setResult(RESULT_OK,intent)
                finish()
            }
        }
    }

    private fun View.setMarginTop(topMargin: Int) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(params.leftMargin, topMargin, params.rightMargin, params.bottomMargin)
        layoutParams = params
    }

}