package com.sdk.kotdemo.f

class B {

    constructor(name: String, age: Int) {
        this.age = age
        this.name = name
    }

    var name: String

    var age: Int = 0

    var stringRepresentation:String
        get() = this.toString()
        set(value) {
            this.stringRepresentation = value
        }

}