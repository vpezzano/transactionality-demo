# Example of transactionality in Spring Boot.
Acid transaction properties: atomicity, consistency, isolation, durability. The following definitions are
from Wikipedia.
Atomicity:   an atomic transaction is an indivisible and irreducible series of database operations 
             such that either all occur, or nothing occurs.
Consistency: it's the requirement that any given database transaction must change affected data only in
             allowed ways. Any data written to the database must be valid according to all defined rules,
             including constraints, cascades, triggers, and any combination thereof.
Isolation:   determines how transaction integrity is visible to other users and systems. For example, when a
             user is creating a Purchase Order and has created the header, but not the Purchase Order lines,
             is the header available for other systems/users (carrying out concurrent operations, such as a
             report on Purchase Orders) to see?
             A lower isolation level increases the ability of many users to access the same data at the same
             time, but increases the number of concurrency effects (such as dirty reads or lost updates) users
             might encounter. Conversely, a higher isolation level reduces the types of concurrency effects
             that users may encounter, but requires more system resources and increases the chances that one
             transaction will block another.
Durability:  property which guarantees that transactions that have committed will survive permanently.