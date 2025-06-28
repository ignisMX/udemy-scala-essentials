package patternmatching

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val nextInt = random.nextInt(10)

  val description = nextInt match {
    case 1 => "the ONE"
    case 2 => "Double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  }

  println(nextInt)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(personName, personAge) if personAge < 21 => s"Hi, my name is $personName and I can't drink in the US"
    case Person(personName, personAge) => s"Hi, my name is $personName and I am $personAge years old"
    case _ => "I don't know who I am"
  }

  println(greeting)

  /*
  1. cases are matched in order
  2. what if no cases match? MatchError
  3. type of the pattern matching expression? unified type of all the types in all the cases
  4. Pattern matching really well with case classes?
   */

  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")

  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  // match everything
  val isEven = nextInt match {
    case number if number % 2 == 0 => true
    case _ => false
  }

  //WHY?
  val isEvenConditional = if (nextInt % 2 == 0) true else false
  val isEvenNormal = nextInt % 2 == 0

  /*
  Exercise
  simple function uses PM
  takes an Expr => human-readable form

  Sum(Number(2), Number(3)) = 2 + 3
  Sum(Number(2), Number(3), Number(4)) = 2 + 3 + 4
  Prod(Sum(Number(2), Number(1), Number(4))) = (2 + 3) * 4
  Sum(Prod(Number(2),Number(1)), Number(3)) = 2 * 1 +3
   */

  trait Expression

  case class Number(number: Int) extends Expression

  case class Sum(elementOne: Expression, elementTwo: Expression) extends Expression

  case class Prod(elementOne: Expression, elementTwo: Expression) extends Expression

  def show(expression: Expression): String = expression match {
    case Number(number) => s"$number"
    case Sum(elementOne, elementTwo) => s"${show(elementOne)} + ${show(elementTwo)}"
    case Prod(elementOne, elementTwo) => {
      def maybeShowParentheses(expr: Expression) = expr match{
        case Prod(_,_) => show(expr)
        case Number(_) => show(expr)
        case _ => s"( ${show(expr)} )"
      }

      maybeShowParentheses(elementOne) + " * " + maybeShowParentheses(elementTwo)
    }
  }

  println(show(Sum(Number(1), Number(2))))
  println(show(Sum(Sum(Number(1), Number(2)), Number(3))))
  println(show(Prod(Sum(Number(1), Number(2)), Number(3))))
  println(show(Sum(Prod(Number(1), Number(2)), Number(3))))

}
