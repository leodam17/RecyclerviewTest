package test1a.c14220172.recyclerviewtest

import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ActivityAdapter(
    private val activityList: MutableList<activity>,
    private val editActivityLauncher: ActivityResultLauncher<Intent>,
    private val sharedPreferences: SharedPreferences // Add SharedPreferences here
) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tv_idActivity)
        val tvNama: TextView = itemView.findViewById(R.id.tv_namaActivity)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsiActivity)
        val tvDeadline: TextView = itemView.findViewById(R.id.tv_deadlineActivity)
        val ivGambar: ImageView = itemView.findViewById(R.id.iv_gambarActivity)
        val btnDel : Button = itemView.findViewById(R.id.btn_hapus)
        val btnStart : Button = itemView.findViewById(R.id.btn_Start)
        val btnEdit : Button = itemView.findViewById(R.id.btn_edit)
        val btnSimpan : Button = itemView.findViewById(R.id.btn_simpan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activityList[position]

        holder.tvId.text = "Id: ${activity.id}"
        holder.tvNama.text = "Nama: ${activity.nama}"
        holder.tvDeskripsi.text = "Deskripsi: ${activity.deskripsi}"
        holder.tvDeadline.text = "Deadline: ${activity.deadline}"

        Picasso.get()
            .load(activity.imageUrl)
            .into(holder.ivGambar)

        if (activity.status == "Selesai") {
            holder.btnStart.text = "Selesai"
            holder.btnStart.isEnabled = false
        } else {
            holder.btnStart.text = "Start"
            holder.btnStart.isEnabled = true
        }

        holder.btnDel.setOnClickListener {
            removeItem(position)
        }

        holder.btnStart.setOnClickListener{
            activity.status = "Selesai"
            notifyItemChanged(position)
        }

        holder.btnSimpan.setOnClickListener {
            saveActivityToSharedPreferences(activity)
        }

        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, editActivity::class.java).apply {
                putExtra("ACTIVITY_DATA", activity)
            }
            editActivityLauncher.launch(intent)
        }
    }

    override fun getItemCount(): Int = activityList.size

    private fun removeItem(position: Int) {
        activityList.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun saveActivityToSharedPreferences(activity: activity) {
        // Ambil data yang sudah ada, atau buat array kosong jika belum ada data
        val json = sharedPreferences.getString("SAVED_ACTIVITIES", "[]") ?: "[]"

        // Mengonversi JSON ke dalam List activity
        val savedActivities = Gson().fromJson(json, ArrayList::class.java) as ArrayList<activity>

        // Cek apakah activity sudah ada di list berdasarkan ID-nya
        if (!savedActivities.any { it.id == activity.id }) {
            savedActivities.add(activity) // Menambahkan activity ke dalam list jika belum ada
        }

        // Mengonversi list kembali ke JSON dan menyimpannya ke SharedPreferences
        val updatedJson = Gson().toJson(savedActivities)
        sharedPreferences.edit().putString("SAVED_ACTIVITIES", updatedJson).apply()
    }

}
