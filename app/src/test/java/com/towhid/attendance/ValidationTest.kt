package com.towhid.attendance


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidationTest {

    @Test
    fun nameUserNameValidationInputIsValid() {
        for (nameOrUsername in PositiveData.nameOrUserList) {
            val result = !Validation.isFieldEmpty(string = nameOrUsername)
            println("$nameOrUsername  ->  $result")
            assertThat(result).isEqualTo(true)
        }
    }

    @Test
    fun nameUserNameValidationInputIsInvalid() {
        for (nameOrUsername in NegativeData.nameOrUserList) {
            val result = !Validation.isFieldEmpty(string = nameOrUsername)
            println("$nameOrUsername  ->  $result")
            assertThat(result).isEqualTo(false)
        }
    }


}