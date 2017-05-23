
package com.knoldus.actors

import akka.actor.{ActorLogging, Props}
import akka.persistence.{PersistentActor, RecoveryCompleted}
import com.knoldus.commands.{Credit, Debit, GetBalance, PrintBalance}
import com.knoldus.events.{Credited, Debited, Event}
import com.knoldus.services.TransactionService

/**
 * Created by Harmeet Singh(Taara) on 19/11/16.
 */
class TransactionActor(openBalance: Double) extends PersistentActor with ActorLogging {

  var balance = openBalance;

  override def receiveRecover: Receive = {
    case event: Event => {
      log.info(s"${ event } Recover Start")

      updateBalance(event)
    }

    case RecoveryCompleted => log.info("Balance recovery Completed")
  }

  override def receiveCommand: Receive = {
    case Debit(amount) if balance >= amount => {
      log.info(s"${ amount }: Amount Debit ... ")

      persist(Debited(amount))(updateBalance)
      sender() ! "Done Debit"
    }
    case Credit(amount) => {
      log.info(s"${ amount }: Amount Credit ... ")

      persist(Credited(amount))(updateBalance)
      sender() ! "Done Credit"
    }

    case PrintBalance => log.info(s"Remaining Balance: ${ balance }")
    case GetBalance => sender() ! balance
  }

  override def persistenceId: String = TransactionActor.name

  val updateBalance: Event => Unit = {
    case Debited(amount) => balance = TransactionService.balanceDebit(balance, amount)
    case Credited(amount) => balance = TransactionService.balanceCredit(balance, amount)
  }
}

object TransactionActor {

  def props(openBalance: Double): Props = Props(classOf[TransactionActor], openBalance)

  val name = "balance-transactions"
}
