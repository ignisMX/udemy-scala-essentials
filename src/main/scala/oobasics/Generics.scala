package oobasics

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???

    /*

      A = Cat
      B = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfString = new MyList[String]

  //generics methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. yes, List[Cat] extends List[Animal] = Covariance
  class CovariantList[+A]
  val animal : Animal = new Cat
  val animalList:CovariantList[Animal] = new CovariantList[Cat]

  //2. No = Invariance
  class InvariantList[A]
  val invariantList : InvariantList[Animal] = new InvariantList[Animal]

  //3. Hell, no! Contravariance
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //bounded types
  class Cage[A <: Animal](animal:A)
  val cage = new Cage(new Dog)

  class Car
  //val newCage = new Cage(new Car)
}
