package oobasics

object OOBasics extends App {

  private val personOne = new Person("John", 24)
  println(s"number: ${personOne.number}")
  println(s"age: ${personOne.age}")
  personOne.greet("Shiqui")
  personOne.greet()

  private val personTwo = new Person
  personTwo.greet()
}

//Constructor
class Person(name: String, val age: Int = 35) {

  val number = 2

  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi $name, welcome to scala ")

  def greet(): Unit = println(s"Hi, I am $name")

  def this(name: String) = this(name, 50)

  def this() = this("David Johns")
}
