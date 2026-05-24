#include<thread>
#include<iostream>
#include<mutex>
#include<semaphore>
using namespace std;
mutex mtx;
int account = 1000;
/*
race condition: 多个进程抢占同一个资源。
如何避免：共享某个变量时需要mutex，使得只有critical section code才能运行。
mutex：allow one process to 
atomic：不可分割的操作，原子操作是实现信号量的基础。信号量里的 PV 操作（wait 和 signal）必须是原子操作。

特性	互斥量 (Mutex)	信号量 (Semaphore)
所有权	有所有权。只有加锁的线程才能解锁。	无所有权。任何线程都可以执行 signal (V操作)。
初始值	通常初始为 1（解锁状态）。	可以是任意非负整数（0, 1, 2, ...）。
主要用途	保护临界区，实现互斥访问（一次只让一个线程访问共享资源）。	控制对一定数量资源的访问（如连接池、生产者-消费者问题），也用于线程同步。
值范围	只有 0 (锁定) 和 1 (解锁)。	0 到 N (计数信号量) 或 0/1 (二进制信号量，类似Mutex但无所有权)。
操作	lock / unlock （或 acquire / release）	wait (P操作) / signal (V操作)
semapahores信号量：Semaphores allow two or more processes to synchronize their actions coordinate access to a shared resource.
包含count and queue。
count分为binary and general两种。
count = 0，阻塞的是试图执行 wait() 操作的进程，而不是阻塞“所有进程来访问这个代码”。count = 1 表示：有 1 个可用资源，下一个调用 wait() 的进程不会被阻塞，而是会成功获取资源。
queue这个队列通常按先进先出 (FIFO) 顺序（队列）或按优先级排队。内核调度器会从队列中唤醒下一个进程来获取资源。
wait：检查可用资源，如果可用，继续执行；否则等待直到资源可用。
signal：某个进程执行完了，给其他等待资源的进程发送信号表示资源释放了。

wait
critical section code
signal

bounded buffer
*/
void beijing_atm() {
    lock_guard<mutex> lock(mtx);  // 自动加锁/解锁，作用域结束自动释放
    // mtx.lock();跟上面代码一样的作用，本质是不允许外面的进程中断
    int total = account;
    if (total >= 100) {
        account -= 100;
        cout << "Beijing withdraw 100, remaining: " << account << endl;
    }
}

void shanghai_atm() {
    lock_guard<mutex> lock(mtx);
    int total = account;
    if (total >= 150) {
        account -= 150;
        cout << "Shanghai withdraw 150, remaining: " << account << endl;
    }
}

int main() {
    thread t1(beijing_atm);
    thread t2(shanghai_atm);
    t1.join();
    t2.join();
    return 0;
}