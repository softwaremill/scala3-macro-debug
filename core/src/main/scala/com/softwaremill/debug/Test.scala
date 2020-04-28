package com.softwaremill.debug

object Main extends App:
  val z = 2

  def test =
    Debug.hello()

    val x = 0
    val y = 1

    println("--")

    Debug.debugSingle(x)
    Debug.debugSingle(x+y)

    println("--")

    Debug.debug(x)
    Debug.debug(x, y)
    Debug.debug(x+y)
    Debug.debug(x, x+y)
    Debug.debug("A", x, x+y)
    Debug.debug("A", x, "B", y)
    Debug.debug(x, y, z)

  test