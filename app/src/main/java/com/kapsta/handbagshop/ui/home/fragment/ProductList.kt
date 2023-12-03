package com.kapsta.handbagshop.ui.home.fragment

import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kapsta.handbagshop.R
import com.kapsta.handbagshop.ui.home.adapter.ProductGridViewAdapter
import com.kapsta.handbagshop.ui.home.viewmodel.ProductListViewModel

class ProductList : Fragment(R.layout.content_main) {

    private val viewModel: ProductListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productGridView = view.findViewById<GridView>(R.id.gridView)
        viewModel.getProductList().observe(viewLifecycleOwner, Observer {
            if (it != null)
                productGridView.adapter = ProductGridViewAdapter(requireContext(), it)
        })

    }
}