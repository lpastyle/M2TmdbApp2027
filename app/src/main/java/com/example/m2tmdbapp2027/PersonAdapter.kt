package com.example.m2tmdbapp2027

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m2tmdbapp2027.data.remote.Person
import com.example.m2tmdbapp2027.databinding.PersonItemBinding

class PersonAdapter(private val persons: ArrayList<Person>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>()  {
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

    class  PersonViewHolder(private val binding : PersonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.person = person
        }
    }
}