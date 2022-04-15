package com.dheaninda.part1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dheaninda.part1.adapter.SectionPagerAdapter
import com.dheaninda.part1.databinding.ActivityUserDetailBinding
import com.dheaninda.part1.model.DetailViewModel

class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showProgressBar(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java)
        viewModel.setUserDetail(username!!)
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                showProgressBar(false)
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvCompany.text = it.company
                    tvLocation.text = it.location
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    tvRepository.text = "${it.public_repos} Repository"
                    Glide.with(this@UserDetail)
                        .load(it.avatar_url)
                        .into(ivProfile)

                }
            }
        }
        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun showProgressBar(state: Boolean) {
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }

    }    }