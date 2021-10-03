package com.sample.showroomtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.showroomtest.databinding.RepositoryListItemBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private var repositoryList: List<Item>? = null

    fun setRepositoryList(repositoryList: List<Item>?) {
        if (this.repositoryList == null) {
            this.repositoryList = repositoryList
            if (repositoryList != null) {
                notifyItemRangeInserted(0, repositoryList.size)
            }
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@RepositoryAdapter.repositoryList).size
                }

                override fun getNewListSize(): Int {
                    if (repositoryList != null) {
                        return repositoryList.size
                    }
                    return 0
                }


                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldList = this@RepositoryAdapter.repositoryList
                    return oldList?.get(oldItemPosition)?.id == repositoryList?.get(newItemPosition)?.id
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val repository = repositoryList?.get(newItemPosition)
                    val old = repositoryList?.get(oldItemPosition)
                    return repository?.id == old?.id && repository?.name == old?.name
                }
            })
            this.repositoryList = repositoryList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.binding.repository = repositoryList?.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return repositoryList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.repository_list_item, parent,
            false
        ) as RepositoryListItemBinding
        return RepositoryViewHolder(binding)
    }

    open class RepositoryViewHolder(val binding: RepositoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}