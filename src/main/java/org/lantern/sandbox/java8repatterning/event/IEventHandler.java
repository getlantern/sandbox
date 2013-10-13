package org.lantern.sandbox.java8repatterning.event;

public interface IEventHandler<D> {
	void handle(D event);
}
