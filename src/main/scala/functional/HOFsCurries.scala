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

}
