package org.lantern.sandbox.java8repatterning.example;

import java.util.concurrent.Executors;

import org.lantern.sandbox.java8repatterning.event.Event;

public class ExampleApplication {
	private Object lock = new Object();

	public void run() {
		// Configure the Event subsystem with our own executor
		Event.configureAsyncExecutor(Executors.newFixedThreadPool(4));

		// Set up a sample API, listen to an event and then do some stuff
		ExampleAPI api = new ExampleAPI();
		api.onDidStuff.listen(this::stuffWasDone);
		api.onDidOtherStuff.listen(this::otherStuffWasDone);
		api.doStuff();
		api.doOtherStuff();

		// Wait for the asynchronous event handler to do its thing
		synchronized (lock) {
			try {
				lock.wait(60);
			} catch (InterruptedException ie) {
				System.err.println("Never notified of completion!");
			}
		}

		// We're done with our little test
		System.exit(0);
	}

	public void stuffWasDone(MyEventData data) {
		System.out.println("Stuff was done... " + data.getTimestamp() + ": "
				+ data.getMessage());
	}

	public void otherStuffWasDone(MyEventData data) {
		System.out.println("Other stuff was done... " + data.getTimestamp() + ": "
				+ data.getMessage());
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public static void main(String[] args) {
		new ExampleApplication().run();
	}

}
