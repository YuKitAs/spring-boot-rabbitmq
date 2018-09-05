# spring-boot-rabbitmq

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

**List queues:**

```console
# rabbitmqctl list_queues
```

**List bindings:**

```console
# rabbitmqctl list_bindings
```
