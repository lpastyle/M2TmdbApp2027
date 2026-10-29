package com.example.m2tmdbapp2027

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m2tmdbapp2027.databinding.FragmentBlankBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val LOGTAG = BlankFragment::class.simpleName
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBlankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOGTAG, "onCreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOGTAG, "onCreateView")
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_blank, container, false)
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(LOGTAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        binding.param1Tv.text = param1
        binding.param2Tv.text = param2


    }
    override fun onStart() {
        super.onStart()
        Log.d(LOGTAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOGTAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOGTAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOGTAG, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOGTAG, "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(LOGTAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOGTAG, "onDestroy")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(LOGTAG, "onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(LOGTAG, "onDetach")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}