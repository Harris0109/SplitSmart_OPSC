package com.example.splitsmart20

data class MemberData(
    val name: String,
    val amount: Double,
    val isMeenaChecked: Boolean = false,
    val isNazneenChecked: Boolean = false,
    val isJulieChecked: Boolean = false,
    val isJudithChecked: Boolean = false
) {
    val selectedMembers: List<String>
        get() {
            val members = mutableListOf<String>()
            if (isMeenaChecked) members.add("Meena")
            if (isNazneenChecked) members.add("Nazneen")
            if (isJulieChecked) members.add("Julie")
            if (isJudithChecked) members.add("Judith")
            return members
        }

    val eachShare: Double
        get() = if (selectedMembers.isNotEmpty()) amount / selectedMembers.size else 0.0
}





