package test1a.c14220172.recyclerviewtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val deadline = findViewById<EditText>(R.id.et_deadline)
        val save = findViewById<Button>(R.id.btn_save)


        val dataIntent = intent.getParcelableExtra("ACTIVITY_DATA", activity::class.java)
        if (dataIntent != null){
            name.setText(dataIntent.nama)
            image.setText(dataIntent.imageUrl)
            deskripsi.setText(dataIntent.deskripsi)
            deadline.setText(dataIntent.deadline)
        }

        save.setOnClickListener{
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