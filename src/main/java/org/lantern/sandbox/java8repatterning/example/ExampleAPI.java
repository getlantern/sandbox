package org.lantern.sandbox.java8repatterning.example;

import org.lantern.sandbox.java8repatterning.event.Event;
import org.lantern.sandbox.java8repatterning.event.IEvent;

public class ExampleAPI {
	public final IEvent<MyEventData> onDidStuff = new Event<MyEventData>();
	public final IEvent<MyEventData> onDidOtherStuff = new Event<MyEventData>();

	public void doStuff() {
		System.out.println("Doing stuff");
		try {
			Thread.sleep(50);
		} catch (InterruptedException ie) {
			// ignore
		}
		onDidStuff.emitAsync(new MyEventData("I did stuff", System
				.currentTimeMillis()));
	}

	public void doOtherStuff() {
		System.out.println("Doing other stuff");
		try {
			Thread.sleep(50);
		} catch (InterruptedException ie) {
			// ignore
		}
		onDidOtherStuff.emitAsync(new MyEventData("I did other stuff", System
				.currentTimeMillis()));
	}
}
