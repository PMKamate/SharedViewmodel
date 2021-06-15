package com.practicaltest.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation


/**
 * A simple [detailFragment] subclass.
 */
class DetailFragment : Fragment() {
    private var nameTextView: TextView? = null
    private var ratingTextView: TextView? = null
    private var navController: NavController? = null
    private var sharedViewModel: SharedViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTextView = view.findViewById(R.id.nameTextView)
        ratingTextView = view.findViewById(R.id.ratingTextView)
        navController = Navigation.findNavController(view)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel!!.movie.observe(viewLifecycleOwner,
            { movie -> updateUI(movie) })
    }

    private fun updateUI(movie: Movie) {
        nameTextView!!.text = movie.name
        ratingTextView!!.text = movie.rating.toString()
    }
}
