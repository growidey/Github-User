package com.dheaninda.appgithub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dheaninda.appgithub.databinding.ItemUserBinding

class ListUserAdapter(private val listUser:ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder (var binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, name, avatar, company, location, repository, follower, following) = listUser[position]
        holder.binding.tvItemUsername.text = username
        holder.binding.tvItemName.text = name
        holder.binding.imgItemPhoto.setImageResource(avatar)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }
}