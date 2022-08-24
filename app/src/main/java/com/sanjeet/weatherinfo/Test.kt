package com.sanjeet.weatherinfo

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    println("Hello Main")

    println("2nd Thread is ${Thread.currentThread().name}")

    val list:List<String> = listOf("da","fd","gf")
    println("First : ${list.get(0)}")
    println("Second : ${list.get(1)}")
    println("Third : ${list.get(2)}")

    val map:Map<String,String> = mapOf("First" to "Sanjeet","Second" to "Manjeet", "Third" to "Ranjeet")
    println("First : ${map["First"]}")
    println("Second : ${map["Second"]}")
    println("Third : ${map["T"]}")

}