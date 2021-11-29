# AWS Cloud Practitioner

* Not a good certification by itself, but it's good for studying for the solution architect course
* Two types of questions: **multiple-choice** and **multiple response**: 
  * Multiple-response question solutions are generally **2/5** or **3/6** 

## Cloud Computing

* **CLOUD COMPUTING**: The practice of using a network of remote servers hosted on the Internet to store, manage, and process data, rather than a local server or a personal computer. The opposite of this would be on-premise computing, where you own the servers, hire IT employees, rent the physical land, and in general take all the risk. 
* **ADVANTAGES OF CLOUD COMPUTING**: (THIS IS IMPORTANT, MEMORIZE THIS)
  * No big upfront cost; save money by paying only when you actually consume the computing services
  * Additionally, you are saving money because hundreds of thousands of other people are sharing the cost
  * You don't need to guess about the amount of infrastructure you need; save on costs
  * Launch resources way faster than on-premise; you can utilize already made resources in a few clicks rather than waiting for your IT engineers to create the solution on-premise 
  * Stop wasting time and resources on running data centers; you can focus on your customers instead
  * Go global in a much easier way, since the on-premise server is in one physical location 
* **TYPES OF CLOUD COMPUTING**: 
  * SaaS (Software as a Service): A completed product that is managed by the service provider; examples include Gmail and Office365
  * PaaS (Platform as a Service): Hardware and software tools that, for lack of better words, just work; companies can instead focusing on managing and deploying apps rather than making it work with said tools. Examples include Heroku, AWS Elastic Beanstalk, and Engines for Google
  * IaaS (Infrastructure as a Service): Infrastructure that is available over the cloud; includes data centers, IT staff, and hardware. Examples include AWS, Azure, and Google Cloud Platform
* **CLOUD COMPUTING DEPLOYMENT MODELS**
  * Cloud: Fully utilizing cloud computing. Examples include startups, such as Dropbox and Squarespace
  * Hybrid: Utilizing cloud computing for some services, but not all. Examples include banks, fintech, and consulting firms. These businesses use hybrid computing because they have some clients that are not comfortable with having their data on the cloud, or they have legacy on-premise computing that is still usable 
  * On-premise: Fully utilizing on-premise computing. Examples of businesses that use these are government, hospitals, and insurance companies (e.g. extremely sensitive data)
* **AWS GLOBAL INFRASTRUCTURE**
  * AWS has >190 million customers
  * **REGION**: *Distinct* physical location with multiple availability zones; each region has at least 2 AZs, and the biggest region is **US-EAST**. Not all services are available in all regions, but new services almost always become available first in **US-EAST**
  * **AVAILABILITY ZONES**: One or more individual data centers; AZs are represented by a region code followed by a letter identifier. There is <10ms latency between Availability Zones. Most businesses also distribute their instances over multiple AZs, so that if one fails, there is another ready to process requests
  * **EDGE LOCATION**: Data center owned by a trusted partner of AWS. These locations specifically serve requests for CloudFront and Route 53. These usually help to get data/upload data to AZs. 
* **GOVCLOUD** 
  * GovCloud regions are special regions that allow customers to host sensitive information. They are only allowed to be operated by U.S. citizens, and are usuallly government-oriented. 

