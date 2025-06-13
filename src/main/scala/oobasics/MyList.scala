package oobasics

abstract class MyList[+A] {
  /*
  * head = first element of the list
  * tail = remainder fo the list
  * isEmpty = is the list is empty
  * add(int) => new list with this element added
  * toString => a String representation of the list
  * */

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException()

  def tail: MyList[Nothing] = throw new NoSuchElementException()

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

object MyListTest extends App {
  val listOne: MyList[Int] = new Cons(1, Empty)
  println(listOne.head)
  println(listOne.add(4).head)
  println(listOne.isEmpty)


  val listTwo: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listTwo.tail.head)
  println(listTwo.toString)

  val listThree : MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  println(listThree.toString)

}

