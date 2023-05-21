<h2>CDC - Change Data Capture</h2>

```
(CDC) is a technique and a design pattern.
```
<p> 
We often use it to replicate data between databases in real-time.
Debezium lets your apps react every time your data changes, and you
don't have to change your apps that modify the data. Debezium 
continuously monitors your databases and lets any of your applications
stream every row-level change in the same order they were committed 
to the database
</p>

*** 

<h3>How to run an application</h3>

* Kick off Postgres container by pushing docker command: `docker-compose up -d`
* Run Everest service first 
* Run SpyGlass service

<p> Bunch of data is generating and pushing from Everest to postgres periodically.
CDC debezium provides a config to setup listeners that allows to monitor DB changes.
When postgres has been altered the listener notifies handler to process data by itself.
The data can ben pushed to any DBs or Caches. It allows to sync up the data between multiple resources. SpyGlass service saves portfolios to H2 DB. 
</p>



***
*author*: ```dzmitry kapachou```