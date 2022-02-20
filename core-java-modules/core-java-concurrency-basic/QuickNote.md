Reference: https://www.baeldung.com/java-runnable-callable

## Runable: 
   The Runnable interface is a functional interface and has a single run() method which doesn't accept any parameters and does not return any values.
## Callable: 
   The Callable interface is a generic interface containing a single call() method – which returns a generic value V:
```java
public interface Callable<V> {
    V call() throws Exception;
}
```
----------------------
Exception Handling
==================
## Runnable
Since the method signature does not have the “throws” clause specified, there is no way to propagate further checked exceptions.

## Callable
Callable's call() method contains “throws Exception” clause so we can easily propagate checked exceptions further.

---------------------

