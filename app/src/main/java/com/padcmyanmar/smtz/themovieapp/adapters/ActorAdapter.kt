package com.padcmyanmar.smtz.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.themovieapp.R
import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themovieapp.viewHolders.ActorViewHolder

class ActorAdapter : RecyclerView.Adapter<ActorViewHolder>() {

    private var mActorsList : List<ActorVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor_list,parent,false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        if(mActorsList.isNotEmpty()){
            holder.bindData(mActorsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mActorsList.count()
    }

    fun setNewData(movieList: List<ActorVO>) {
        mActorsList = movieList
        notifyDataSetChanged()
    }
}