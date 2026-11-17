package com.example.m2tmdbapp2027

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.m2tmdbapp2027.data.db.SocialBarEntity
import com.example.m2tmdbapp2027.databinding.FragmentSocialBarBinding

const val SBFC_VIEW_TAG = "sbfcv_view_tag"

class SocialBarFragment : Fragment() {
    private val LOGTAG = SocialBarFragment::class.simpleName

    private lateinit var binding : FragmentSocialBarBinding
    private var cn : Int = Color.LTGRAY
    private var cs : Int = Color.RED

    companion object {
        fun newInstance() = SocialBarFragment
    }

    private val viewModel by activityViewModels<SocialBarViewModel> {
        SocialBarViewModel.SocialBarViewModelFactory(
            (requireActivity().application as TmdbApplication).database.socialBarDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        requireArguments().getString(SBFC_VIEW_TAG)?.let {
            val mapkey = it.toInt()

            // set like button
            viewModel.nbLikes.observe(viewLifecycleOwner) { likes ->
                val nblikes = likes.getOrElse(mapkey, { 0 })
                binding.nbLikeTv.text = nblikes.toString()
                val likeColor = if (nblikes > 0) cs else cn
                binding.likeIv.setColorFilter(likeColor)
                binding.nbLikeTv.setTextColor(likeColor)
            }
            binding.likeIv.setOnClickListener {
                var nbLikesNow = viewModel.nbLikes.value?.getOrElse(mapkey) { 0 } ?: 0
                var isFavorite = viewModel.isFavorite.value?.getOrElse(mapkey) { false} ?: false
                nbLikesNow++
                viewModel.save(SocialBarEntity(mapkey, isFavorite, nbLikesNow))
                /* UI update no longer needed here. Done by the livedata observer
                viewModel.nbLikes[mapkey] = viewModel.nbLikes.getOrElse(mapkey, { 0 }) + 1
                binding.nbLikeTv.text = viewModel.nbLikes[mapkey].toString()
                binding.likeIv.setColorFilter(cs)
                binding.nbLikeTv.setTextColor(cs) */
            }

            // set favorite button
            viewModel.isFavorite.observe(viewLifecycleOwner) { favorites ->
                val isFavorite = favorites.getOrElse(mapkey, { false })
                binding.favoriteIv.setColorFilter(if (isFavorite) cs else cn)
            }
            binding.favoriteIv.setOnClickListener {
                var nbLikes = viewModel.nbLikes.value?.getOrElse(mapkey) { 0 } ?: 0
                var isFavoriteNow = viewModel.isFavorite.value?.getOrElse(mapkey) { false} ?: false
                isFavoriteNow != isFavoriteNow
                viewModel.save(SocialBarEntity(mapkey, isFavoriteNow, nbLikes))

                /*UI update no longer needed here. Done by the livedata observer
                   viewModel.isFavorite[mapkey] =
                    !viewModel.isFavorite.getOrElse(mapkey, { false })
                binding.favoriteIv.setColorFilter(if (viewModel.isFavorite[mapkey] == true) cs else cn)*/

            }

            // set share button
            binding.shareIv.setColorFilter(cn)
            binding.shareIv.setOnClickListener {
                Log.d(LOGTAG,"shared clicked for id=${mapkey}")
            }
        }
    }
}