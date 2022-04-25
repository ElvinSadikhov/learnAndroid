package com.example.lessons_16_18activityonactivtyresultpractise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lessons_16_18activityonactivtyresultpractise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var launcher: ActivityResultLauncher<Intent> ?= null
    private var MY_LOG = "MyLog"

    lateinit var mActBinding : ActivityMainBinding
    private var login: String = ""
    private var password: String = ""
    private var fstName: String = ""
    private var sndName: String = ""
    private var frsName: String = ""
    private var avatarID: Int = Constants.dulaID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActBinding.root)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
            val state = result.data?.getStringExtra(Constants.SIGN_STATE)

            if (result.data?.getStringExtra(Constants.SIGN_STATE) == Constants.SIGN_IN_STATE && result.resultCode == RESULT_OK){
                Log.d(MY_LOG,"we are back at MainAct from In state")

                var curStatus:String
                if (result.data?.getStringExtra(Constants.LOGIN)==login && result.data?.getStringExtra(Constants.PASSWORD)==password){
                    mActBinding.bSignUp.visibility = View.GONE
                    mActBinding.bSignIn.text = getString(R.string.quit)
                    mActBinding.ivAvatar.setImageResource(avatarID)
                    mActBinding.ivAvatar.visibility = View.VISIBLE
                    curStatus = "$fstName $sndName $frsName"
                    mActBinding.tvStatus.text = curStatus
                    mActBinding.tvStatus.visibility = View.VISIBLE
                } else {
                    mActBinding.ivAvatar.setImageResource(R.drawable.dula)
                    mActBinding.ivAvatar.visibility = View.VISIBLE
                    curStatus = getString(R.string.accountNotFound)
                    mActBinding.tvStatus.text = curStatus
                }
            }
            else if (result.data?.getStringExtra(Constants.SIGN_STATE) == Constants.SIGN_UP_STATE && result.resultCode == RESULT_OK){
                Log.d(MY_LOG,"we are back at MainAct from Up state")

                login = result.data?.getStringExtra(Constants.LOGIN)!!
                password = result.data?.getStringExtra(Constants.PASSWORD)!!
                fstName = result.data?.getStringExtra(Constants.FSTNAME)!!
                sndName = result.data?.getStringExtra(Constants.SNDNAME)!!
                frsName = result.data?.getStringExtra(Constants.FRSNAME)!!
                avatarID = result.data?.getStringExtra(Constants.AVATAR_ID)!!.toInt()
            }

        }
    }

    fun onClickSignIn(view: View){
        if(mActBinding.bSignIn.text == getString(R.string.quit)){
            mActBinding.bSignIn.text = getString(R.string.signInState) //!!!
            mActBinding.bSignUp.visibility = View.VISIBLE
            mActBinding.ivAvatar.visibility = View.GONE
            mActBinding.tvStatus.visibility = View.GONE
        } else {
            mActBinding.tvStatus.visibility = View.GONE
            mActBinding.ivAvatar.visibility = View.GONE
            var signInAct = Intent(this, LogInUpActivity::class.java)
            signInAct.putExtra(Constants.SIGN_STATE, Constants.SIGN_IN_STATE)
            launcher?.launch(signInAct)
        }
    }

    fun onClickSignUp(view: View){
        mActBinding.tvStatus.visibility = View.GONE
        mActBinding.ivAvatar.visibility = View.GONE
        var signUpAct = Intent(this, LogInUpActivity::class.java)
        signUpAct.putExtra(Constants.SIGN_STATE,Constants.SIGN_UP_STATE)
        launcher?.launch(signUpAct)
    }
}