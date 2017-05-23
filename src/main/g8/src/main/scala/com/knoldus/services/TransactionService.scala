
package com.knoldus.services

/**
 * Created by Harmeet Singh(Taara) on 19/11/16.
 */
object TransactionService {

  def balanceDebit(balance: Double, debitAmount: Double): Double = balance - debitAmount

  def balanceCredit(balance: Double, creditAmount: Double): Double = balance + creditAmount
}

