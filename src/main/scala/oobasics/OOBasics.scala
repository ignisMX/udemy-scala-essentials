package oobasics

object OOBasics extends App {

  private val personOne = new Person("John", 24)
  println(s"number: ${personOne.number}")
  println(s"age: ${personOne.age}")
  personOne.greet("Shiqui")
  personOne.greet()

  private val personTwo = new Person
  personTwo.greet()

  private val writerOne = new Writer("J.K.", " Rowling", 1990)
  println(s"full name: ${writerOne.getFullName()}")

  private val novelOne = new Novel("Harry Potter", 2001, writerOne)
  novelOne.isWrittenBy()
  println(s"is written by ${writerOne.getFullName()} ? : ${novelOne.isWrittenBy(writerOne)}")
  novelOne.getAuthorAge()

  private val novelTwo = novelOne.copy(2012)
  novelTwo.isWrittenBy()
  println(s"is written by ${writerOne.getFullName()} ? : ${novelTwo.isWrittenBy(writerOne)}")
  novelTwo.getAuthorAge()

  private val counterOne = new Counter(1)
  println(s"current Counter for counterOne: ${counterOne.getCurrentCount()}")

  private val counterTwo = counterOne.increment()
  println(s"current Counter for counterTwo: ${counterTwo.getCurrentCount()}")

  private val counterThree = counterOne.decrement()
  println(s"current Counter for counterThree: ${counterThree.getCurrentCount()}")

  private val counterFour = counterOne.increment(5)
  println(s"current Counter for counterFour: ${counterFour.getCurrentCount()}")

  private val counterFive = counterOne.decrement(5)
  println(s"current Counter for counterFive: ${counterFive.getCurrentCount()}")


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

class Writer(name: String, surname: String, val yearOfBirth: Int) {

  def getFullName(): String = s"${this.name} ${this.surname}"
}

class Novel(name: String, releaseYear: Int, author: Writer) {

  def isWrittenBy(): Unit = println(s"Written by ${this.author.getFullName()}")

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def getAuthorAge(): Unit = println(s"author age: ${releaseYear - this.author.yearOfBirth}")

  def copy(releaseYear: Int): Novel = new Novel(this.name, releaseYear, this.author)
}

class Counter(initialValue: Int) {

  def getCurrentCount(): Int = this.initialValue

  def increment(): Counter = new Counter(getCurrentCount() + 1)

  def decrement(): Counter = new Counter(getCurrentCount() - 1)

  def increment(value: Int): Counter = new Counter(getCurrentCount() + value)

  def decrement(value: Int): Counter = new Counter(getCurrentCount() - value)

}
