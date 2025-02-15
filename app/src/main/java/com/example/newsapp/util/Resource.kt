package com.example.newsapp.util

sealed class Resource<T> (val data:T?=null, val message:String?=null){
    class Succes<T>(data:T):Resource<T>(data)
    class Error<T>(message:String,data:T?=null):Resource<T>(data,message)
    class Loading<T>:Resource<T>()
}