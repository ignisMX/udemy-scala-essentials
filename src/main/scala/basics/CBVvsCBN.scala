package basics

object CBVvsCBN extends App {

  private def calledByValue(number: Long): Unit = {
    println(s"called by value $number")
    println(s"called by value $number")
  }

  private def calledByName(number: => Long): Unit = {
    println(s"called by name $number")
    println(s"called by name $number")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  private def infinite(): Int = 1 + infinite()

  private def printFirstParameter(paramOne: Int, paramTwo: => Int): Unit = println(paramOne)

  printFirstParameter(34, infinite())
}
