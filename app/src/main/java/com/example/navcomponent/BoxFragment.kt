package com.example.navcomponent

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navcomponent.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment: Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        val color = args.color

        binding.root.setBackgroundColor(color)

        binding.goBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.openSecretButton.setOnClickListener {
            findNavController().navigate(BoxFragmentDirections.actionBoxFragmentToSecretFragment())
        }

        binding.generateNumberButton.setOnClickListener {
            val number =  Random.nextInt(100)
            // send the result
            publishResults(EXTRA_RANDOM_NUMBER, number)
            //go back to the previous screen, also navigateUp cen be used
            findNavController().popBackStack()
        }
    }

    companion object {
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
    }
}