package test1a.c14220172.recyclerviewtest

import android.app.DatePickerDialog
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class editActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<EditText>(R.id.et_activityName)
        val image = findViewById<EditText>(R.id.et_imageUrl)
        val deskripsi = findViewById<EditText>(R.id.et_deksripsi)
        val deadline = findViewById<TextView>(R.id.tv_deadline)
        val save = findViewById<Button>(R.id.btn_save)
        val imageCalendar = findViewById<ImageView>(R.id.calendar)

        // Ambil data intent untuk diisi ke EditText
        val dataIntent = intent.getParcelableExtra("ACTIVITY_DATA", activity::class.java)
        if (dataIntent != null) {
            name.setText(dataIntent.nama)
            image.setText(dataIntent.imageUrl)
            deskripsi.setText(dataIntent.deskripsi)
            deadline.setText(dataIntent.deadline)
        }

        // Tambahkan DatePickerDialog pada klik EditText deadline
        imageCalendar.setOnClickListener {
            val calendar = Calendar.getInstance()

            // Jika sudah ada nilai sebelumnya, gunakan nilai tersebut
            if (dataIntent != null && dataIntent.deadline.isNotEmpty()) {
                val dateParts = dataIntent.deadline.split("-")
                if (dateParts.size == 3) {
                    calendar.set(
                        dateParts[0].toInt(),
                        dateParts[1].toInt() - 1, // Bulan di Calendar dimulai dari 0
                        dateParts[2].toInt()
                    )
                }
            }

            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                    deadline.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Tombol save untuk mengembalikan hasil
        save.setOnClickListener {
            val updateActivity = activity(
                id = dataIntent?.id ?: 0,
                nama = name.text.toString(),
                imageUrl = image.text.toString(),
                deadline = deadline.text.toString(),
                deskripsi = deskripsi.text.toString(),
                status = dataIntent?.status ?: "belum selesai"
            )
            val intent = Intent().apply {
                putExtra("UPDATED_ACTIVITY", updateActivity)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
