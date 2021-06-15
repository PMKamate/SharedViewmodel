package com.practicaltest.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.practicaltest.sharedviewmodel.MovieAdapter.OnItemCLickListener



class MasterFragment : Fragment(), OnItemCLickListener {
    lateinit var recyclerView: RecyclerView
    private var movieAdapter: MovieAdapter? = null
    private var navController: NavController? = null
    private var sharedViewModel: SharedViewModel? = null
    private val movieList: MutableList<Movie> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.matser_fragment, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.recyclerView)
        movieAdapter = MovieAdapter(movieList, this)
        recyclerView.setAdapter(movieAdapter)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel!!.movieList.observe(viewLifecycleOwner, { movies->
            movieList.clear()
            movieList.addAll(movies!!)
            movieAdapter!!.notifyDataSetChanged()
        })
    }

    override fun onItemClick(position: Int) {
//        Toast.makeText(requireContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        sharedViewModel!!.setMovie(position)
        navController?.navigate(R.id.action_masterFragment_to_detailFragment)
    }
}











