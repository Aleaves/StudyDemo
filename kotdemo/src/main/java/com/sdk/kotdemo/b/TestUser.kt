package com.sdk.kotdemo.b

fun main() {
    val user = User()
    user.setOnDataDoneListener("Tim",object : OnDataDoneListener{
        override fun onDataDone(item: String) {
            println(item)
        }
    })

    user.setOnDataDoneListener(object : OnDataDoneListener{
        override fun onDataDone(item: String) {

        }
    })

    user.setOnDataDoneListener1{
        "122$it"
    }

    val student = Student()
    student.setOnNameDoneListener {

    }


}