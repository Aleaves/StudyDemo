package com.sdk.kotdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sdk.kotdemo.a.Student
import com.sdk.kotdemo.a.UserInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
//            test1()
//            test2()
            test3()
        }
//       / fab.setColor()

    }

    private fun test3() {
        val user = UserInfo(){
            "122"
        }
        user.setOnUserName()
        val student = Student("tom",18)
        student.name
    }

    private fun test1() {
        val testmap = mapOf<String, String?>("name" to null, "age" to "23")

        val map = mapOf("name" to "tom", "age" to 20)
        var user = User(map)
        println(user.name + "===" + user.age)
    }

    class User(var map: Map<String, Any?>) {
        val name: String by map
        val age: Int by map
    }

    private fun test2() {
        var s = "12212"
        var result = s.let {
            1000
            200
            it.substring(1, 2)
        }
        println(result)

        var res = with(s) {
            substring(1, 2)
        }

        println(res)
    }

}
