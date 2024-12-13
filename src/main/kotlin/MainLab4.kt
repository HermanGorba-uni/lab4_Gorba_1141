import kotlin.math.abs
import kotlin.math.cbrt
import kotlin.math.exp
import kotlin.math.pow

fun main() {
    // Варіант 2
    // Завдання 1 див. файл "Розрахунки для n = 2.png"
    var2z2()
    var2z3()
}

fun var2z2() {
    val (a,b,h) = readInputData()
    tabulation(a,b,h)
}

fun readInputData(): Triple<Double, Double, Double> {
    print("Введіть значення a: ")
    val a = readln().toDouble()
    print("Введіть значення b: ")
    val b = readln().toDouble()
    print("Введіть значення h: ")
    val h = readln().toDouble()
    return Triple(a,b,h)
}

fun tabulation(a: Double, b:Double, h:Double) {
    if (h <= 0 || a > b) {
        println("Некоректні значення: переконайтеся, що h > 0 і a <= b")
        return
    }

    println("Таблиця значень функції f(x) = cbrt(x):")
    println("x\t\tf(x)")
    var x = a
    while (x <= b) {
        val y = cbrt(x)
        println(String.format("%.2f\t%.2f", x, y))
        x += h
    }
}



fun var2z3() {
    try {
        print("Введіть значення x: ")
        val x = readln().toDouble()
        print("Введіть кількість доданків (n): ")
        val n = readln().toInt()
        print("Введіть значення e (точність): ")
        val e1 = readln().toDouble()

        val sumN = f(x, n)
        println("Сума $n доданків: $sumN")

        val (sumE1, countE1) = f(x, n, e1)
        println("Сума доданків для e=$e1: $sumE1, кількість доданків: $countE1")

        val e2 = e1 / 10
        val (sumE2, countE2) = f(x, n, e2)
        println("Сума доданків для e=$e2: $sumE2, кількість доданків: $countE2")

        val exactValue = f(x)
        println("Точне значення e^(-x^2): $exactValue")

        println("Похибка для n доданків: ${abs(exactValue - sumN)}")
        println("Похибка для e=$e1: ${abs(exactValue - sumE1)}")
        println("Похибка для e=$e2: ${abs(exactValue - sumE2)}")
    }
    catch (e: Exception) {
        println(e.message)
    }
}

fun f(x:Double):Double {
    return exp(-x.pow(2))
}

fun f(x: Double, n: Int): Double {
    var sum = 0.0

    for (i in 0 until n) {
        val term = (-1.0).pow(i) * x.pow(2 * i) / factorial(i)
        sum += term
    }

    return sum
}

fun f(x: Double, n: Int, e: Double): Pair<Double, Int> {
    var sum = 0.0
    var count = 0

    for (i in 0 until n) {
        val term = (-1.0).pow(i) * x.pow(2 * i) / factorial(i)

        if (abs(term) > e) {
            sum += term
            count++
        }
    }

    return Pair(sum, count)
}

fun factorial(n: Int): Double {
    return if (n == 0 || n == 1) 1.0 else (2..n).fold(1.0) { res, i -> res * i }
}
