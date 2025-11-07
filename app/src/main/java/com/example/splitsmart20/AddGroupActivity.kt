package com.example.splitsmart20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AddGroupActivity : AppCompatActivity() {

    private lateinit var groupNameInput : EditText
    private lateinit var checkBoxMeena : CheckBox
    private lateinit var checkBoxNazneen: CheckBox
    private lateinit var checkBoxJulie : CheckBox
    private lateinit var checkBoxJudith : CheckBox
    private lateinit var addGroupButton : AppCompatButton

    private lateinit var groupRecyclerView: RecyclerView
    private  lateinit var groupAdapter: GroupAdapter

    private val groups = mutableListOf<GroupData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_group)

        groupNameInput = findViewById(R.id.groupNameInput)
        checkBoxMeena = findViewById(R.id.checkboxMeena)
        checkBoxNazneen = findViewById(R.id.checkboxNazneen)
        checkBoxJulie = findViewById(R.id.checkboxJulie)
        checkBoxJudith = findViewById(R.id.checkboxJudith)
        addGroupButton = findViewById(R.id.addGroupButton)

        groupRecyclerView = findViewById(R.id.groupRecyclerView)

        // Initialise RecyclerView
        groupAdapter = GroupAdapter(groups)
        groupRecyclerView.layoutManager = LinearLayoutManager(this)
        groupRecyclerView.adapter = groupAdapter

        // Handles Add Group Button
        addGroupButton.setOnClickListener {
            val groupName = groupNameInput.text.toString()

            // Validate inputs
            if(groupName.isEmpty()){
                Toast.makeText(this, "Please enter a group name.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Creating new group member data
            val newGroup = GroupData(
                groupName = groupName,
                isMeenaChecked = checkBoxMeena.isChecked,
                isNazneenChecked = checkBoxNazneen.isChecked,
                isJulieChecked =  checkBoxJulie.isChecked,
                isJudithChecked = checkBoxJudith.isChecked
            )

            // Adding data to the list and updating recyclerView
            groups.add(newGroup)
            groupAdapter.notifyDataSetChanged()

            // Clear input fields
            groupNameInput.text.clear()
            checkBoxMeena.isChecked = false
            checkBoxNazneen.isChecked = false
            checkBoxJulie.isChecked = false
            checkBoxJudith.isChecked = false

            Toast.makeText(this, "Group added!", Toast.LENGTH_SHORT).show()

        }
    }
}


