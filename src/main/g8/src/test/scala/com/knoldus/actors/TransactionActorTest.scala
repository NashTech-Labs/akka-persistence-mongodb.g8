
package com.knoldus.actors

import akka.pattern.ask
import com.knoldus.commands.{Credit, Debit, GetBalance, PrintBalance}

/**
 * Created by Harmeet Singh(Taara) on 19/11/16.
 */
class TransactionActorTest extends PersistenceSpec {

  it should "recover transaction on crash" in {
    var transactionActor = system.actorOf(TransactionActor.props(100))
    transactionActor ! Credit(100)
    transactionActor ! Credit(200)
    transactionActor ! PrintBalance

    (transactionActor ? GetBalance).futureValue shouldBe 400

    transactionActor ! Debit(50)

    cleanup(transactionActor)

    transactionActor = system.actorOf(TransactionActor.props(100))
    transactionActor ! Credit(100)
    transactionActor ! PrintBalance

    (transactionActor ? GetBalance).futureValue shouldBe 450
  }
}
