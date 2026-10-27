package com.example.m2tmdbapp2027

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m2tmdbapp2027.data.remote.Person
import com.example.m2tmdbapp2027.databinding.PersonItemBinding

class PersonAdapter(private val persons: ArrayList<Person>, context: Context) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>()  {
    private var scoreRatings: Array<String> = context.resources.getStringArray(R.array.score_rating)
    private var ratingColors: Array<String> = context.resources.getStringArray(R.array.rating_colors)

    private var maxPopularity = 0f

    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                maxPopularity = persons.maxOfOrNull { it.popularity } ?: 0f
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

    inner class  PersonViewHolder(private val binding : PersonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.person = person
            binding.scoreMax = maxPopularity
            binding.executePendingBindings()
        }
    }

    private fun getRating(value: Double?, max: Double): String {
        val index = mapValueToIndex(value, max, scoreRatings.size)
        return scoreRatings[index]
    }

    private fun getScoreColor(value: Double?, max: Double): Int {
        val index = mapValueToIndex(value, max, ratingColors.size)
        return ratingColors[index].toInt()
    }

    private fun mapValueToIndex(value: Double?, max: Double, size: Int): Int =
        if (value != null && max > 0.0) ((size * value) / max).toInt().coerceAtMost(size - 1) else 0


}