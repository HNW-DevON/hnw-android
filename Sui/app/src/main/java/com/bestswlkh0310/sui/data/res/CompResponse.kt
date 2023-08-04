package com.bestswlkh0310.sui.data.res

data class CompResponse(
    val id: String,
    val name: String,
    val description: String,
    val goal: String,
    val service: String,
    val address: String,
    val homepage: String
): Comparator<CompResponse> {
    override fun compare(o1: CompResponse?, o2: CompResponse?): Int {
        return o1!!.name.compareTo(o2!!.name)
    }
}
