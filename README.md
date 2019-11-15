# spring-boot-rabbitmq

## Prerequisites

* RabbitMQ 3.6.10 ([installation guide](https://www.rabbitmq.com/install-debian.html) on Ubuntu)
* Spring Boot AMQP Starter 2.2.1
* Java 1.8
* Maven 3.5.4

## RabbitMQ

**Start RabbitMQ Server:**

```console
# service rabbitmq-server start
```
**List queues:**

```console
# rabbitmqctl list_queues
```

**List exchanges:**

```console
# rabbitmqctl list_exchanges
```

**List bindings:**

```console
# rabbitmqctl list_bindings
```

**Open Management UI:**

Visit `http://localhost:15672/` and login with username `guest` and password `guest`.


## Spring Application

**Build project with Maven:**

```console
$ mvn clean package
```

**Run the app with `sender` and `receiver` profiles in two terminals, for example:**

```console
$ ./run-sender.sh tut1
$ ./run-receiver.sh tut1
```

For more information about each tut, see [Wiki](https://github.com/YuKitAs/spring-boot-rabbitmq/wiki).


