package com.github.labibmuhajir.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.labibmuhajir.R
import com.github.labibmuhajir.data.datasource.MovieDataSource
import com.github.labibmuhajir.di.Inject
import com.github.labibmuhajir.di.InjectEngine.inject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var movieRepository: MovieDataSource //can not be private
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
        val movies = movieRepository.getPopularMovies()
        adapter.addItems(movies)
    }
}
