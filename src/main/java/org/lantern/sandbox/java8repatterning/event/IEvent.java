package org.lantern.sandbox.java8repatterning.event;

public interface IEvent<D> {
	void listen(IEventHandler<D> handler);

	void unlisten(IEventHandler<D> handler);

	void emitSync(D data);

	void emitAsync(D data);
}
