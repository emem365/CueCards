package com.madhurmaurya.cuecards.ui.cueCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.madhurmaurya.cuecards.CardsApplication
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.databinding.PresentationModeFragmentBinding

class PresentationModeFragment : Fragment() {

    private lateinit var binding: PresentationModeFragmentBinding
    private val viewModel: SingleCueCardViewModel by activityViewModels{
        val database = (activity?.application as CardsApplication).database
        SingleCueCardViewModelFactory(database.cueCardDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PresentationModeFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            color = viewModel.cueCardWithCueCardContents.value?.cueCard?.color ?: R.color.pastel_red
            lifecycleOwner = viewLifecycleOwner
            presentationViewPager.adapter = PresentationModeViewPagerAdapter(presentationViewPager)

        }
        viewModel.cueCardWithCueCardContents.observe(viewLifecycleOwner){
            (binding.presentationViewPager.adapter as PresentationModeViewPagerAdapter)
                .submitList(it.cueCardContents)
            binding.color = it.cueCard.color
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener { navigateUp() }
    }

    private fun navigateUp(){
        findNavController().navigateUp()
    }
}