package org.lantern.sandbox.java8repatterning.event;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Event<D> implements IEvent<D> {
	private static final AtomicReference<ExecutorService> s_asyncExecutor = new AtomicReference<ExecutorService>();

	static {
		// Configure a sensible default executor
		configureAsyncExecutor(Executors.newSingleThreadExecutor());
	}

	public static void configureAsyncExecutor(ExecutorService newExecutor) {
		ExecutorService oldExecutor = s_asyncExecutor.getAndSet(newExecutor);
		if (oldExecutor != null) {
			oldExecutor.shutdown();
		}
	}

	private final Queue<IEventHandler<D>> handlers = new LinkedBlockingQueue<IEventHandler<D>>();

	public void listen(IEventHandler<D> handler) {
		handlers.add(handler);
	}

	public void unlisten(IEventHandler<D> handler) {
		handlers.remove(handler);
	}

	public void emitSync(D data) {
		for (IEventHandler<D> handler : handlers) {
			handler.handle(data);
		}
	}

	public void emitAsync(D data) {
		for (IEventHandler<D> handler : handlers) {
			s_asyncExecutor.get().submit(() -> {
				handler.handle(data);
			});
		}
	}
}
