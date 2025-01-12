package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.models.Article
import com.example.newsapp.R


class NewsAdapter :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    lateinit var articleImage:ImageView
    lateinit var articleSource: TextView
    lateinit var articleTitle:TextView
    lateinit var articleDescription: TextView
    lateinit var articleDateTime:TextView

    private val differCallback =object : DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem:Article,newItem:Article):Boolean{
        return oldItem.url==newItem.url
    }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }
    val differ=AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article)->Unit)?=null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=differ.currentList[position]

         articleImage = holder.itemView.findViewById<ImageView>(R.id.articleImage)
         articleSource = holder.itemView.findViewById<TextView>(R.id.articleSource)
         articleTitle = holder.itemView.findViewById<TextView>(R.id.articleTitle)
         articleDescription = holder.itemView.findViewById<TextView>(R.id.articleDescription)
         articleDateTime = holder.itemView.findViewById<TextView>(R.id.articleDateTime)

        holder.itemView.apply {
            val imageUrl = article.urlToImage
            if (imageUrl.isNullOrEmpty()) {
                Glide.with(this)
                    .load(R.drawable.baseline_image_not_supported_24) // Imaginea implicită
                    .into(articleImage)
            } else {
                Glide.with(this)
                    .load(imageUrl)
                    .into(articleImage)
            }

            articleSource.text=article.source?.name
            articleTitle.text=article.title
            articleDescription.text=article.description
            articleDateTime.text=article.publishedAt

            setOnClickListener{
                onItemClickListener?.let{
                    it(article)
                }
            }
        }

    }
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }
}