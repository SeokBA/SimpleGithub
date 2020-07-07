package com.androidhuman.example.simplegithub.ui.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.api.model.GithubRepo
import com.androidhuman.example.simplegithub.ui.GlideApp
import java.util.ArrayList

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.RepositoryHolder>() {

    private var items: MutableList<GithubRepo> = ArrayList()

    private val placeholder = ColorDrawable(Color.GRAY)

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        return RepositoryHolder(parent)
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        // items.get(position) 대신 배열 인덱스 접근 연산자를 사용합니다.
        val repo = items[position]

        GlideApp.with(holder.itemView.context)
                .load(repo.owner.avatarUrl)
                .placeholder(placeholder)
                .into(holder.ivProfile)

        holder.tvName.text = repo.fullName
        holder.tvLanguage.text = if (TextUtils.isEmpty(repo.language))
            holder.itemView.context.getText(R.string.no_language_specified)
        else
            repo.language

        // View.OnClickListener의 본체를 람다 표현식으로 작성합니다.
        holder.itemView.setOnClickListener {
            if (null != listener) {
                listener!!.onItemClick(repo)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: MutableList<GithubRepo>) {
        this.items = items
    }

    fun setItemClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    fun clearItems() {
        this.items.clear()
    }

    // 'internal' 키워드를 제거하여 가시성을 'public'으로 변경합니다.
    class RepositoryHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_repository, parent, false)) {

        // init 블록에서 프로퍼티의 값을 설정해 주고 있으므로
        // 여기에서 값을 할당하지 않아도 컴파일 에러가 발생하지 않습니다.
        var ivProfile: ImageView

        var tvName: TextView

        var tvLanguage: TextView

        init {
            // 클래스 생성자 호출 시 클래스 내 프로퍼티의 값을 할당합니다.
            ivProfile = itemView.findViewById(R.id.ivItemRepositoryProfile)
            tvName = itemView.findViewById(R.id.tvItemRepositoryName)
            tvLanguage = itemView.findViewById(R.id.tvItemRepositoryLanguage)
        }
    }

    interface ItemClickListener {

        fun onItemClick(repository: GithubRepo)
    }
}
