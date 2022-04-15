package com.dheaninda.appgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dheaninda.appgithub.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }
   private lateinit var vBinding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        vBinding.ivUser.setImageResource(user.avatar)
        vBinding.tvName.text = user.name
        vBinding.tvUsername.text = user.username
        vBinding.tvFollowers.text = user.follower
        vBinding.tvFollowing.text = user.following
        vBinding.btCompany.text = user.company
        vBinding.btRepository.text = user.repository
        vBinding.tvLocation.text = user.location
    }

}