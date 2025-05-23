package basics

object Functions extends App {

  def aFunctionOne(text: String, number: Int): String = text + " " + number

  println(aFunctionOne("Hello", 3))

  def aParameterlessFunctionOne(): Int = 42

  def aParameterlessFunctionTwo: Int = 35

  println(aParameterlessFunctionOne())
  println(aParameterlessFunctionTwo)

  def aFunctionTwo(text: String, number: Int): String = {
    if (number == 1) text
    else text + " " + aFunctionTwo(text, number - 1)
  }

  println(aFunctionTwo("Hello", 3))

  // When you need loops in scala, use recursion

  def aFunctionThree(text: String): Unit = println(text)

  def aFunctionFour(numberOne: Int): Int = {
    def aFunctionFive(numberTwo: Int, numberThree: Int): Int = numberTwo + numberThree

    aFunctionFive(numberOne, numberOne - 1)
  }

  println(aFunctionFour(5))

  /**
   * Task
   * 1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
   * 2. Factorial function (n) => n * (n - 1)
   * 3. Fibonacci sequence
   * 4. Test if a number is prime
   * */

  println("Greeting")

  def greeting(name: String, age: Int): String = s"Hi, my name is $name and I am $age years old"

  println(greeting("John", 30))

  println("Factorial")

  def factorial(number: Int): Int = {
    if (number == 1) number
    else number * factorial(number - 1)
  }

  println(factorial(5))
  println(factorial(10))

  println("Fibonacci")

  def fibonacci(number: Int): Int = {
    if (number <= 2) 1
    else fibonacci(number - 1) + fibonacci(number - 2)
  }

  println(fibonacci(5))
  println(fibonacci(8))

  println("Prime")

  def isPrime(number: Int): Boolean = {

    def isPrimeUntil(nextNumber: Int): Boolean = {
      if (nextNumber == 1) true
      else number % nextNumber != 0 && isPrimeUntil(nextNumber - 1)
    }

    if (number > 2) isPrimeUntil(number - 1) else true
  }

  println(s"1 is prime ${isPrime(1)}")
  println(s"2 is prime ${isPrime(2)}")
  println(s"3 is prime ${isPrime(3)}")
  println(s"4 is prime ${isPrime(4)}")
  println(s"5 is prime ${isPrime(5)}")
  println(s"6 is prime ${isPrime(6)}")
  println(s"7 is prime ${isPrime(7)}")
  println(s"8 is prime ${isPrime(8)}")
  println(s"9 is prime ${isPrime(9)}")
  println(s"10 is prime ${isPrime(10)}")
  println(s"11 is prime ${isPrime(11)}")
  println(s"12 is prime ${isPrime(12)}")
  println(s"37 is prime ${isPrime(37)}")
  println(s"2003 is prime ${isPrime(2003)}")
  println(s"n is prime ${isPrime(37 * 17)}")
}
