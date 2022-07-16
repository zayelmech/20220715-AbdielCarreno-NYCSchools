package com.imecatro.nyc_schools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imecatro.nyc_schools.databinding.CardItemBinding
import com.imecatro.nyc_schools.model.Schools

class SchoolsAdapter(
    private val schoolsSet: List<Schools>,
    private val onCardClicked: (String) -> Unit

) : RecyclerView.Adapter<SchoolsAdapter.SchoolsViewHolder>() {

    inner class SchoolsViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(school: Schools) {
            binding.apply {
                schoolsName.text = school.schoolName
                schoolsWebsite.text = school.website
                itemView.setOnClickListener {
                    onCardClicked(school.dbn)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolsViewHolder =
        SchoolsViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        )

    override fun onBindViewHolder(holder: SchoolsViewHolder, position: Int) =
        holder.bind(schoolsSet[position])

    override fun getItemCount(): Int = schoolsSet.size

}