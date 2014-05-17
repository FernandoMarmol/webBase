package eu.fmm.sw.listeners;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletRequestAttributeListener implements
		ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println("CustomServletRequestAttributeListener - attributeAdded");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println("CustomServletRequestAttributeListener - attributeRemoved");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println("CustomServletRequestAttributeListener - attributeReplaced");
	}

}
