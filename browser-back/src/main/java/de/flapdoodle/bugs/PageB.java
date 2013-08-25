package de.flapdoodle.bugs;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class PageB extends WebPage {

	private int _started;

	public PageB() {
		this(0);
	}
	
	public PageB(int start) {
		_started=start;
		DB.setLastSavedCounter(getClass(), _started);
		
		IModel<Integer> model=Model.of(start);
		add(new Label("count",model));
		
		add(new AjaxLink<Integer>("link",model) {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				setModelObject(1+getModelObject());
				if (getModelObject()>_started+2) {
					setResponsePage(new PageB(getModelObject()));
				} else {
					target.addComponent(this);
				}
			}
		});
	}
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		int lastSavedCounter = DB.getLastSavedCounter(getClass(),_started);
		if (lastSavedCounter>_started) {
			RequestCycle.get().setResponsePage(new PageB(lastSavedCounter));
			RequestCycle.get().setRedirect(true);
		}
	}

}
