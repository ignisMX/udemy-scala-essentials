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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException()

  def tail: MyList[Nothing] = throw new NoSuchElementException()

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(value: A): B
}


object MyListTest extends App {
  val listOne: MyList[Int] = new Cons(1, Empty)
  println(listOne.head)
  println(listOne.add(4).head)
  println(listOne.isEmpty)


  val listTwo: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listTwo.tail.head)
  println(listTwo.toString)

  val listThree: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
  println(listThree.toString)

  val listFour: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))

  println(listTwo.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listTwo.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  println((listTwo ++ listFour).toString)
  println(listTwo.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(value: Int): MyList[Int] = new Cons(value, new Cons(value + 1, Empty))
  }).toString)
}

