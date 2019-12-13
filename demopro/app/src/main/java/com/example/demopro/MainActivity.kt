package com.example.demopro

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.demopro.RoomDb.SplootAppDB
import com.example.demopro.request.LoginRequest
import com.example.demopro.response.LoginResponse
import com.example.demopro.rx.RxAPICallHelper
import com.example.demopro.rx.RxAPICallback
import com.example.demopro.service.CommonServices
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private var splootDB: SplootAppDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        splootDB = SplootAppDB.getInstance(this)


       /* val emailid = findViewById<EditText>(R.id.email_name)

        val password = findViewById<EditText>(R.id.pass)*/







        login.setOnClickListener {

            Log.e("qwerty","${email_name.text.toString()}")

            Process1(email_name.text.toString().trim(),pass.text.toString().trim())

        }
var user: String? = null
    var pass: String? = null







        //process for room db


        process_db("name","password")



    }

    private fun process_db(username: String, password: String) {

        AsyncTask.execute {
            try {
                val callDetails = splootDB!!.petMasterDao()



            } catch (e: Exception) {
                val s = e.message;
                Log.e("Error in pet details",s)
            }
        }

    }


    private fun Process1(username: String, passwords: String) {
        var nextpage= Intent(this, firstpage::class.java)

    //    var alertDialog = ConstantMethods().setProgressDialog(this)
     //   alertDialog.show()
        var commService: CommonServices = ApiProduction(this).provideService(CommonServices::class.java)
        val loginRequest = LoginRequest()
        loginRequest.setPassword(passwords)
        loginRequest.setUserName(username)
        var apiCall: Observable<LoginResponse> = commService.login(loginRequest)

        RxAPICallHelper().call(apiCall, object : RxAPICallback<LoginResponse> {
            override fun onSuccess(loginResponse: LoginResponse) {
                if(loginResponse.getStatus()!!)
                {
                 /*   Log.e("Sucess","Sucess")
                    stringPref("token",loginResponse.getToken().toString())
                    stringPref("name",loginResponse.getUserDetails()?.userName.toString())
                    stringPref("useremail",loginResponse.getUserDetails()?.emailId.toString())
                    stringPref("active",loginResponse.getUserDetails()?.active.toString())
                    stringPref("userId",loginResponse.getUserDetails()?.userId.toString())
                    stringPref("loginTime", Calendar.getInstance().timeInMillis.toString())
                    stringPref("petid", null)

                    stringPref("petimage",null)
                    val android_id = Settings.Secure.getString(
                        applicationContext.contentResolver,
                        Settings.Secure.ANDROID_ID
                    )
                    var accessToken by stringPref("access_token", null)
                    var username by stringPref("name", null)
                    var useremail by stringPref("useremail", null)
                    var userdesignation by stringPref("userdesignation", null)
                    var active by stringPref("active", null)
                    var userId by stringPref("userId", null)
                    var loginTime by stringPref("loginTime", null)
                    var petid by stringPref("petid", null)
                    var petimage by stringPref("petimage", null)

                    accessToken =  loginResponse.getToken().toString();
                    username = loginResponse.getUserDetails()?.userName.toString()
                    useremail = loginResponse.getUserDetails()?.emailId.toString()
                    active = loginResponse.getUserDetails()?.active.toString()
                    userId =  loginResponse.getUserDetails()?.userId.toString()
                    loginTime = Calendar.getInstance().timeInMillis.toString()
*/

                    Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT).show()

                }
                else
                {
                    Toast.makeText(this@MainActivity, loginResponse.getMsg(), Toast.LENGTH_SHORT).show()
                /*    password.text.clear()
                    alertDialog.dismiss()*/
                }
            }
            override fun onFailed(throwable: Throwable) {
                Log.e("addjourney_dategrid"," clicked Throwable:"+throwable.toString())
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
           //     alertDialog.dismiss()
            }
        })
    }
}
