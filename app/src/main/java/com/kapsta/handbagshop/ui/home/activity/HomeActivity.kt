package com.kapsta.handbagshop.ui.home.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.kapsta.handbagshop.R
import com.kapsta.handbagshop.ui.home.fragment.ProductList
import com.kapsta.handbagshop.ui.home.fragment.SearchBar
import com.kapsta.handbagshop.ui.home.fragment.SearchBarCallbackListener
import com.kapsta.handbagshop.ui.home.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), SearchBarCallbackListener {
    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addTopbarFragment()
        val extras = intent.extras
        val sessionKey = extras!!.getString("session_key")
        val clientCode = extras.getString("client_code")
        viewModel.clientCode.value = clientCode
        viewModel.sessionKey.value = sessionKey
        viewModel.searchBarValue.observe(this, Observer {
            if (it.length > 2) {
                viewModel.findProductByName(it)
            } else if (it.isEmpty()) {
                viewModel.fetchProductList()
            }
        })
        addProductListFragment()
        viewModel.fetchProductList()
    }


    private fun addTopbarFragment() {
        val fm: FragmentManager = supportFragmentManager
        var fragment = fm.findFragmentByTag("topbarFragment")
        if (fragment == null) {
            val ft: FragmentTransaction = fm.beginTransaction()
            fragment = SearchBar(this)
            ft.add(R.id.content, fragment, "topbarFragment")
            ft.commit()
        }
    }

    private fun addProductListFragment() {
        val fm: FragmentManager = supportFragmentManager
        var fragment = fm.findFragmentByTag("productListFragment")
        if (fragment == null) {
            val ft: FragmentTransaction = fm.beginTransaction()
            fragment = ProductList()
            ft.add(R.id.content, fragment, "productListFragment")
            ft.commit()
        }
    }

    override fun onSearchTextChanged(text: String) {
        viewModel.searchBarValue.value = text
    }
}