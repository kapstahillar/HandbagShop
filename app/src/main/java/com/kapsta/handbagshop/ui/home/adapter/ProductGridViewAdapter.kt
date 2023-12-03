package com.kapsta.handbagshop.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kapsta.handbagshop.R
import com.kapsta.handbagshop.data.dto.Product


// class for holding the cached view
internal class ProductGridViewHolder {
    var titleView: TextView? = null
    var priceView: TextView? = null
}

class ProductGridViewAdapter(private val context: Context, private val data: List<Product>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var viewHolder: ProductGridViewHolder
        if (view == null) {
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.content_carditem, parent, false);
            viewHolder = ProductGridViewHolder()
            viewHolder.titleView = view.findViewById(R.id.title)
            viewHolder.priceView = view.findViewById(R.id.price)
            view?.tag = viewHolder
        } else {
            viewHolder = view.tag as ProductGridViewHolder
        }
        val productData = data[position]
        val titleView = viewHolder.titleView?.findViewById<TextView>(R.id.title)
        titleView?.text = productData.name.en
        val priceView = viewHolder.priceView?.findViewById<TextView>(R.id.price)
        priceView?.text = productData.price.toString()
        return view!!
    }
}
