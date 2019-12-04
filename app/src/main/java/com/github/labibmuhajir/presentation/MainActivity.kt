package com.github.labibmuhajir.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.labibmuhajir.R
import com.github.labibmuhajir.di.Inject
import com.github.labibmuhajir.di.InjectEngine.ACTIVITY
import com.github.labibmuhajir.di.InjectEngine.inject
import com.github.labibmuhajir.domain.ViewState
import com.github.labibmuhajir.ext.gone
import com.github.labibmuhajir.ext.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @Inject(ACTIVITY)
    lateinit var mainViewModel: MainViewModel
    @Inject("cinema")
    lateinit var cinema: String
    @Inject("address")
    lateinit var address: String

    private val adapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
        setContentView(R.layout.activity_main)
        setupView()
        initData()
    }

    private fun setupView() {
        rvMovie.adapter = adapter
    }

    private fun initData() {
        tvCinemaValue.text = cinema
        tvAddress.text = address
        mainViewModel.movieState.observe(this, Observer { state ->
            when (state) {
                is ViewState.Success -> {
                    progressBar.gone()
                    adapter.addItems(state.data)
                }

                is ViewState.Loading -> progressBar.visible()
            }
        })
    }
}
