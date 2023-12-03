package com.kapsta.handbagshop.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapsta.handbagshop.data.dto.Product
import com.kapsta.handbagshop.service.ProductService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private var productService: ProductService
) : ViewModel() {

    private var job: Job? = null
    val sessionKey = MutableLiveData("")
    val clientCode = MutableLiveData("")

    val searchBarValue = MutableLiveData("")
    private var productList: MutableLiveData<List<Product>?> = MutableLiveData()

    fun getProductList(): MutableLiveData<List<Product>?> {
        return productList
    }

    fun fetchProductList() {
        job = viewModelScope.launch {
            if (clientCode.value != null && sessionKey.value != null) {
                val products = productService.getAll(sessionKey.value!!, clientCode.value!!)
                productList.value = products.data
            }
        }
    }

    fun findProductByName(name: String) {
        if (validateSearch(name)) {
            job = viewModelScope.launch {
                if (clientCode.value != null && sessionKey.value != null) {
                    val products = productService.getAll(sessionKey.value!!, clientCode.value!!)
                    productList.value = products.data?.filter { it.name.en.startsWith(name, true) }
                }
            }
        }
    }

    private fun validateSearch(searchText: String): Boolean {
        if (searchText == "") {
            return false
        }
        return true
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}