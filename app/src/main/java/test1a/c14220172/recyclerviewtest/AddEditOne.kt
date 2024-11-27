package test1a.c14220172.recyclerviewtest

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.util.Calendar

class AddEditOne : AppCompatActivity() {
    private lateinit var etActivityName: EditText
    private lateinit var etImageUrl: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var tvDeadline: TextView
    private lateinit var btnOk: Button
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit_one)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etActivityName = findViewById(R.id.et_activityName)
        etImageUrl = findViewById(R.id.et_imageUrl)
        etDeskripsi = findViewById(R.id.et_deksripsi)
        tvDeadline = findViewById(R.id.tv_deadline2)
        btnOk = findViewById(R.id.btn_ok)
        image = findViewById(R.id.calendar)

        image.setOnClickListener {
            showDatePicker()
        }

        btnOk.setOnClickListener {
            val activityName = etActivityName.text.toString()
            val imageUrl = etImageUrl.text.toString()
            val deskripsi = etDeskripsi.text.toString()
            val deadline = tvDeadline.text.toString()

            val intent = Intent().apply {
                putExtra("ACTIVITY_NAME", activityName)
                putExtra("IMAGE_URL", imageUrl)
                putExtra("DESKRIPSI", deskripsi)
                putExtra("DEADLINE", deadline)
            }

            setResult(RESULT_OK, intent)
            finish()
        }

        val buttonCancel = findViewById<Button>(R.id.btn_cancel)
        buttonCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "$selectedDay-${selectedMonth + 1}-$selectedYear"
                tvDeadline.text = formattedDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }
}
