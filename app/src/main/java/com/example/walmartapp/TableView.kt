package com.example.walmartapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.ComponentActivity

class TableView : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_view)

        val tableLayout = findViewById<TableLayout>(R.id.table_layout)
        val addBtn = findViewById<Button>(R.id.add_btn)
        val versionEditText = findViewById<EditText>(R.id.version_input)
        val codeNameEditText = findViewById<EditText>(R.id.codename_input)

        addBtn.setOnClickListener {
            val tableRow = TableRow(this)
            val layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
            tableRow.layoutParams = layoutParams

            val versionView = TextView(this)
            versionView.text = versionEditText.text.toString()

            val codeNameView = TextView(this)
            codeNameView.text = codeNameEditText.text.toString()

            tableRow.addView(versionView, 0)
            tableRow.addView(codeNameView, 1)

            tableLayout.addView(tableRow)

            // Clear input fields after adding the row
            versionEditText.text.clear()
            codeNameEditText.text.clear()
        }
    }
}
