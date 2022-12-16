package me.alfredobejarano.sdefleet.utils

/**
 * Finds the highest common factor of two numbers.
 * @param other - The other number to find the HCF.
 */
fun Int.hcf(other: Int): Int {
    var hcf = 1
    var i = 1

    while (i <= this && i <= other) {
        if (this % i == 0 && other % i == 0) hcf = i
        i++
    }

    return hcf
}