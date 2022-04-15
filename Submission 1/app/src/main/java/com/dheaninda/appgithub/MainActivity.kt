package com.dheaninda.appgithub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheaninda.appgithub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vBinding: ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vBinding.root)

        vBinding.rvUsers.setHasFixedSize(true)
        list.addAll(listUser)
        showRecyclerView()
    }

    private val listUser: ArrayList<User>
        get() {
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataFollower = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val listUser = ArrayList<User>()
            for(i in dataUsername.indices) {
                val user = User(
                    dataUsername[i],
                    dataName[i],
                    dataAvatar.getResourceId(i, -1),
                    dataCompany[i],
                    dataLocation[i],
                    dataRepository[i],
                    dataFollower[i],
                    dataFollowing[i]
                )
                listUser.add(user)

            }
            return listUser
        }
    private fun showRecyclerView(){
        vBinding.rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        vBinding.rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback((object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Toast.makeText(this@MainActivity, data.username, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
                intent.putExtra(UserDetailActivity.EXTRA_USER, data)
                startActivity(intent)
            }
        }))
    }
}