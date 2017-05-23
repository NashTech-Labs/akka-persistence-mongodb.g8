# Akka Persistence using MongoDB

Akka is a powerful actor based tool kit for **Concurrency**, **Parallelism** and **Clustering**. But for every concurrent applications, Akka actors are not fit because Futures are another alternative for performing Concurrency. Most of the developers follow simple rules for using Future and Actors is **“Futures for Concurrency, Actors for State“**.

In concurrent applications most of the time we are facing race conditions and deadlocks. Because of this, we are using actors for maintain state in concurrent applications, but actors are maintain state in memory. When ever the system goes fail it is difficult to recover actor state and our whole applications goes down. For maintaining actor state on secondary storage, Akka tool kit provide us tool “Persistence“.

[For more detail please click on link](https://blog.knoldus.com/2016/11/21/akka-persistence-using-mongodb-part-i/)