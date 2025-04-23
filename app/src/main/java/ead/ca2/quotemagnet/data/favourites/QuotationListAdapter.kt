package ead.ca2.quotemagnet.data.favourites

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater


import ead.ca2.quotemagnet.domain.model.Quotation
import ead.ca2.quotemagnet.databinding.QuotationItemBinding


class QuotationListAdapter(private val onItemClick: (String) -> Unit) : ListAdapter<Quotation, QuotationListAdapter.ViewHolder>(QuotationDiff()) {
    class QuotationDiff : DiffUtil.ItemCallback<Quotation>(){
        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(private val binding: QuotationItemBinding, val onItemClick: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quotation: Quotation) {
            binding.quotationAuthor.text = quotation.author
            binding.quotationText.text = quotation.text
        }
        init {
            binding.root.setOnClickListener{
                onItemClick(binding.quotationAuthor.text.toString())

            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(QuotationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemClick)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}