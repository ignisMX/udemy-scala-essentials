package functional

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  // map, flatMap, filter in MyList

  // Function that applies a function n times over a value x
  // nTimes(f,n,x)
  // nTimes(f,3,x) = f(f(f(x))) = nTimes(f,2,f(x)) = f(f(f(x)))
  // nTimes(f,n,x) = f(f(...f(x))) = nTimes(f, n-1, f(x))

  def nTimes(function: Int => Int, times: Int, number: Int): Int =
    if (times <= 0) number
    else nTimes(function, times - 1, function(number))


  val plusOne = (number: Int) => number + 1
  println(nTimes(plusOne, 10, 1))

  // nTimesBetter(f,n) = x => f(f(f(...(x))))
  // increment!0 = nTimesBetter(plusOne,10) = x => plusOne(plusOne...(x))
  // val y = increment10(1)
  def nTimesBetter(function: Int => Int, times: Int): (Int => Int) =
    if (times <= 0) (number: Int) => number
    else (number: Int) => nTimesBetter(function, times - 1)(function(number))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  val superAdder: Int => (Int => Int) = (numberOne: Int) => (numberTwo: Int) => numberOne + numberTwo
  val add3 = superAdder(3) // numberTwo => 3 + numberTwo
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiples parameter lists
  def curriedFormatter(str: String)(double: Double): String = str.format(double)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
  1. Expand MyList
    - foreach method A => Unit
    [1,2,3].foreach(x => println(x))

    - sort function ((A,A) => Int) => MyList
    [1,2,3].sort((x,y) => y - x ) => [3,2,1]

    -zipWith (list, (A,A) => B) => MyList(B)
    [1,2,3].zipWith([4,5,6], x * y) => [1 * 4,2 * 5,3 * 6]=[4,10,18]

    -fold(start)(function) => a value
    [1,2,3].fold(0)(x + y) = 6
  */

  def toCurry(function: (Int, Int) => Int): (Int => Int => Int) =
    x => y => function(x, y)

  def fromCurry(function: (Int => Int => Int)): (Int, Int) => Int = (x, y) => function(x)(y)

  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int = x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)

  def add4 = superAdder2(4)

  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
