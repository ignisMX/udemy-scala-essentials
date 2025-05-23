package basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorialOne(number: Int): Int = {
    if (number < 1) 1
    else {
      println("Computing factorial of " + number + " - I first need factorial of " + (number - 1))
      val result = number * factorialOne(number - 1)
      println("Computed factorial of " + number)
      result
    }
  }

  /*
  Non tail recursive
  factorialOne(3)
  => 3 * factorialOne(2)
  => 3 * (2 * factorialOne(1))
  => 3 * (2 * (1 * factorialOne(0)))
  => 3 * (2 * (1 * 1)) = 6
  */


  def factorialTwo(numberOne: Int): Int = {
    @tailrec
    def factorialHelper(numberTwo: Int, accumulator: Int): Int = {
      if (numberTwo < 1) accumulator
      else factorialHelper(numberTwo - 1, numberTwo * accumulator)
    }

    factorialHelper(numberOne, 1)
  }
  /*
    Tail recursive
    factorialHelper(3, 1)
    => factorialHelper(2, 3 * 1)
    => factorialHelper(1, 2 * 3 * 1)
    => factorialHelper(0, 2 * 3 * 1)
    => return 6
    */


  /**
   * Task
   * 1. Concatenate a string n times
   * 2. isPrime function with tail recursion
   * 3. Fibonacci with tail recursion
   * */

  println("String concatenation")

  def concatenate(text: String, times: Int): String = {

    @tailrec
    def concatenation(textString: String, time: Int, accumulator: String): String = {
      if (time == 0) accumulator
      else concatenation(textString, time - 1, s"$textString  $accumulator")
    }

    concatenation(text, times, "")
  }

  println(concatenate("Hello", 5))

  println("\nis Prime")

  def isPrime(number: Int): Boolean = {
    @tailrec
    def isPrimeUtil(nextNumber: Int, accumulator: Boolean): Boolean = {
      if (nextNumber == 1) accumulator
      else isPrimeUtil(nextNumber - 1, number % nextNumber != 0 && accumulator)
    }

    if (number > 2) isPrimeUtil(number - 1, true) else true
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

  println("\nFibonacci")

  def fibonacci(number: Int): Int = {

    @tailrec
    def fibonacciUtil(lastNumber: Int, accumulator: Int, counter: Int): Int = {
      if (counter == number) accumulator
      else fibonacciUtil(accumulator, lastNumber + accumulator, counter + 1)
    }

    if(number < 2) 1 else fibonacciUtil(1, 1, 2)
  }

  println(s"fibonacci of 1 ${fibonacci(1)}")
  println(s"fibonacci of 2 ${fibonacci(2)}")
  println(s"fibonacci of 3 ${fibonacci(3)}")
  println(s"fibonacci of 4 ${fibonacci(4)}")
  println(s"fibonacci of 5 ${fibonacci(5)}")
  println(s"fibonacci of 6 ${fibonacci(6)}")
  println(s"fibonacci of 7 ${fibonacci(7)}")
  println(s"fibonacci of 8 ${fibonacci(8)}")
  println(s"fibonacci of 9 ${fibonacci(9)}")
}
