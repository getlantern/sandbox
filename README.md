## Java 8 Repatterning

This project shows off some new ways of structuring APIs and apps
that have opened up as a result of Java 8.  It is a sandbox for
playing around with ideas.  Naturally, it requires Java 8 to compile,
though for distributing code that uses Java 8 features, one can use
[Retrolambda](https://github.com/orfjackal/retrolambda) to distribute it
as Java 6 and 7 compatible bytecode.

### Events

See [`ExampleApplication.java`](src/main/java/org/lantern/sandbox/java8repatterning/example/ExampleApplication.java)
for an example of an application that does event handling that confers the following benefits:

 * Strongly typed
 * Terse and clear syntax for declaring and consuming events
 * Easy unit testing because of no data/infrastructure cross-contamination between instances of [`ExampleAPI`](src/main/java/org/lantern/sandbox/java8repatterning/example/ExampleAPI.java)
 * Low magic (everything is discernible by following method calls in the app,
   there's no magic framework that controls and structures things for us).
 * The same type of event data can be used for different logical events
 * Each API has control over how it chooses to implement the event handling
   interfaces (e.g. use the available [`Event`](src/main/java/org/lantern/sandbox/java8repatterning/event/Event.java)
 * The available `Event` implementation is configurable at a system-wide level

 