package com.example.noteit.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.noteit.R
import com.example.noteit.databinding.FragmentCreateBinding
import com.example.noteit.databinding.FragmentHomeBinding
import com.example.noteit.datafiles.Notes
import com.example.noteit.datafiles.ViewModelNotes
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateFragment : Fragment() {
    // TODO: Rename and change types of parameters
     val viewModel: ViewModelNotes by viewModels()
lateinit var binding: FragmentCreateBinding
var priority = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(inflater,container,false)





        binding.pred.setOnClickListener {
            priority=1
            binding.pred.setImageResource(R.drawable.checkbtn)
            binding.porg.setImageResource(0)
            binding.pyellow.setImageResource(0)

        }

 binding.porg.setOnClickListener {
            priority=2
            binding.porg.setImageResource(R.drawable.checkbtn)
            binding.pred.setImageResource(0)
            binding.pyellow.setImageResource(0)

        }

 binding.pyellow.setOnClickListener {
            priority=3
            binding.pyellow.setImageResource(R.drawable.checkbtn)
            binding.porg.setImageResource(0)
            binding.pred.setImageResource(0)

        }


        binding.AddItButton.setOnClickListener {
    CreateNote(it)
            Toast.makeText(context,"InfoCreated",Toast.LENGTH_SHORT).show()


        }
        return binding.root


    }

    private fun CreateNote(it : View?) {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val notesData = binding.editNote.text.toString()
        val date = getCurrentDate()
        val pr = priority.toString()
        val anote = Notes(0,title,subtitle,notesData,date,pr)
        viewModel.insertNote(anote)


        Navigation.findNavController(it!!).navigate(R.id.action_createFragment_to_homeFragment)
    }

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // You can adjust the format as needed
        return dateFormat.format(calendar.time)
    }
}