package oobasics

import oobasics.Enums.Permissions.READ

object Enums extends App{

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE
    def openDocument():Unit =
      if(this == READ) println("opening document ...")
      else println("reading not allowed")
  }

  val somePermissions: Permissions = READ

  enum PermissionsWithBits(bits:Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
    def fromBits(bit: Int): PermissionsWithBits = PermissionsWithBits.NONE
  }

  val somePermissionsOrdinal = somePermissions.ordinal
  val allPermissions = PermissionsWithBits.values
  val readPermission = Permissions.valueOf("READ")

  somePermissions.openDocument()
  println(somePermissionsOrdinal)

}
