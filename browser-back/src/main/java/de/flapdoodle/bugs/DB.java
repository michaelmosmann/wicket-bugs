package de.flapdoodle.bugs;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Session;


public class DB {
	static Map<Class<?>, Integer> lastSavedCounter=new HashMap<Class<?>, Integer>();
	
	public static synchronized int getLastSavedCounter(Class<?> key,int defaultValue) {
		Integer ret=lastSavedCounter.get(key);
		String sessionId=Session.get().getId();
		System.out.println("DB: lastSavedCounter "+key+"("+defaultValue+")="+ret+"["+sessionId+"]");
		if (ret!=null) {
			return ret;
		}
		return defaultValue;
	}
	
	public static synchronized void setLastSavedCounter(Class<?> key,int counter) {
		lastSavedCounter.put(key, counter);
	}
}
