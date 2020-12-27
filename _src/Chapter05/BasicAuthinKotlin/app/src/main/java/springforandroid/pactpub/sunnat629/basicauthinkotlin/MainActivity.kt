package springforandroid.pactpub.sunnat629.basicauthinkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import springforandroid.pactpub.sunnat629.basicauthinkotlin.adapter.UserListAdapter
import springforandroid.pactpub.sunnat629.basicauthinkotlin.model.UserModel
import springforandroid.pactpub.sunnat629.basicauthinkotlin.repository.UserService
import springforandroid.pactpub.sunnat629.basicauthinkotlin.repository.UserServiceImpl


class MainActivity : AppCompatActivity() {

    var username = "sunnat629"
    var password = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userService: UserService = UserServiceImpl().getUserServiceImplImpl(username,password)

        val call: Call<List<UserModel>> = userService.getUserList()
        call.enqueue(object: Callback<List<UserModel>> {
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.e("PACKTPUB", t.message)
            }

            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                val adapter = UserListAdapter(this@MainActivity, response.body())
                displayList.adapter = adapter
            }
        })
    }
}
