package lifecycle;

import java.util.HashMap;

import middleware.igor_marques_basic_remote_patterns.server_side.AbstractRemoteObject;

public class RemoteObjectPool {
	private static RemoteObjectPool instance;
	
	public static RemoteObjectPool getInstance() {
		if (instance == null)
			instance = new RemoteObjectPool();
		return instance;
	}
	
	private HashMap<String, AbstractRemoteObject> objects;
	
	private RemoteObjectPool() {
		objects = new HashMap<String, AbstractRemoteObject>();
	}
	
	public void registerObject(String url, AbstractRemoteObject obj) {
		objects.put(url, obj);
	}
	
	public void removeObject(String url, AbstractRemoteObject obj) {
		objects.remove(url);
	}
	
	public AbstractRemoteObject getObject(String url) {
		return objects.get(url);
	}
}
