package com.example.splitsmart20

data class GroupData(
    val groupName: String,
    val isMeenaChecked: Boolean = false,
    val isNazneenChecked: Boolean = false,
    val isJulieChecked: Boolean = false,
    val isJudithChecked: Boolean = false
){
    val selectedGroups: List<String>
        get(){
            val groups = mutableListOf<String>()
            if(isMeenaChecked) groups.add("Meena")
            if(isNazneenChecked) groups.add("Nazneen")
            if(isJulieChecked) groups.add("Julie")
            if(isJudithChecked) groups.add("Judith")
            return groups
        }
}
