## fork()
经过这个代码，进程调用了fork()系统调用，这是操作系统提供的创建新进
程的方法。
因为 fork() 成功后，两个进程几乎一模一样（共享代码、数据等），它们都需要知道自己是谁，从而执行不同的任务。这个返回值就是它们区分彼此的唯一标识。
在父进程中： fork() 返回新创建的子进程的 PID（一个大于0的整数）。

在子进程中： fork() 返回 0。
```c
#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();  // 关键：这行在父子进程中都会执行，但返回值不同

    if (pid < 0) {
        // 错误处理：fork失败
        perror("fork failed");
    } 
    else if (pid == 0) {
        // 子进程进入这个分支（因为fork()返回0）
        printf("我是子进程，我的PID是 %d，我看到的返回值是 %d\n", getpid(), pid);
    } 
    else {
        // 父进程进入这个分支（因为fork()返回子进程的PID， >0）
        printf("我是父进程，我的PID是 %d，我看到的返回值是 %d（这是我孩子的PID）\n", getpid(), pid);
    }
    return 0;
}
```

## wait()
上述代码的输出结果是不确定的，可能是父进程先打印也可能是子进程。为了让结果确定，这样就会等待子进程执行完。
```c
 int wc = wait(NULL); 
18           printf("hello, I am parent of %d (wc:%d) (pid:%d)\n", 
19                   rc, wc, (int) getpid());
```

## exec()
exec() 的作用是：在当前进程中，将当前的程序替换成一个全新的程序。

可以理解为“灵魂换血”：进程的“身体”（PID、环境变量、打开的文件描述符等大部分属性）保持不变，但“灵魂”（执行的代码、数据、堆栈）被完全替换成另一个程序