package com.example.practice.flo.data.ui.main.locker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.flo.data.entities.Song
import com.example.practice.flo.databinding.ItemSongBinding

class SavedsongRVAdapter() :
    RecyclerView.Adapter<SavedsongRVAdapter.ViewHolder>() {
    private val songs = ArrayList<Song>()
    interface MyItemClickListener{
        fun onRemoveSong(songId: Int)
    }
    private lateinit var mItemClickListener : MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSongBinding = ItemSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songs[position])
        holder.binding.itemSongMenuBtn.setOnClickListener {
            mItemClickListener.onRemoveSong(songs[position].id)
            removeSong(position)
        }
    }

    override fun getItemCount(): Int = songs.size

    @SuppressLint("NotifyDataSetChanged")
    fun addSongs(songs: ArrayList<Song>) {
        this.songs.clear()
        this.songs.addAll(songs)

        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun removeSong(position: Int){
        songs.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(song: Song){
            binding.itemSongTitle.text = song.title
            binding.itemSongSinger.text= song.singer
            binding.itemSongCoverImgIv.setImageResource(song.coverImg!!)
        }
    }
}