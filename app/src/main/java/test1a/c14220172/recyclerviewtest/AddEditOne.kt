package test1a.c14220172.recyclerviewtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddEditOne : AppCompatActivity() {
    private lateinit var etActivityName: EditText
    private lateinit var etImageUrl: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var etDeadline: EditText
    private lateinit var btnOk: Button

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
        etDeadline = findViewById(R.id.et_deadline)
        btnOk = findViewById(R.id.btn_ok)

        btnOk.setOnClickListener {
            // Collect the data from the EditTexts
            val activityName = etActivityName.text.toString()
            val imageUrl = etImageUrl.text.toString()
            val deskripsi = etDeskripsi.text.toString()
            val deadline = etDeadline.text.toString()

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
}
