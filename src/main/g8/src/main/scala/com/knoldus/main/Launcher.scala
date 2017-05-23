
package com.knoldus.main

import scala.concurrent.Await
import scala.concurrent.duration._

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import com.knoldus.actors.TransactionActor
import com.knoldus.commands.{Credit, Debit, GetBalance}


/**
 * Created by Harmeet Singh(Taara) on 19/11/16.
 */

//scalastyle:off
object Launcher extends App {

  implicit val timeout = Timeout(50 seconds)
  val system = ActorSystem("transaction-system")
  var transactionActor = system.actorOf(TransactionActor.props(10000))

  Await.result(transactionActor ? Credit(1000), Duration.Inf)

  Await.result(transactionActor ? Debit(5000), Duration.Inf)

  var future = transactionActor ? GetBalance
  var balance = Await.result(future, Duration.Inf).asInstanceOf[Double]

  println(s"Before Killing Remaining Balance ${ balance }")
  system.stop(transactionActor)

  println(">>>>>>>>>>>>>>>>>>>>> Actor Killed <<<<<<<<<<<<<<<<<<<<<<<<<")
  println(">>>>>>>>>>>>>>>>>>>>> Recovery Start <<<<<<<<<<<<<<<<<<<<<<<<<")
  var transactionActor1 = system.actorOf(TransactionActor.props(10000))

  Await.result(transactionActor1 ? Credit(11000), Duration.Inf)

  Await.result(transactionActor1 ? Debit(2500), Duration.Inf)

  future = transactionActor1 ? GetBalance
  balance = Await.result(future, Duration.Inf).asInstanceOf[Double]

  println(s"After Killing Remaining Balance ${ balance }")
  system.stop(transactionActor1)
  system.terminate()
}
