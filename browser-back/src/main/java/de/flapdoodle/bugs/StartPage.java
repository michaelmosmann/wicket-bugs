package de.flapdoodle.bugs;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class StartPage extends WebPage {

	public StartPage() {
		add(new BookmarkablePageLink<PageA>("a", PageA.class));
		add(new BookmarkablePageLink<PageA>("b", PageB.class));
	}
}
