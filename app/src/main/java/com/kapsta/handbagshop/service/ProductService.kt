package com.kapsta.handbagshop.service

import com.kapsta.handbagshop.data.dto.Product
import com.kapsta.handbagshop.framework.service.BaseService
import com.kapsta.handbagshop.framework.service.data.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

public interface IProductService {
    @GET("/v1/product")
    suspend fun getAll(
        @Header("sessionKey") sessionKey: String,
        @Header("clientCode") clientCode: String,
    ): Response<List<Product>>
}

class ProductService(apiUrl: String) : BaseService(apiUrl) {
    private val service: IProductService = getClient().create(IProductService::class.java)

    suspend fun getAll(sessionKey: String, clientCode: String): Resource<List<Product>> {
        println("Session key: " + sessionKey)
        println("clientCode: " + clientCode)
        val response = service.getAll(sessionKey, clientCode)
        return if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error("Failed to fetch products")
        }
    }
}