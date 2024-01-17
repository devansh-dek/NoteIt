package com.example.noteit.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteit.R
import com.example.noteit.databinding.FragmentCreateBinding
import com.example.noteit.databinding.FragmentEditBinding
import com.example.noteit.datafiles.Notes
import com.example.noteit.datafiles.ViewModelNotes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {
    var prior =1.toString()
    val notes by navArgs<EditFragmentArgs>()
    val viewModel: ViewModelNotes by viewModels()
    lateinit var binding: FragmentEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditBinding.inflate(inflater,container,false)
setHasOptionsMenu(true)
        binding.editTitle.setText(notes.dataTransfer.Title)
        binding.editSubTitle.setText(notes.dataTransfer.subTitle)
        binding.editNote.setText(notes.dataTransfer.note)

        when(notes.dataTransfer.priority) {
            "1" -> {
                prior = 1.toString()
                binding.pred.setImageResource(R.drawable.checkbtn)
                binding.porg.setImageResource(0)
                binding.pyellow.setImageResource(0)


            }

            "2" -> {
                prior = 2.toString()
                binding.porg.setImageResource(R.drawable.checkbtn)
                binding.pred.setImageResource(0)
                binding.pyellow.setImageResource(0)
            }

            "3" -> {
                prior = 3.toString()
                binding.pyellow.setImageResource(R.drawable.checkbtn)
                binding.porg.setImageResource(0)
                binding.pred.setImageResource(0)
            }


        }
        binding.pred.setOnClickListener {
            prior=1.toString()
            binding.pred.setImageResource(R.drawable.checkbtn)
            binding.porg.setImageResource(0)
            binding.pyellow.setImageResource(0)

        }

        binding.porg.setOnClickListener {
            prior=2.toString()
            binding.porg.setImageResource(R.drawable.checkbtn)
            binding.pred.setImageResource(0)
            binding.pyellow.setImageResource(0)

        }

        binding.pyellow.setOnClickListener {
            prior=3.toString()
            binding.pyellow.setImageResource(R.drawable.checkbtn)
            binding.porg.setImageResource(0)
            binding.pred.setImageResource(0)

        }


        binding.AddItButton.setOnClickListener {

            updateData(it)

        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.editnavbar,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delteIT -> { // Corrected the typo in "delteIT"
                val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
                bottomSheet.setContentView(R.layout.btmshet)
                bottomSheet.show()



                val yesbtn = bottomSheet.findViewById<Button>(R.id.yesdel)
                val nobtn = bottomSheet.findViewById<Button>(R.id.nodel)
                if (yesbtn != null) {
                    yesbtn.setOnClickListener {
                        viewModel.delteNote(notes.dataTransfer.id)
bottomSheet.dismiss()
                    }
                }
                if (nobtn != null) {
                    nobtn.setOnClickListener {
                        bottomSheet.dismiss()
                    }

                }



            }





        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateData(it: View?) {

        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val notesData = binding.editNote.text.toString()
        val date = getCurrentDate()
        val pr = prior
        val anote = Notes(notes.dataTransfer.id,title,subtitle,notesData,date,pr)
        viewModel.updateNote(anote)


        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)
    }
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // You can adjust the format as needed
        return dateFormat.format(calendar.time)
    }

}