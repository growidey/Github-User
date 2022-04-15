package com.dheaninda.part1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheaninda.part1.R
import com.dheaninda.part1.adapter.UserAdapter
import com.dheaninda.part1.databinding.FragmentFollowBinding
import com.dheaninda.part1.model.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_follow) {

    private var vbinding: FragmentFollowBinding? = null
    private val binding get() = vbinding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args = arguments
        username = args?.getString(UserDetailActivity.EXTRA_USERNAME).toString()
        vbinding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }
        showProgressBar(true)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowingViewModel::class.java]
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showProgressBar(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vbinding = null
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressbar.visibility = if (state) View.VISIBLE else View.GONE
    }
}