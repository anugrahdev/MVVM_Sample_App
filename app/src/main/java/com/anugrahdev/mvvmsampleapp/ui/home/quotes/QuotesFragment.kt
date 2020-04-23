package com.anugrahdev.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.anugrahdev.mvvmsampleapp.R
import com.anugrahdev.mvvmsampleapp.data.db.entities.Quote
import com.anugrahdev.mvvmsampleapp.utils.Coroutines
import com.anugrahdev.mvvmsampleapp.utils.hide
import com.anugrahdev.mvvmsampleapp.utils.show
import com.anugrahdev.mvvmsampleapp.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.quotes.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem());
        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {
        val mAdapter =GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=mAdapter
        }
    }

    private fun List<Quote>.toQuoteItem():List<QuoteItem>{
        return this.map{
            QuoteItem(it)
        }
    }

}
