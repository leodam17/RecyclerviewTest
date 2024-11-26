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
            // Collect the data from the EditTexts
            val activityName = etActivityName.text.toString()
            val imageUrl = etImageUrl.text.toString()
            val deskripsi = etDeskripsi.text.toString()
            val deadline = tvDeadline.text.toString()

            // Create an Intent to send data back to MainActivity
            val intent = Intent().apply {
                putExtra("ACTIVITY_NAME", activityName)
                putExtra("IMAGE_URL", imageUrl)
                putExtra("DESKRIPSI", deskripsi)
                putExtra("DEADLINE", deadline)
            }

            // Set the result with the data
            setResult(RESULT_OK, intent)
            finish()  // Close the AddEditOne activity and return to MainActivity
        }

        val buttonCancel = findViewById<Button>(R.id.btn_cancel)
        buttonCancel.setOnClickListener {
            // Close the AddEditOne activity without sending any result
            setResult(RESULT_CANCELED)
            finish()
        }
    }
    private fun showDatePicker() {
        // Get current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Show DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Update the TextView with selected date
                val formattedDate = "$selectedDay-${selectedMonth + 1}-$selectedYear"
                tvDeadline.text = formattedDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }
}
