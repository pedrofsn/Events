package br.com.pedrofsn.jobs

import br.com.pedrofsn.jobs.data.model.DateCalculator.asDate
import br.com.pedrofsn.jobs.data.model.DateCalculator.countDaysBetween
import java.text.ParseException
import org.junit.Test

class DateCalculatorTest {

    @Test
    fun `GIVEN same dates WHEN count days between THEN result as expected`() {
        // GIVEN
        val input1 = "27/02/2021".asDate()!!
        val input2 = "27/02/2021".asDate()!!

        // WHEN
        val result = countDaysBetween(input1, input2)

        // THEN
        assert(result == 0)
    }

    @Test
    fun `GIVEN today and yesterday WHEN count days between THEN result as expected`() {
        // GIVEN
        val input1 = "27/02/2021".asDate()!!
        val input2 = "26/02/2021".asDate()!!

        // WHEN
        val result = countDaysBetween(input1, input2)

        // THEN
        assert(result == 1)
    }

    @Test(expected = ParseException::class)
    fun `GIVEN wrong date WHEN parse THEN result as null`() {
        "aa/02/2021".asDate()
    }
}