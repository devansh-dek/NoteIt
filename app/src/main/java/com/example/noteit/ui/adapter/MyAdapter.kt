package com.example.noteit.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteit.R
import com.example.noteit.databinding.NotesboxBinding
import com.example.noteit.datafiles.Notes
import com.example.noteit.ui.fragments.HomeFragment
import com.example.noteit.ui.fragments.HomeFragmentDirections
import java.util.ArrayList

class MyAdapter( var context: Context,var notesData : List<Notes>): RecyclerView.Adapter<MyAdapter.AdapterViewHolder>() {
    class AdapterViewHolder(val binding: NotesboxBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
return AdapterViewHolder(NotesboxBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun getItemCount(): Int {
        return notesData.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val dataset = notesData[position]
            holder.binding.data.text = dataset.note
            holder.binding.date.text = dataset.DateToday
            holder.binding.title.text = dataset.Title
            holder.binding.subtitle.text= dataset.subTitle
when(dataset.priority){
    "1" -> {
        holder.binding.priority.setBackgroundResource(R.drawable.reddot)


    }
    "2" -> {
        holder.binding.priority.setBackgroundResource(R.drawable.orangedot)

    }
    "3" -> {
        holder.binding.priority.setBackgroundResource(R.drawable.yellowdot)

    }



}

        holder.binding.root.setOnClickListener {
            val act = HomeFragmentDirections.actionHomeFragmentToEditFragment(dataset)
            Navigation.findNavController(it!!).navigate(act)
        }


    }

    fun filtering(newListFiltered: ArrayList<Notes>) {
        notesData = newListFiltered
        notifyDataSetChanged()
    }


}