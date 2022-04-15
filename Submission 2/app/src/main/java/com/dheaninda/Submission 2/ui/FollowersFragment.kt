package com.dheaninda.part1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheaninda.part1.R
import com.dheaninda.part1.adapter.UserAdapter
import com.dheaninda.part1.databinding.FragmentFollowBinding
import com.dheaninda.part1.model.FollowersViewModel

class FollowersFragment: Fragment(R.layout.fragment_follow) {

    private var vbinding : FragmentFollowBinding? = null
    private val binding get()= vbinding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(UserDetail.EXTRA_USERNAME).toString()
        vbinding = FragmentFollowBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }
        showProgressBar(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner, {
            if(it!=null){
                adapter.setList(it)
                showProgressBar(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vbinding = null
    }

    private fun showProgressBar(state: Boolean){
        if (state){
            binding.progressbar.visibility = View.VISIBLE
        }else{
            binding.progressbar.visibility = View.GONE
        }
    }
}