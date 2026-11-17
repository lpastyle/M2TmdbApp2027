package com.example.m2tmdbapp2027

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commitNow
import androidx.recyclerview.widget.RecyclerView
import com.example.m2tmdbapp2027.data.remote.Person
import com.example.m2tmdbapp2027.databinding.PersonItemBinding
import kotlin.jvm.java

class PersonAdapter(private val persons: ArrayList<Person>, private val context: AppCompatActivity) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>()  {
    private val LOGTAG= PersonAdapter::class.simpleName
    private var scoreRatings: Array<String> = context.resources.getStringArray(R.array.score_rating)
    private var ratingColors: Array<String> = context.resources.getStringArray(R.array.rating_colors)

    private var maxPopularity = 0f

    private var onPersonItemClickedListener: OnPersonItemClickedListener? = null

    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                maxPopularity = persons.maxOfOrNull { it.popularity } ?: 0f
                saveHighestPopularity()
            }
        })

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PersonViewHolder(
        PersonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =
     holder.bind(persons.get(position))


    override fun getItemCount(): Int = persons.size

    override fun onViewAttachedToWindow(holder: PersonViewHolder) {
        super.onViewAttachedToWindow(holder)
        val sbfcv = holder.binding.socialBarFcv
        val tag = sbfcv.tag?.toString()
        val bundle = Bundle().apply {
            putString(SBFC_VIEW_TAG, tag)
        }
        context.supportFragmentManager.commitNow {
            Log.d(LOGTAG,"replace ${sbfcv.tag}")
            replace (
                sbfcv.id,
                SocialBarFragment::class.java,
                bundle,
                tag
            )
        }
    }

    override fun onViewDetachedFromWindow(holder: PersonViewHolder) {
        super.onViewDetachedFromWindow(holder)
        val fm = context.supportFragmentManager
        // After onSaveInstanceState (rotation, activity destroyed) transactions are forbidden,
        // and the FragmentManager takes care of its fragments by itself.
        if (fm.isStateSaved) return

        // Remove the fragment
        fm.findFragmentById(holder.binding.socialBarFcv.id)?.let { fragment ->
            fm.commitNow {
                Log.d(LOGTAG,"remove ${fragment.tag}")
                remove(fragment)
            }
        }
    }

    inner class  PersonViewHolder(val binding : PersonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.person = person
            binding.scoreMax = maxPopularity
            binding.scoreLabel = getRating(person.popularity, maxPopularity)
            binding.scoreColor = getScoreColor(person.popularity, maxPopularity)
            binding.socialBarFcv.id = View.generateViewId()
            binding.socialBarFcv.tag = person.id.toString()
            binding.personItemCl.setOnClickListener {
                onPersonItemClickedListener?.onPersonItemClicked(person)
            }
            binding.executePendingBindings()
        }
    }

    private fun getRating(value: Float?, max: Float): String {
        val index = mapValueToIndex(value, max, scoreRatings.size)
        return scoreRatings[index]
    }

    private fun getScoreColor(value: Float?, max: Float): Int {
        val index = mapValueToIndex(value, max, ratingColors.size)
        return ratingColors[index].toInt()
    }

    private fun mapValueToIndex(value: Float?, max: Float, size: Int): Int =
        if (value != null && max > 0.0) ((size * value) / max).toInt().coerceAtMost(size - 1) else 0

    fun saveHighestPopularity() {
        val sharedPref = context.getPreferences(Context.MODE_PRIVATE)
        val highestPopularity = sharedPref.getFloat(SAVED_HIGHEST_POPULARITY_SPKEY, 0f)
        if (maxPopularity > highestPopularity) {
            sharedPref.edit {
                putFloat(
                    SAVED_HIGHEST_POPULARITY_SPKEY,
                    maxPopularity
                )
            }
        }
    }

    fun setPersonItemClickedListener(listener: OnPersonItemClickedListener) {
        this.onPersonItemClickedListener =listener
    }
}