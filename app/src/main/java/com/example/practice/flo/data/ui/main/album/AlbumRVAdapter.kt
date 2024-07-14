package com.example.practice.flo.data.ui.main.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.flo.data.entities.Album
import com.example.practice.flo.databinding.ItemAlbumBinding


class AlbumRVAdapter(private val albumList: ArrayList<Album>): RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>(){

    interface MyItemClickListener{
        fun onItemClick(album: Album)
   //     fun onRemoveAlbum(position: Int)

    }

    interface MyPlayClickListener {
        fun onPlayAlbum(album : Album)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener =itemClickListener
    }

    private lateinit var mItemPlayClickListener: MyPlayClickListener
    fun setPlayClickListener(itemPlayClickListener: MyPlayClickListener){
        mItemPlayClickListener =itemPlayClickListener
    }

    fun addItem(album: Album){
        albumList.add(album)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        albumList.removeAt(position)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //뷰 홀더를 생성해줘야할때 생성 아이템뷰를 재활용하기 위해 해당 작업을 함
        val binding: ItemAlbumBinding = ItemAlbumBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       //데이터를 바인딩 해주는 작업
        //position은 인덱스 id
        holder.bind(albumList[position])
        holder.itemView.setOnClickListener{ mItemClickListener.onItemClick(albumList[position]) }
        holder.binding.itemAlbumMiniStart.setOnClickListener {
            mItemPlayClickListener.onPlayAlbum(albumList[position])
        }

      //  holder.binding.itemAlbumTitleTv.setOnClickListener{mItemClickListener.onRemoveAlbum(position)}
    }

    override fun getItemCount(): Int =albumList.size

    inner class ViewHolder(val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(album: Album){
            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text=album.singer
            binding.itemAlbumCoverImgIv.setImageResource(album.coverImg!!)
        }
    }

}