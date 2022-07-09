package au.com.vaishali.practice.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.vaishali.practice.R
import au.com.vaishali.practice.application.base.BindingViewHolder
import au.com.vaishali.practice.databinding.FlickerPhotoItemLayoutBinding
import au.com.vaishali.practice.presentation.model.FlickerPhoto
import com.bumptech.glide.Glide
import javax.inject.Inject

class FlickerPhotoAdapter @Inject constructor(private val context: Context) :
    RecyclerView.Adapter<BindingViewHolder<FlickerPhotoItemLayoutBinding>>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var listOfPhotos: List<FlickerPhoto> = emptyList()
    private var onFlickerPhotoClickListener: OnFlickerPhotoClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<FlickerPhotoItemLayoutBinding> =
        BindingViewHolder(FlickerPhotoItemLayoutBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(
        holder: BindingViewHolder<FlickerPhotoItemLayoutBinding>,
        position: Int
    ) {
        val photoItem = listOfPhotos[position]

        with(holder.binding) {
            Glide.with(flickerPhotoImage.context).load(photoItem.imageUrl)
                .placeholder(R.drawable.ic_flicker_place_holder).into(flickerPhotoImage)

            photoTitle.text = photoItem.title

            flickerPhotoImage.setOnClickListener {
                onFlickerPhotoClickListener?.let { it.onPhotoClickListener(photoItem) }
            }
        }
    }

    override fun getItemCount(): Int = listOfPhotos.size

    fun setFlickerPhotoClickListener(onFlickerPhotoClickListener: OnFlickerPhotoClickListener) {
        this.onFlickerPhotoClickListener = onFlickerPhotoClickListener
    }

    fun updateFlickerList(listOfPhotos: List<FlickerPhoto>) {
        this.listOfPhotos = listOfPhotos
        notifyItemRangeChanged(0, listOfPhotos.size)
    }


    interface OnFlickerPhotoClickListener {
        fun onPhotoClickListener(photo: FlickerPhoto)
    }

}