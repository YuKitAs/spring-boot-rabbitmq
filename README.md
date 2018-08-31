# spring-boot-rabbitmq

**Build project with Maven:**

```console
$ mvn clean package
```

**Run the app with `sender` and `receiver` profiles in two terminals, for example:**

```console
$ java -jar target/spring-boot-rabbitmq-0.0.1-SNAPSHOT.jar --spring.profiles.active=tut1,sender
$ java -jar target/spring-boot-rabbitmq-0.0.1-SNAPSHOT.jar --spring.profiles.active=tut1,receiver
```

**List queues:**

```console
# rabbitmqctl list_queues
```
