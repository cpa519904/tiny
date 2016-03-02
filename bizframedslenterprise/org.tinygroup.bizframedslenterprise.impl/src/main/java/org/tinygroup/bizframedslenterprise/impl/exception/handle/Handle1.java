package org.tinygroup.bizframedslenterprise.impl.exception.handle;

import org.tinygroup.event.Event;
import org.tinygroup.exceptionhandler.ExceptionHandler;

public class Handle1 implements ExceptionHandler{

	public void handle(Throwable t, Event event) {
		System.out.println("e:"+t.getMessage());
	}

}
