@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import lesson3.task1.revert
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (element in v) {
        sum += sqr(element)
    }
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum = 0.0
    for (element in list) {
        sum += element
    }
    return if (list.size == 0) 0.0 else sum / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var average = 0.0
    for (element in list) {
        average += element
    }
    if (list.size != 0) average /= list.size
    for (i in 0 until list.size) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var c = 0.0
    for (i in 0 until a.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var poly = 0.0
    for (i in 0 until p.size) {
        poly += p[i] * x.pow(i)
    }
    return poly
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var adden = 0.0
    for (i in 0 until list.size) {
        adden += list[i]
        list[i] = adden
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val factor = mutableListOf<Int>()
    var n1 = n
    if (isPrime(n)) factor.add(n)
    else for (i in 2..n / 2) {
        if (isPrime(i)) while (n1 % i == 0) {
            factor.add(i)
            n1 /= i
        }
        if (n1 == 1) break
    }
    return factor
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val factor = factorize(n)
    return factor.joinToString(separator = "*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var n1 = n
    val list = mutableListOf<Int>()
    val number = mutableListOf<Int>()
    while (n1 > 0) {
        number.add(n1 % base)
        n1 /= base
    }
    for (i in number.size - 1 downTo 0) {
        list.add(number[i])
    }
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val string = "abcdefghijklmnopqrstuvwxyz"
    val count = "0123456789"
    var n1 = n
    var ch: Char
    var list = ""
    val number = mutableListOf<Int>()
    while (n1 > 0) {
        number.add(n1 % base)
        n1 /= base
    }

    for (i in number.size - 1 downTo 0) {
        if (number[i] > 9) ch = string[number[i] - 10]
        else ch = count[number[i]]
        list += ch
    }
    return list
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var power = digits.size - 1
    var digit = 0.0
    for (element in digits) {
        digit += element * base.toDouble().pow(power)
        power -= 1
    }
    return digit.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val string = "abcdefghijklmnopqrstuvwxyz"
    val count = "0123456789"
    val digit = mutableListOf<Int>()
    var symbol: Char
    for (i in 0 until str.length) {
        symbol = str[i]
        if (symbol in string) digit.add(string.indexOf(symbol, 0) + 10)
        else if (symbol in count) digit.add(count.indexOf(symbol, 0))
    }
    digit.toList()
    return decimal(digit, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var n1 = n
    val digit = "MDCLXVI"
    var number = ""
    var c = 0
    var count: Int
    var tens = 1000
    while (n1 > 0) {
        count = n1 / tens
        if (c % 2 == 0) {
            if (count > 0) {
                for (i in 1..count) {
                    number += digit[c]
                }
                n1 %= tens
            }
            if ((n1 > 1) && (n1 / (tens / 10) == 9)) {
                number += digit[c + 2]
                number += digit[c]
                n1 %= (tens - tens / 10)
            }
        } else {
            if (count == 1) {
                number += digit[c]
                n1 %= tens
            }
            if (n1 / (tens * 2 / 10) == 4) {
                number += digit[c + 1]
                number += digit[c]
                n1 %= (tens - tens / 5)
            }
        }
        if (c % 2 == 0) tens /= 2
        else tens /= 5
        c++
    }
    return number
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var word = ""
    var n1 = 1000000
    val list = mutableListOf<Int>()
    n1 += n
    n1 = n1 * 10 + 1
    n1 = revert(n1)
    n1 /= 10
    while (n1 != 1) {
        list.add(n1 % 10)
        n1 /= 10
    }
    for (i in 0..5) {
        if (i == 0 || i == 3) {
            word += when (list[i]) {
                1 -> "сто "
                2 -> "двести "
                3 -> "триста "
                4 -> "четыреста "
                5 -> "пятьсот "
                6 -> "шестьсот "
                7 -> "семьсот "
                8 -> "восемьсот "
                9 -> "девятьсот "
                else -> ""
            }
        }
        if (i == 1 || i == 4) {
            word += when (list[i]) {
                1 -> {
                    when (list[i + 1]) {
                        0 -> "десять "
                        1 -> "одиннадцать "
                        2 -> "двенадцать "
                        3 -> "тринадцать "
                        4 -> "четырнадцать "
                        5 -> "пятнадцать "
                        6 -> "шетнадцать "
                        7 -> "семнадцать "
                        8 -> "восемнадцать "
                        9 -> "девятнадцать "
                        else -> ""
                    }
                }
                2 -> "двадцать "
                3 -> "тридцать "
                4 -> "сорок "
                5 -> "пятьдесят "
                6 -> "шестьдесят "
                7 -> "семьдесят "
                8 -> "восемьдесят "
                9 -> "девяносто "
                else -> ""
            }
        }
        if ((i == 2 || i == 5) && (list[i - 1] != 1)) {
            word += when (list[i]) {
                1 -> if (i == 2) "одна " else "один "
                2 -> if (i == 2) "две " else "два "
                3 -> "три "
                4 -> "четыре "
                5 -> "пять "
                6 -> "шесть "
                7 -> "семь "
                8 -> "восемь "
                9 -> "девять "
                else -> ""
            }
        }
        if ((i == 2) && (list[0] != 0 || list[1] != 0 || list[2] != 0)) {
            word += when (list[i]) {
                1 -> if (list[1] != 1) "тысяча " else "тысяч "
                2, 3, 4 -> "тысячи "
                else -> "тысяч "
            }
        }
    }
    return word.trim()
}