package com.practicaltest.sharedviewmodel

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class SharedViewModel : ViewModel() {
    private val mutableMovieList = MutableLiveData<MutableList<Movie>?>()
    private val mutableMovie = MutableLiveData<Movie>()
    val movieList: LiveData<MutableList<Movie>?>
        get() {
            if (mutableMovieList.value == null) {
                LoadMovies().execute()
            }
            return mutableMovieList
        }
    val movie: LiveData<Movie>
        get() = mutableMovie

    fun setMovie(position: Int) {
        val movie = mutableMovieList.value!![position]
        mutableMovie.value = movie
    }

    fun addMovie(movie: Movie) {
        if (mutableMovieList.value != null) {
            val movieList = mutableMovieList.value
            movieList!!.add(movie)
            mutableMovieList.value = movieList
            return
        }
        val movieList: MutableList<Movie> = ArrayList()
        movieList.add(movie)
        mutableMovieList.value = movieList
    }

    @Suppress("DEPRECATION")
    internal inner class LoadMovies :
        AsyncTask<Void?, Void?, Void?>() {
        protected override fun doInBackground(vararg p0: Void?): Void? {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            val movieList: MutableList<Movie> = ArrayList()
            movieList.add(Movie("Captain America", 8.8))
            movieList.add(Movie("Iron Man", 7.8))
            movieList.add(Movie("Avengers", 6.8))
            mutableMovieList.postValue(movieList)
        }
    }

    companion object {
        private const val TAG = "SharedViewModel"
    }
}
