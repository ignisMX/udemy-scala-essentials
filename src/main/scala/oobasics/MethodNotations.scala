package oobasics

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {

    def likes(movie: String): Boolean = movie == this.favoriteMovie

    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"${this.name} what the heck?"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def +(nickname: String): Person = new Person(s"${this.name} $nickname", this.favoriteMovie)

    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def learns(topic: String): String = s"${this.name} is learning $topic"

    def learnsScala: String = this learns "Scala"

    def apply(times: Int): String = s"${this.name} watched ${this.favoriteMovie} $times times"
  }

  private val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  private val tom = new Person("Tom", "Fight Club")
  println(mary.hangOutWith(tom))
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  //All operators are methods
  //Akka operators ! ?

  //Prefix notation
  val x = -1 // is equivalent to unary_-
  val y = 1.unary_-
  //Unary only works with - + ~ !

  println(mary.unary_!)
  println(!mary)

  //postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary()) //equivalent to mary.apply()

  println((mary + "The Rockstar").apply())
  println((+mary).age)

  println(mary learns "Java")
  println(mary learnsScala)

  println(mary(3))

}
