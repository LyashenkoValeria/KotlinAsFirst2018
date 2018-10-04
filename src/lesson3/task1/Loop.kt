@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = abs(n)
    do {
        count++
        number /= 10
    } while (number > 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 1
    var fib = 1
    if (n > 2) for (i in 3..n) {
        fib = fib1 + fib2
        fib2 = fib1
        fib1 = fib
    }
    return (fib)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var n1 = n
    var m1 = m
    while (n1 != m1) {
        if (n1 > m1) n1 = max(n1, m1) - min(n1, m1)
        else m1 = max(n1, m1) - min(n1, m1)
    }
    return (n1 * n / n1 * m / n1)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var del = 0
    for (i in 2..n) {
        del = i
        if (n % i == 0) break
    }
    return del
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var del = 0
    for (i in (n - 1) downTo 1) {
        del = i
        if (n % i == 0) break
    }
    return del
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var n1 = n
    var m1 = m
    while (n1 != m1) {
        if (n1 > m1) n1 = max(n1, m1) - min(n1, m1)
        else m1 = max(n1, m1) - min(n1, m1)
    }
    return n1 == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var count = 0
    for (i in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt()) {
        if (sqr(i) in m..n) {
            count++
            break
        }
    }
    return count > 0
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var x1 = x
    var count = 0
    while (x1 != 1) {
        count++
        if (x1 % 2 == 0) x1 /= 2
        else x1 = x1 * 3 + 1
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var sin = 0.0
    var x1 = x
    var n = 1
    if (x > 2 * PI) x1 = x - ((x / (2 * PI)) * 2 * PI)
    else if (x < -2 * PI) x1 = x + ((x / (2 * PI)) * 2 * PI)
    while (abs(x1) >= eps) {
        sin += x1
        x1 = (-1.0).pow(n) * x.pow(2 * n + 1) / factorial(2 * n + 1)
        n++
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var cos = 0.0
    var x1 = 1.0
    var n = 1
    var x2 = x
    if (x > 2 * PI) x2 = x - ((x / (2 * PI)) * 2 * PI)
    else if (x < -2 * PI) x2 = x + ((x / (2 * PI)) * 2 * PI)
    while (abs(x1) >= eps) {
        cos += x1
        x1 = (-1.0).pow(n) * x2.pow(2 * n) / factorial(2 * n)
        n++
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var m = 0
    var n1 = n
    while (n1 > 0) {
        m = m * 10 + n1 % 10
        n1 /= 10
    }
    return m
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var m = 0
    var n1 = n
    while (n1 > 0) {
        m = m * 10 + n1 % 10
        n1 /= 10
    }
    n1 = n
    while (m > 0) {
        if (m % 10 != n1 % 10) break
        m /= 10
        n1 /= 10
    }
    return (m == 0)
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n1 = n
    var count = 0
    val digit = n % 10
    while (n1 > 0) {
        if (n1 % 10 != digit) {
            count = 1
            break
        }
        n1 /= 10
    }
    return count == 1
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = 0
    var i = 0
    var square: Int
    while (count < n) {
        i++
        square = sqr(i)
        while (square > 0) {
            count++
            square /= 10
        }
    }
    square = sqr(i)
    return if (count - n == 0) (square % 10) else {
        for (j in 1..(count - n)) square /= 10
        square % 10
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var count = 2
    var fib1 = 1
    var fib2 = 1
    var fib = 1
    var remember = fib
    while (count < n) {
        fib = fib1 + fib2
        fib2 = fib1
        fib1 = fib
        remember = fib
        while (fib > 0) {
            count++
            fib /= 10
        }
    }
    fib = remember
    return if (n < 3) 1 else
        if (count - n == 0) {
            (fib % 10)
        } else {
            for (j in 1..(count - n)) fib /= 10
            fib % 10
        }
}
