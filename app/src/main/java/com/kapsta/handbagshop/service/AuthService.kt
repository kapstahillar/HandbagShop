package com.kapsta.handbagshop.service

import com.kapsta.handbagshop.data.dto.AuthUser
import com.kapsta.handbagshop.data.model.AuthRequest
import com.kapsta.handbagshop.data.model.AuthResponse
import com.kapsta.handbagshop.framework.service.BaseService
import com.kapsta.handbagshop.framework.service.data.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

public interface IAuthService {
    @GET("/api/")
    suspend fun login(
        @Query("clientCode") clientCode: String,
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("sessionLength") sessionLength: Int = 86400,
        @Query("request") request: String = "verifyUser"
    ): Response<AuthResponse>
}

class AuthService(apiUrl: String) : BaseService(apiUrl) {
    private val service: IAuthService = getClient().create(IAuthService::class.java)

    suspend fun login(request: AuthRequest): Resource<AuthUser> {
        val response = service.login(request.userCode, request.userName, request.password)
        val success = response.isSuccessful
        if (!success) {
            Resource.Error<AuthUser>("Failed to retrieve user")
        }
        val httpCode = response.code()
        val responseBody = response.body()
        if (responseBody == null || response.body()!!.records.isEmpty()) {
            return Resource.Error("Failed to retrieve user")
        }
        return Resource.Success(
            AuthUser(
                response.body()!!.records[0].sessionKey,
                request.userCode
            )
        )
    }
}