package com.suhov.memappwocompose.presentation.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coub.player.CoubPlayer
import com.suhov.memappwocompose.R
import com.suhov.memappwocompose.model.MemNetworkEntity
import kotlinx.android.synthetic.main.item_detail_view.view.*
import kotlinx.android.synthetic.main.item_mem.view.*
import kotlinx.android.synthetic.main.item_mem.view.imageView
import java.lang.Exception

object ViewHolderFactory {
    fun get(view: View, viewType: Int): RecyclerView.ViewHolder =
            when(viewType){
                R.layout.item_mem -> CreateForListHolder(view)
                R.layout.item_detail_view -> CreateDetailHolder(view)
                else -> throw Exception("Wrong view type")
            }

    class CreateForListHolder(view: View) : RecyclerView.ViewHolder(view),
            GenericAdapter.Binder<MemNetworkEntity> {
        override fun bind(
            data: MemNetworkEntity,
            listener: OnItemClickListener<MemNetworkEntity>?) {
            itemView.apply {
                item_mem.setOnClickListener { listener?.onClickItem(data) }

                data.author.let { topicTextView.text = it }
                data.description.let { headingTextView.text = it }
                data.date.let { dateTextView.text = it }
                data.previewURL.let{ Glide.with(this)
                            .load(it)
                            .centerCrop()
                            .placeholder(R.drawable.ic_placeholder_background)
                            .into(imageView)
                        }
            }

        }
    }
    class CreateDetailHolder(view: View) : RecyclerView.ViewHolder(view),
            GenericAdapter.Binder<MemNetworkEntity> {
        override fun bind(
            data: MemNetworkEntity,
            listener: OnItemClickListener<MemNetworkEntity>?) {
           itemView.apply {
               data.embedId.let {
                   val coubPlayer = CoubPlayer.Factory.getInstance(context)
                   coubPlayer.injectInto(test) // player will remove all existing views in container
                   coubPlayer.load(it!!) // https://coub.com/view/${permalink}
                   coubPlayer.setPlayWhenReady(true)
               }
               data.gifURL.let {
                   Glide.with(this)
                           .load(it)
                           .centerCrop()
                           .placeholder(R.drawable.ic_placeholder_background)
                           .into(imageView)
               }
               data.author.let { author.text = it }
               data.description.let { description.text = it }
           }
        }

    }
}