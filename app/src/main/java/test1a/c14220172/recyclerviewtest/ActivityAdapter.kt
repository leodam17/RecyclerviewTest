package test1a.c14220172.recyclerviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ActivityAdapter(private val activityList: MutableList<activity>) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    // ViewHolder class to hold the references to the TextViews and ImageView
    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tv_idActivity)
        val tvNama: TextView = itemView.findViewById(R.id.tv_namaActivity)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsiActivity)
        val tvDeadline: TextView = itemView.findViewById(R.id.tv_deadlineActivity)
        val ivGambar: ImageView = itemView.findViewById(R.id.iv_gambarActivity)
        val btnDel : Button = itemView.findViewById(R.id.btn_hapus)
        val btnStart : Button = itemView.findViewById(R.id.btn_Start)
    }

    // Create a new ViewHolder for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ActivityViewHolder(view)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activityList[position]

        // Set the data for each TextView in the ViewHolder
        holder.tvId.text = "Id: ${activity.id}"
        holder.tvNama.text = "Nama: ${activity.nama}"
        holder.tvDeskripsi.text = "Deskripsi: ${activity.deskripsi}"
        holder.tvDeadline.text = "Deadline: ${activity.deadline}"

        Picasso.get()
            .load(activity.imageUrl)  // Assuming `imageUrl` is the image URL in the `activity` object
            .into(holder.ivGambar)

        if (activity.status == "Selesai") {
            holder.btnStart.text = "Selesai" // Change button text to "Selesai"
            holder.btnStart.isEnabled = false // Disable the button if it's finished
        } else {
            holder.btnStart.text = "Start" // Otherwise, keep it as "Start"
            holder.btnStart.isEnabled = true // Make sure the button is enabled
        }

        holder.btnDel.setOnClickListener {
            removeItem(position)  // Call function to remove item from list
        }

        holder.btnStart.setOnClickListener{
            activity.status = "Selesai"
            notifyItemChanged(position)  // Notify RecyclerView to update the item
        }
    }

    // Return the number of items in the list
    override fun getItemCount(): Int = activityList.size

    private fun removeItem(position: Int) {
        activityList.removeAt(position)  // Remove item from the list
        notifyItemRemoved(position)  // Notify RecyclerView to remove the item
        notifyItemRangeChanged(position, activityList.size)  // Notify RecyclerView to update the rest of the items
    }
}
