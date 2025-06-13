package oobasics

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahaha")
  }

  println(funnyAnimal.eat)

  class Person(name: String) {
    def greet: Unit = println(s"Hi, my name is $name and I am here to help you")
  }

  val jim = new Person("Jim") {
    override def greet: Unit = println(s"Hi I am Jim ")
  }

  println(jim.greet)
}

