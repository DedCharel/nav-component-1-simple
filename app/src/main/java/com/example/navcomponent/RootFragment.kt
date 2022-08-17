package com.example.navcomponent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.navcomponent.databinding.FragmentRootBinding

class RootFragment: Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(255, 255, 200))
        }

        binding.openYellowBoxButton.setOnClickListener {
            openBox(Color.rgb(200, 255, 200))
        }

        parentFragmentManager.setFragmentResultListener(BoxFragment.REQUEST_CODE, viewLifecycleOwner){ _, data ->
            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
            Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openBox(color: Int){
        findNavController().navigate(
            R.id.action_rootFragment_to_boxFragment, // nav action to be executed
            bundleOf(BoxFragment.ARG_COLOR to color), // arguments for the destination
            // optional additional options, example of simple animation:
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            }
        )

    }
}