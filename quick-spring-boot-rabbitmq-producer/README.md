# 一、Mac下安装RabbitMQ

### 1. 进入[官网](http://www.rabbitmq.com/install-standalone-mac.html)下载

### 2. 设置命令访问路径
```
export RABBIT_HOME=/Users/zn/software/rabbitmq_server-3.7.10
export PATH=${PATH}:${RABBIT_HOME}/sbin
```
### 3. 启动server

```
// 以窗口进程进行启动
rabbitmq-server
// 以后台进程进行启动
rabbitmq-server -detached
```
### 4. 启动界面管理

进入${RABBIT_HOME}/sbin路径，输入
```
sudo ./rabbitmq-plugins enable rabbitmq_management
```
执行一次以后不用再次执行

执行后在浏览器里输入localhost:15672可进入管理界面

用户名：guest，密码：guest

### 5. 查看状态

```
rabbitmqctl status
```

### 6. 关闭server

```
// 前台进程状态下
ctrl + c
// 后台进程状态下
rabbitmqctl stop
```