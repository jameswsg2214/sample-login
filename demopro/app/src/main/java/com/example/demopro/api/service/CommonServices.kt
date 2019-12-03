package com.example.demopro.service


import com.example.demopro.request.Cmsdata
import com.example.demopro.request.LoginRequest
import com.example.demopro.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

interface CommonServices {

    @POST("auth/login")
    fun login(@Body body: LoginRequest): Observable<LoginResponse>

    @POST("apim/getCMSlist")
    fun getcms(): Observable<Cmsdata>



}