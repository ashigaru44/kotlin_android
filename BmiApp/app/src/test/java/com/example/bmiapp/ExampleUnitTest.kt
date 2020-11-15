package com.example.bmiapp

import org.junit.Test

import org.junit.Assert.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun bmi_isCorrect_metric() {
        val units = "METRIC"
        val mass = "80"
        val height = "190"
        val expectedResult = 22.16
        val bmiCalc = BmiCalculate()
        assertEquals(expectedResult, bmiCalc.calculateBMI(units, mass, height), 0.01)
    }

    @Test
    fun bmi_isCorrect_imperial() {
        val units = "IMPERIAL"
        val mass = "150"
        val height = "72"
        val expectedResult = 20.34
        val bmiCalc = BmiCalculate()
        assertEquals(expectedResult, bmiCalc.calculateBMI(units, mass, height), 0.01)
    }

    @Test
    fun bmi_isCorrect_metric_weird() {
        val units = "METRIC"
        val mass = "300"
        val height = "100"
        val expectedResult = 300.0
        val bmiCalc = BmiCalculate()
        assertEquals(expectedResult, bmiCalc.calculateBMI(units, mass, height), 0.01)
    }

    @Test
    fun bmi_incorrect_unit() {
        val units = "INCORRECT_UNIT"
        val mass = "23122"
        val height = "3322"
        val expectedResult = 0.0
        val bmiCalc = BmiCalculate()
        assertEquals(expectedResult, bmiCalc.calculateBMI(units, mass, height), 0.01)
    }

    @Test
    fun bmi_isCorrect_output_type() {
        val units = "IMPERIAL"
        val mass = "150"
        val height = "72"
        val expectedResult = Double::class.simpleName
        val bmiCalc = BmiCalculate()
        assertEquals(expectedResult, bmiCalc.calculateBMI(units, mass, height)::class.simpleName)
    }
}