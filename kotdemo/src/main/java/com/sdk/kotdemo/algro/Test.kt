package com.sdk.kotdemo.algro

import java.util.*

fun main() {
    println(isValid("{[]}"))
}

val maps = hashMapOf('{' to '}', '[' to ']', '(' to ')', '?' to '?')

fun isValid(s: String): Boolean {
    val chars = s.toCharArray()
    val list = LinkedList<Char>()
    list.add('?')
    chars.forEach {
        if (maps.containsKey(it)) {
            list.add(it)
        } else {
            if (maps.get(list.removeLast()) != it) {
                return false
            }
        }
    }
    return list.size == 1
}