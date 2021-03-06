package com.madhurmaurya.cuecards.ui.editCueCard

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.databinding.EditCueCardFragmentBinding
import com.madhurmaurya.cuecards.ui.cueCard.CueCardContentRecyclerViewAdapter
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModel
import com.madhurmaurya.cuecards.ui.sharedViewModels.EditCueCardViewModel

class EditCueCardFragment : Fragment() {

    private lateinit var binding: EditCueCardFragmentBinding
    private val viewModel: EditCueCardViewModel by activityViewModels()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditCueCardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cueCardFragmentRecyclerView.adapter = CueCardContentRecyclerViewAdapter()
            titleInputEditText.text?.insert(0, viewModel.title)
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.contents.observe(viewLifecycleOwner) {
            val adapter = (binding.cueCardFragmentRecyclerView.adapter
                    as CueCardContentRecyclerViewAdapter)
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        binding.addCardContentButton.setOnClickListener { addContent() }
        binding.backButton.setOnClickListener { navigateUp() }
        binding.nextButton.setOnClickListener { done() }
    }

    private fun addContent() {
        val text = binding.contentInputEditText.text
        if (text.isNullOrEmpty()) {
            binding.contentTextField.isErrorEnabled = true
            binding.contentTextField.error = getString(R.string.content_empty)
        } else {
            viewModel.add(text.toString())
            binding.contentTextField.isErrorEnabled = false
            binding.contentInputEditText.text?.clear()
        }
    }

    private fun navigateUp() {
        viewModel.reset()
        findNavController().navigateUp()
    }

    private fun done() {
        val text = binding.titleInputEditText.text
        if (text.isNullOrEmpty()) {
            binding.titleTextField.isErrorEnabled = true
            binding.titleTextField.error = getString(R.string.title_empty)
        } else {
            binding.titleTextField.isErrorEnabled = false
            viewModel.setTitle(text.toString())
            if (viewModel.contents.value == null || viewModel.contents.value!!.isEmpty()) {
                binding.contentTextField.isErrorEnabled = true
                binding.contentTextField.error = getString(R.string.content_empty)
                return
            }
            Log.d("Edit", "reaching here")
            val sharedViewModel: CueCardsViewModel by activityViewModels()
            if(viewModel.id == null){
                sharedViewModel.add(viewModel.title, viewModel.color, viewModel.contents.value!!)
            }
            else {
                sharedViewModel.update(viewModel.id!!, viewModel.title, viewModel.color, viewModel.contents.value!!)
            }
            Log.d("Edit", "updated here")
            findNavController().navigateUp()
            viewModel.reset()
        }
    }
}