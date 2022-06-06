# System Design

---

## Introduction

- *Distributed Systems*: A group of computers working together (abstracted from the user into a single application) to respond to a user's query. Distributed systems have many fallacies (assumptions about the system before making it): 
  - Network is reliable 
  - Zero lag (latency) 
  - Infinite bandwidth
  - Topology of the network doesn't change
  - Network is secure
  - One administrator
  - Zero transport cost
- Distributed systems have many shared characteristics: 
  - No shared clock: leads to *clock drift*, where timers on different parts of the system go out of sync and cause a lot of fuck ups
  - No shared memory: Each part of the system has its own memory 
  - Shared resources: Anything one node has should be able to share to other nodes
  - Concurrency and Consistency: All parts of the system should be working together and at the same time
- Distributed systems should be able to talk to each other; in other words, a unified format/protocol
- You will need to handle a lot of issues occurring with distributed systems: 
  - Client can't find server: Protocol is needed that allows a client to find another server
  - Server crash mid request: Protocol is needed to resend a request
  - Server response is lost: Protocol is needed to find a fallback network to use in case the network that the server use to send a response back to the client fails 
  - Client crashes
- There are, however, a lot of really nice benefits 
  - More reliable and scalable: If one part of the system fails, the client can rely on another part of the system. Additionally, since the system is already designed to work on multiple machines, it is much easier to just add a few more machines to the mix
  - Lower latency/better performance: An application can run on multiple data centers; therefore additionally making it more cost-effective 

---

## Performance Metrics

- *Scalability*: Easiness of a system to grow/manage more traffic
- *Reliability*: Probability a system will fail; measured by MTBF (Mean time between failures)

$$
\text{MTBF} = \frac{\text{Total Elapsed Time} - \text{Total Downtime}}{\text{\# of failures}}
$$

* *Availability*: Most important metric user-wise; amount of time a system is operational during a given period of time

$$
\text{Availability }\% = \frac{\text{Available Time}}{\text{Total Time}} \times 100
$$

* A reliable system is always an available system, but not vice versa. ALWAYS go for reliability rather than availability 
* *Efficiency*: How well the system performs; measured by latency and throughput (total requests the system can meet)
* *Manageability*: Speed/difficulty involved with maintaining system, e.g. difficulty to deploy updates. The goal should be to abstract away infrastructure so engineers don't need to worry about it
* *Observability*: How hard it is to track bugs

---

## Numbers Programmers Should Know

* This entire section just espouses the fact that (whenever possible) you should keep your data locally and as close as possible to the program (CPU)
  * Avoid network calls
  * Replicate data across data centers for recovery and better performance
  * Use CDNs (content delivery networks) to get rid of lag
  * Keep often accessed data in RAM 

---

## Quick Math for Capacity Estimates

* 8 bits = 1 byte, 1024 bytes = 1 KB, 1024 KB = 1 MB, 1024 MB = 1 GB, 1024 GB = 1 TB
* `char` = 1 byte, `int` = 4 bytes, `time` = 4 bytes
*  *Traffic Estimates*: The total number of requests the app will receive; calculated by

$$
\text{Average Daily Active Users (DAU)} \times \text{Average Reads/Writers Per User}
$$

* *Memory*: (the 0.2 makes sure the 80/20 rule is used; that is, 20% of your data will be 80% of your requests/traffic)

$$
\text{Read Requests Per Day} \times \text{Average Request Size} \times .2
$$

* *Bandwidth*: 

$$
\text{Requests Per Day} \times \text{Request Size}
$$

* *Storage*

$$
\text{Writes Per Day} \times \text{Size of Write} \times \text{Time to Store Data}
$$

---

## Horizontal vs Vertical Scaling

* *Vertical Scaling*: The simplest way to scale an application; upgrade a specific area of an application
  * This has diminishing returns and is very limited in terms of scalability 
  * Single point of failure as well
* *Horizontal Scaling*: The harder way to scale; more complex upfront, but worth it in the long run
  * Redundancy is built in 
  * Load balancer is required to distribute traffic 

---

## System Design Components

* *Load Balancers*: balance incoming traffic to multiple servers
  * Can be software OR hardware based
  * Examples include Nginx/HAProxy (software),  F5/Citrix (hardware) 
* There are many routing methods for LBs: 
  * *Round Robin*: LB has a list of servers that are available and simply cycles through the list. This could result in uneven traffic
  * *Least Connections*: LB routes requests based on the number of client connections to server; this is useful for chat/streaming apps
  * *Least Response Time*: Routes based on how quickly servers respond
  * *IP Hash*: Routes client to server based on IP, since a certain server may have the data that an app wants. This is useful for stateful sessions
* There are two types of LBs, L4 and L7
  * L4 only has access to TCP and UDP data. It is faster, but a lack of info can lead to uneven traffic
  * L7 has full access to HTTP data. It can also terminate SSL connections and decrypt traffic. L7 can authenticate a request, and in general can create smarter routing options

## Caching

* *Caching*: Taking data that would be stored on a hard drive/disk and transfer it to memory. This improves app performance and saves money
  * Reading from cache is 50-200x faster
  * Caching can serve the same traffic with fewer resources used
  * You can pre-calculate and cache data to save time as well
* You can use caching in multiple layers of the app, such as in DNS, CDN, the app itself, and the database
* *Cache Eviction*: We need this to prevent stale data and we must only cache the most valuable data
  * *TTL (Time To Live)*: a set time period before a certain cache entry is deleted
* 
