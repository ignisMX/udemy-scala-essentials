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

  def map[B](transformer: A => B): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def withFilter(predicate: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(consumer: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  def fold[B](starValue: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException()

  def tail: MyList[Nothing] = throw new NoSuchElementException()

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def withFilter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def foreach(consumer: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("List do not have the same length")
    else Empty

  def fold[B](starValue: B)(operator: (B, Nothing) => B): B = starValue
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def withFilter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  def foreach(consumer: A => Unit): Unit = {
    consumer(h)
    t.foreach(consumer)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(element: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(element, Empty)
      else if (compare(element, sortedList.head) <= 0) new Cons(element, sortedList)
      else new Cons(sortedList.head, insert(element, sortedList.tail))


    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("List do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  def fold[B](startValue: B)(operator: (B, A) => B): B = t.fold(operator(startValue, h))(operator)
}

trait MyPredicate[-T] {
  def apply(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def apply(value: A): B
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


  println(listTwo.map(_ * 2).toString)


  println(listTwo.filter(_ % 2 == 0).toString)

  println((listTwo ++ listFour).toString)
  println(listTwo.flatMap(value => new Cons(value, new Cons(value + 1, Empty))).toString)

  val listFive: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listFive == listTwo)

  println("Consumer Example")
  listFive.foreach(println)

  println(listFive.sort((x, y) => y - x))
  val listSix: MyList[Int] = new Cons(4, new Cons(5, Empty))
  println(listSix.zipWith(listThree, _ + "-" + _))

  println(listTwo.fold(0)(_ + _))

  val mappedList = for {
    number <- listSix
    string <- listThree
  } yield s"$number-$string"

  println(mappedList)

  val filteredList = for {
    element <- listTwo
    if (element % 2 == 0)
  } yield element

  println(filteredList)

  val flattedMap = for {
    element <- listThree
    nestedList <- new Cons(element, new Cons(s"$element-flatted", Empty))
  } yield nestedList


  println(flattedMap)
}

