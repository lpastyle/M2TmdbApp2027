package com.example.m2tmdbapp2027

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.m2tmdbapp2027.databinding.FragmentSocialBarBinding

private const val SBF_KEY = "sbf_key"

class SocialBarFragment : Fragment() {
   private lateinit var binding : FragmentSocialBarBinding
    private var cn : Int = Color.LTGRAY
    private var cs : Int = Color.RED

    private var mapkey : Int? = null

    companion object {
        fun newInstance(param1: String, param2: String) =
            SocialBarFragment().apply {
                arguments = Bundle().apply {
                    putInt(SBF_KEY, mapkey as Int)
                }
            }
    }

    private val viewModel by activityViewModels<SocialBarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mapkey = it.getString(SBF_KEY)?.toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialBarBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cn = requireContext().getColor(R.color.ic_social_normal)
        cs = requireContext().getColor(R.color.ic_social_selected)

        // get the map key associated to this person
        //val mapkey = 0

        // Set nb like text and icon color
        binding.nbLikeTv.text = viewModel.nbLikes.getOrElse(mapkey as Int, {0}).toString()

    }
}