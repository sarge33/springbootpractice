# Good example: 
## BlockingQueue接口及其实现 https://www.jianshu.com/p/0c87f39bc569
### ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
### LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
### PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
### DelayQueue：一个使用优先级队列实现的无界阻塞队列。
### SynchronousQueue：一个不存储元素的阻塞队列。
### LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
### LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。

Object: synchronized(object) - object.wait() object.notify() object.notifyAll()
Lock: ReentrantLock.newCondition - cond.await() cond.signal() cond.signalAll()
