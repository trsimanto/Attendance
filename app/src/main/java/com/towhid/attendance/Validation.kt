package com.towhid.attendance

object Validation {

    /**
     * @positive_case
     * string can't be empty
     */
    fun isFieldEmpty(string: String): Boolean {
        return string.trim().isEmpty()
    }
}