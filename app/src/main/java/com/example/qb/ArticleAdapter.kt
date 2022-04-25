package com.example.qb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prof.rssparser.Article
import com.squareup.picasso.Picasso

class ArticleAdapter(private var articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) =
        holder.bind(articles[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.text_view_title)
        private val pubDate = itemView.findViewById<TextView>(R.id.text_view_publication_date)
        private val image = itemView.findViewById<ImageView>(R.id.image_view)

        fun bind(article: Article) {
            title.text = article.title

            Picasso.get()
                .load(article.image)
                .into(image)

            pubDate.text = article.pubDate
        }
    }
}