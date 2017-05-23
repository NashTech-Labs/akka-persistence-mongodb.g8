
package com.knoldus.events

/**
 * Created by Harmeet Singh(Taara) on 19/11/16.
 */

sealed trait Event

case class Debited(value: Double) extends Event

case class Credited(value: Double) extends Event
