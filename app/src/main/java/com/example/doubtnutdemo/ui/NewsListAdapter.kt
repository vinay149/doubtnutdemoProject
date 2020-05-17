package com.example.doubtnutdemo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doubtnutdemo.DoubtNutDemoApplication
import com.example.doubtnutdemo.R
import com.example.doubtnutdemo.databinding.LayoutIssueItemBinding
import com.example.doubtnutdemo.model.NewsListEntity
import kotlinx.android.synthetic.main.layout_issue_item.view.*

class NewsListAdapter : PagedListAdapter<NewsListEntity, NewsListAdapter.IssueViewHolder>(DIFF) {


    lateinit var binding: LayoutIssueItemBinding
    lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IssueViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_issue_item, parent, false)
        binding.adapter = this
        return IssueViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item?.title
        holder.author.text = item?.author
        setImage(item?.imageUrl,holder.image)
        item?.let {
            holder.setData(item)
        }
    }

    private fun setImage(url:String?,view:ImageView){
        Glide.with(view.context)
            .load(url)
            .into(view)
    }

    inner class IssueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemData: NewsListEntity? = null
        var author:TextView = itemView.tv_news_author
        var title:TextView = itemView.tv_news_title
        var image:ImageView = itemView.image
        init {
            itemView.cd_issue_item.setOnClickListener {
                itemData?.title?.let { title ->
                    itemData?.let {data->
                        val detailsData = NewsDetails(
                            data.newsChannelname,
                            data.author,
                            data.imageUrl,
                            data.content,
                            data.link
                        )
                        DoubtNutDemoApplication.instance.appComponent.rxBus()
                            .send(ClickedHandlerOfNewsItem.of(detailsData))
                    }
                }
            }
        }

        fun setData(item: NewsListEntity) {
            itemData = item
            Log.d("getAuthorName","${item.author}")

        }

    }

    companion object {

        private val DIFF = object : DiffUtil.ItemCallback<NewsListEntity>() {
            override fun areItemsTheSame(
                oldItem: NewsListEntity,
                newItem: NewsListEntity
            ): Boolean {
                return oldItem.title == oldItem.title
            }

            override fun areContentsTheSame(
                oldItem: NewsListEntity,
                newItem: NewsListEntity
            ): Boolean {
                return oldItem.title == oldItem.title
            }

        }
    }
}

open class Evnet(val value: NewsDetails)
class ClickedHandlerOfNewsItem(data: NewsDetails) : Evnet(data) {
    companion object {
        fun of(data: NewsDetails) = ClickedHandlerOfNewsItem(data)
    }
}
data class NewsDetails(val newsName:String?, val authorName:String?, val imageUrl:String?,
                       val content:String?, val link:String?)