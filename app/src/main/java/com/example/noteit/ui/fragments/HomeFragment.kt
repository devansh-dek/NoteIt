package com.example.noteit.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.SearchView
import android.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteit.R
import com.example.noteit.databinding.FragmentHomeBinding
import com.example.noteit.datafiles.Notes
import com.example.noteit.datafiles.ViewModelNotes
import com.example.noteit.ui.adapter.MyAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationBarItemView


class HomeFragment : Fragment() {

lateinit var binding : FragmentHomeBinding

    val viewModel: ViewModelNotes by viewModels()
    var oldNote  = arrayListOf<Notes>()
    lateinit var adapter: MyAdapter

override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding = FragmentHomeBinding.inflate(inflater,container,false)



    binding.recycleView.layoutManager = GridLayoutManager(requireContext(),2)



    viewModel.showNotes().observe(viewLifecycleOwner,{
        oldNote = it as ArrayList<Notes>
        adapter =MyAdapter(requireContext(), it )
        binding.recycleView.adapter = adapter
    })

    binding.allbtn.setOnClickListener {
        viewModel.showNotes().observe(viewLifecycleOwner,{

            oldNote = it as ArrayList<Notes>
            adapter =MyAdapter(requireContext(), it )
            binding.recycleView.adapter = adapter
        })

    }
    binding.highbtn.setOnClickListener {
        viewModel.showNotesred().observe(viewLifecycleOwner,{
            oldNote = it as ArrayList<Notes>
            adapter =MyAdapter(requireContext(), it )
            binding.recycleView.adapter = adapter
        })
    }
    binding.midbtn.setOnClickListener {
        viewModel.showNotesOrg().observe(viewLifecycleOwner,{
            oldNote = it as ArrayList<Notes>
            adapter =MyAdapter(requireContext(), it )
            binding.recycleView.adapter = adapter
        })
    }
    binding.lowbtn.setOnClickListener {
        viewModel.showNotesYellow().observe(viewLifecycleOwner,{
            oldNote = it as ArrayList<Notes>
            adapter =MyAdapter(requireContext(), it )
            binding.recycleView.adapter = adapter
        })
    }



        binding.AddNotes.setOnClickListener {
            Log.e("&&&&&&&&777","taped on create notes")
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.searchbar,menu)

        val item = menu.findItem(R.id.app_bar_search)
        if(item==null){
            Log.e("######","Item is null")
        }else{
            Log.e("######","Item is not null")

        }
        val searchVIEW = item.actionView as SearchView
        if (searchVIEW != null) {
            searchVIEW.queryHint = "Enter Title/Subtitle"
        }
        else{
            Log.e("@@@@@@","searchview is null boi")
        }
        if (searchVIEW != null) {
            searchVIEW.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false

                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    NotesFiltering(p0)
                    return true

                }


            })
        }



        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {
        var newListFiltered = arrayListOf<Notes>()

        for(i in oldNote){
            if(i.Title.contains(p0!!) || i.subTitle.contains(p0!!)){
                newListFiltered.add(i)
            }

        }
adapter.filtering(newListFiltered)
    }


}