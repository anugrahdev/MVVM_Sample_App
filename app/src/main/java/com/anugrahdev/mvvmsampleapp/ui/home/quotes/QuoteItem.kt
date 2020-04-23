package com.anugrahdev.mvvmsampleapp.ui.home.quotes

import com.anugrahdev.mvvmsampleapp.R
import com.anugrahdev.mvvmsampleapp.data.db.entities.Quote
import com.anugrahdev.mvvmsampleapp.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote:Quote):BindableItem<ItemQuoteBinding>(){
    override fun getLayout(): Int {
        return R.layout.item_quote
    }

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }


}