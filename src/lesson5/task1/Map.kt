@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val phone = mutableMapOf<String, MutableList<String>>()
    val phone1 = mutableMapOf<String, String>()
    for ((a, b) in mapA) {
        if (a !in phone) phone[a] = mutableListOf(b) else
            if ((a in phone) && (b !in phone[a]!!)) phone[a]!!.add(b)
    }
    for ((a, b) in mapB) {
        if (a !in phone) phone[a] = mutableListOf(b) else
            if ((a in phone) && (b !in phone[a]!!)) phone[a]!!.add(b)
    }
    for ((a, b) in phone) {
        phone1[a] = b.joinToString(separator = ", ")
    }
    return phone1.toMap()
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val marks = mutableMapOf<Int, MutableList<String>>()
    for ((surname, m) in grades) {
        if (m !in marks) marks[m] = mutableListOf(surname)
        else {
            marks[m]!!.add(surname)
            marks[m] = marks[m]!!.sortedDescending().toMutableList()
        }
    }
    return marks.toMap()
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    var f = 0
    for ((key, value) in a) {
        if (key in b)
            if (value == b[key]) f++
    }
    return f == a.size
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val stock = mutableMapOf<String, Double>()
    val stockCount = mutableMapOf<String, Int>()
    for ((a, b) in stockPrices) {
        if (a !in stock) {
            stock[a] = b
            stockCount[a] = 1
        } else {
            stock[a] = stock[a]!! + b
            stockCount[a] = stockCount[a]!! + 1
        }
    }
    for ((a, b) in stock) {
        if (a in stockCount) stock[a] = b / stockCount[a]!!
    }
    return stock.toMap()
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    val cost = mutableMapOf<String, Double>()
    var name = ""
    var price = 0.0
    for ((a, b) in stuff) {
        if (b.first == kind) {
            cost[a] = b.second
            price = b.second
            name = a
        }
    }
    if (cost.isEmpty()) return null
    else {
        for ((a, b) in cost) {
            if (b < price) {
                price = cost[a]!!
                name = a
            }
        }
        return name
    }
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    var hands: Set<String>
    val name = mutableMapOf<String, Set<String>>()
    var noFriends = setOf<String>()
    for ((a, b) in friends) {
        noFriends +=a
        if (b.isEmpty()) name[a] = setOf() else {
            hands = b
            for (element in b) {
                noFriends += element
                if (element in friends)
                    hands += friends[element]!!.toSet()
            }
            val list = hands.sorted()
            hands = list.toSet()
            name[a] = (hands - a)
        }
    }
    for (element in noFriends){
        if (element !in name) name[element] = setOf()
    }
    return name.toMap()
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    for ((key, value) in b) {
        if ((key in a) && (a[key] == value)) a.remove(key)
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val people = mutableListOf<String>()
    for (element in a) {
        if (element in b) people.add(element)
    }
    return people
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    val word1 = mutableListOf<Char>()
    for (i in 0 until word.length) {
        if (word[i] !in word1) word1.add(word[i])
    }
    for (element in chars) {
        if (element in word1) word1.remove(element)
    }
    return word1.isEmpty()
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val repeat = mutableMapOf<String, Int>()
    var f = 0
    for (element in list) {
        if (element !in repeat) repeat[element] = 1
        else repeat[element] = repeat[element]!! + 1
    }
    for ((a, b) in repeat) {
        if (b == 1) f++
    }
    if (f == repeat.size) return emptyMap()
    else {
        for ((a, b) in repeat) {
            if (b == 1) repeat.remove(a)
        }
        return repeat.toMap()
    }
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    var f = 0
    var n :List<Char>
    var n1 :List<Char>
    for (i in 0 until words.size - 1) {
        n = words[i].toList().sorted()
        for (j in i + 1 until words.size) {
            n1 = words[j].toList().sorted()
            if (n == n1) f++
        }
        if (f > 0) break
    }
    return f > 0
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    val mass = mutableMapOf<Int, Pair<Int, Int>>()
    for (i in 0 until list.size - 1) {
        for (j in i + 1 until list.size)
            mass[list[i] + list[j]] = i to j
    }
    return if (number in mass) mass[number]!!
    else Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val map = treasures.toMutableMap()
    val inventory = mutableSetOf<String>()
    val mass = capacity
    var value = -1
    var name = ""
    var weight = 0
    while (map.isNotEmpty()) {
        for ((a, b) in map) {
            if (b.second > value) {
                value = b.second
                name = a
                weight = b.first
            }
        }
        if (mass - weight >= 0) inventory.add(name)
        value = -1
        map.remove(name)
    }
    return inventory
}
