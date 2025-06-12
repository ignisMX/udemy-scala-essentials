package oobasics

abstract class MyList {
  /*
  * head = first element of the list
  * tail = remainder fo the list
  * isEmpty = is the list is empty
  * add(int) => new list with this element added
  * toString => a String representation of the list
  * */

  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(element: Int): MyList

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException()

  def tail: MyList = throw new NoSuchElementException()

  def isEmpty: Boolean = true

  def add(element: Int): MyList = new Cons(element, Empty)

  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h

  def tail: MyList = t

  def isEmpty: Boolean = false

  def add(element: Int) = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

object MyListTest extends App {
  var listOne = new Cons(1, Empty)
  println(listOne.head)
  println(listOne.add(4).head)
  println(listOne.isEmpty)


  var listTwo = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listTwo.tail.head)
  println(listTwo.toString)

}

