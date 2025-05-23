package basics

object Expressions extends App {

  val number = 1 + 2
  println(number)

  //Mathematical operations are expressions as well
  // + - * / & | ^ << >> >>> (right shift with zero extension)
  println(2 + 3 * 4)

  //Relational operations are expressions as well.
  // == != > >= < <=
  println(1 == number)

  //Boolean operations are expressions as well
  // ! && ||
  println(!(1 == number))

  // += -= *= /== Only works with variables
  // it brings side effects
  var aVariable = 2
  aVariable += 3 // it also works with -= *= /=
  println(aVariable)

  //Instructions (DO) vs Expressions (VALUE)
  //If an expression is used as a value, it is called an expression
  //If an expression is used as a statement, it is called an instruction

  val aCondition = true
  //Here computes and returns a value
  val aConditionedValue = if (aCondition) 5 else 3 // This is an expression
  println("Should print after this line")
  println(aConditionedValue)
  println(if (aCondition) 5 else 3) //This is an instruction

  // Everything in Scala is an expression

  //Unit is equivalent to void
  var counter = 0
  //Loops returns Unit
  val aWhile : Unit = while(counter < 10){
    println(counter)
    counter += 1
  }
  println(aWhile)

  //Code blocks are expressions as well
  val aCodeBlock = {
    val numberOne = 2
    val numberTwo = numberOne + 1

    if (numberOne < numberTwo) "Less" else "More"
  }

  //constants and variables inside code blocks are local and not visible outside
  // val numberThree = numberOne + 1 // This is not allowed

  //Recap
  //Expressions is "Give me the value of something"
  //Instructions is "Do something"
}
