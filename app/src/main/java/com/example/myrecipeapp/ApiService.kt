package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val rettrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService: ApiService = rettrofit.create(ApiService::class.java)


interface ApiService {
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}