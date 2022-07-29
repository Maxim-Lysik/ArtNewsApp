package com.example.artnewsapplicationtorelease.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.models.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article_preview.view.*


class UsersDiffCallBack(
    private val oldList: List<Article>,
    private val newList: List<Article>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.title == newUser.title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }

}


class NewsAdapter(ctx: Context) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // SHARED PREFERENCES

  val dick = ctx




   /* val sharedPreference =  dick.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)         // IT WORKED
    var editor = sharedPreference.edit().putInt("Ass", 3).commit()
*/



    //var editor = sharedPreference.edit().putLong("l",100L).commit()


    /*editor.putString("username","Anupam")
    editor.putLong("l",100L)
    editor.commit()

*/



    ///



    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d(TAG, "CHECKING AAAAAAAAAAAAAAAA")


            Log.d(
                TAG,
                "PIZDETS FROM ${oldItem.title} AND ${newItem.title} EQUALS ${oldItem.title == newItem.title}"
            )
            return oldItem.title.toString() == newItem.title.toString()


        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d(
                TAG,
                "CHECKING ${oldItem.title} AND ${newItem.title} IS: ${oldItem.title == newItem.title}"
            )
            return oldItem == newItem

        }
    }
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {


            /* try{
             if(article.media == null){Glide.with(this).load("https://i.ibb.co/XYNfnCR/imgonline-com-ua-dexif-J11-TBi-KNXOTO.jpg").into(ivArticleImage)}
             else{Glide.with(this).load(article.media).dontAnimate().into(ivArticleImage)
                 //.onLoadFailed(ContextCompat.getDrawable(context, R.drawable.werrr))

             }}catch(e:GlideException){
                 return
             }
 */

            // TESTING


            if (article.media == null) {
                Picasso.get()
                    .load("https://i.ibb.co/XYNfnCR/imgonline-com-ua-dexif-J11-TBi-KNXOTO.jpg")
                    .error(R.drawable.werrr)
                    .fit()
                    .into(ivArticleImage)
            } else {
                Picasso.get()
                    .load(article.media)
                    .error(R.drawable.werrr)
                    .fit()
                    .centerCrop()
                    .into(ivArticleImage)
            }


/*try{
            if(article.media == null){Glide.with(this).load("https://i.ibb.co/XYNfnCR/imgonline-com-ua-dexif-J11-TBi-KNXOTO.jpg").into(ivArticleImage)}
            else{Glide.with(this).load(article.media).into(ivArticleImage).
                onLoadFailed(ContextCompat.getDrawable(context, R.drawable.werrr))

            }} catch (e: GlideException){Glide.with(this).load("https://i.ibb.co/XYNfnCR/imgonline-com-ua-dexif-J11-TBi-KNXOTO.jpg").into(ivArticleImage)}

*/
            //Glide.with(this).load("https://i.ibb.co/XYNfnCR/imgonline-com-ua-dexif-J11-TBi-KNXOTO.jpg").into(ivArticleImage)


            //tvSource.text = article.author
           tvTitle.text = article.title
            tvDescription.text = article.summary
           // tvPublishedAt.text = article.published_date
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null



    val counter = 0


    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener


       /* val sharedPreference =  dick.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)         // IT WORKED
        var editor = sharedPreference.edit().putInt("Ass", counter+1).commit()
*/


    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}


