
package com.knoldus.commands

/**
 * Created by Harmeet Singh(Taara) on 19/11/16.
 */
sealed trait Command

case class Debit(value: Double) extends Command

case class Credit(value: Double) extends Command

case object PrintBalance extends Command

case object GetBalance extends Command
