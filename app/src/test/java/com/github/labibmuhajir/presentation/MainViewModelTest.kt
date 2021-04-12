package com.github.labibmuhajir.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.labibmuhajir.common.getTestObserver
import com.github.labibmuhajir.data.api.response.MovieResponse
import com.github.labibmuhajir.data.api.response.PageableResponse
import com.github.labibmuhajir.data.datasource.MovieDataSource
import com.github.labibmuhajir.di.Inject
import com.github.labibmuhajir.di.InjectEngine
import com.github.labibmuhajir.di.ProvidedObject
import com.github.labibmuhajir.domain.ViewState
import com.github.labibmuhajir.domain.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Inject
    lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var movieRepository: MovieDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        InjectEngine.apply {
            modules.add(ProvidedObject(MainViewModel(movieRepository)))
        }.inject(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        InjectEngine.resetInjection()
    }

    @Test
    fun `on getMovies success`() {
        dispatcher.runBlockingTest {
            val page = 1
            val expectedResponse = stubMovieResponse()
            val expectedData = listOf(Movie(expectedResponse.results!!.first()))
            given(movieRepository.getPopularMovies(page)).willReturn(expectedResponse)

            val expected = listOf<ViewState<List<Movie>>>(
                ViewState.Loading(),
                ViewState.Success(expectedData)
            )
            val actual = viewModel.movieState.getTestObserver()
            viewModel.getMovies()

            Mockito.verify(movieRepository).getPopularMovies(page)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }

    @Test
    fun `on getMovies error`() {
        dispatcher.runBlockingTest {
            val page = 1
            val exception = Exception()
            given(movieRepository.getPopularMovies(page)).willAnswer { throw exception }

            val expected = listOf<ViewState<List<Movie>>>(
                ViewState.Loading(),
                ViewState.Error()
            )
            val actual = viewModel.movieState.getTestObserver()
            viewModel.getMovies()

            Mockito.verify(movieRepository).getPopularMovies(page)
            Assert.assertEquals(expected, actual.observedValues)
        }
    }

    private fun stubMovieResponse(): PageableResponse {
        return PageableResponse(
            1,
            1,
            listOf(stubMovie()),
            10
        )
    }

    private fun stubMovie(): MovieResponse {
        return MovieResponse(
            "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
            "en",
            "Frozen II",
            false,
            "Frozen II",
            listOf(
                16,
                10402,
                10751
            ),
            "/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
            "/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg",
            "2019-11-20",
            326.113,
            7.1,
            330457,
            false,
            654
        )
    }
}