package com.meruj.picturesandweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.meruj.picturesandweather.R
import com.meruj.picturesandweather.ui.adapters.PicturesAdapter
import com.meruj.picturesandweather.ui.viewmodel.PicturesViewModel
import com.meruj.picturesandweather.util.Status
import kotlinx.android.synthetic.main.fragment_pictures.*

class PicturesFragment : Fragment() {
    lateinit var viewModel: PicturesViewModel
    private var mAdapter = PicturesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PicturesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_pictures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        picturesRV.layoutManager = LinearLayoutManager(activity)
        picturesRV.adapter = mAdapter
        previousPageBtn.setOnClickListener {
            picturesRV.scrollToPosition(0)
            viewModel.loadPrewItems()
            loadData()
        }
        nextPageBtn.setOnClickListener {
            picturesRV.scrollToPosition(0)
            viewModel.loadNextItems()
            loadData()
        }
        loadData()
    }

    private fun loadData() {
        viewModel.getPictures().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        mAdapter.setRecyclerData(it1)
                        mAdapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                }
            }
        })
    }
}