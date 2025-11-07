package com.example.splitsmart20


import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AddExpenseActivity : AppCompatActivity() {

    private lateinit var expenseNameInput: EditText
    private lateinit var expenseAmountInput: EditText
    private lateinit var checkboxMeena: CheckBox
    private lateinit var checkboxNazneen: CheckBox
    private lateinit var checkboxJulie: CheckBox
    private lateinit var checkboxJudith: CheckBox
    private lateinit var addButton: AppCompatButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var memberAdapter: MemberAdapter

    // List to hold all expenses/members
    private val members = mutableListOf<MemberData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        expenseNameInput = findViewById(R.id.expenseNameInput)
        expenseAmountInput = findViewById(R.id.expenseAmountInput)
        checkboxMeena = findViewById(R.id.checkboxMeena)
        checkboxNazneen = findViewById(R.id.checkboxNazneen)
        checkboxJulie = findViewById(R.id.checkboxJulie)
        checkboxJudith = findViewById(R.id.checkboxJudith)
        addButton = findViewById(R.id.addButton)

        recyclerView = findViewById(R.id.recyclerView)

        // Initialize RecyclerView
        memberAdapter = MemberAdapter(members)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = memberAdapter

        // Handle Add Button click
        addButton.setOnClickListener {
            val name = expenseNameInput.text.toString()
            val amount = expenseAmountInput.text.toString().toDoubleOrNull()

            // Validate inputs
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter an expense name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Creating new member data
            val newMember = MemberData(
                name = name,
                amount = amount,
                isMeenaChecked = checkboxMeena.isChecked,
                isNazneenChecked = checkboxNazneen.isChecked,
                isJulieChecked = checkboxJulie.isChecked,
                isJudithChecked = checkboxJudith.isChecked
            )

            // Adding new data to the list and updating RecyclerView
            members.add(newMember)
            memberAdapter.notifyDataSetChanged()

            // Clear input fields after adding
            expenseNameInput.text.clear()
            expenseAmountInput.text.clear()
            checkboxMeena.isChecked = false
            checkboxNazneen.isChecked = false
            checkboxJulie.isChecked = false
            checkboxJudith.isChecked = false

            // Provide feedback
            Toast.makeText(this, "Expense added!", Toast.LENGTH_SHORT).show()
        }
    }
}


