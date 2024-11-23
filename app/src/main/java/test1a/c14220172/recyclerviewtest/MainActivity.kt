package test1a.c14220172.recyclerviewtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var activityList: MutableList<activity> // Use MutableList for adding new items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.lv_activityList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the list properly
        activityList = mutableListOf(
            activity(1, "Projek PABA", "https://i.pinimg.com/736x/85/1e/6f/851e6f9ff3fe37ce8c9299a15cd023f1.jpg", "Membuat Proposal", "28-November-2024", "Belum"),
            activity(2, "Projek WFD", "https://pbs.twimg.com/media/CXgPQwzWEAE_5w-.jpg", "Membuat Event Organizer Website", "30-November-2024", "Belum")
        )

        // Set the adapter
        recyclerView.adapter = ActivityAdapter(activityList)

        val buttonAdd = findViewById<Button>(R.id.btn_add)
        buttonAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditOne::class.java)
            startActivityForResult(intent, 1) // Key change here
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Extract the data from the Intent
            val activityName = data.getStringExtra("ACTIVITY_NAME")
            val imageUrl = data.getStringExtra("IMAGE_URL")
            val deskripsi = data.getStringExtra("DESKRIPSI")
            val deadline = data.getStringExtra("DEADLINE")
            val status = data.getStringExtra("STATUS")

            // Add the new activity to the list
            val newActivity = activity(
                activityList.size + 1, // This can be replaced with proper unique ID logic
                activityName ?: "No Name",
                imageUrl ?: "",
                deskripsi ?: "No Description",
                deadline ?: "No Deadline",
                status?: "Belum"
            )
            activityList.add(newActivity)

            // Notify the adapter that the data has changed
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}

