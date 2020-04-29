package com.softwaremill.debug

object Debug:
  import scala.quoted._

  inline def hello(): Unit = println("Hello, world!")
  
  // -- 
  
  inline def debugSingle(inline expr: Any): Unit = ${debugSingleImpl('expr)} 
  
  private def debugSingleImpl(expr: Expr[Any])(using QuoteContext): Expr[Unit] = 
    '{ println("Value of " + ${Expr(expr.show)} + " is " + $expr) }
  
  // --

  inline def debug(inline exprs: Any*): Unit = ${debugImpl('exprs)}

  private def debugImpl(exprs: Expr[Seq[Any]])(using QuoteContext): Expr[Unit] = 
    def showWithValue(e: Expr[_]): Expr[String] = '{${Expr(e.show)} + " = " + $e}
  
    val stringExps: Seq[Expr[String]] = exprs match 
      case Varargs(es) => 
        es.map {
          case Const(s: String) => Expr(s)
          case e => showWithValue(e)
        }
      case e => List(showWithValue(e))
  
    val concatenatedStringsExp = stringExps.reduceOption((e1, e2) => '{$e1 + ", " + $e2}).getOrElse('{""})
    '{println($concatenatedStringsExp)}

