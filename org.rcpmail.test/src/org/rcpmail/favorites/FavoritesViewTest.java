package org.rcpmail.favorites;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;

public class FavoritesViewTest {
	private static SWTWorkbenchBot	bot	= new SWTWorkbenchBot();
	@BeforeClass
	public static void beforeClass(){
		try{			
			bot.shell("Subclipse Usage").activate();
			bot.button("Cancel").click();
		}catch(WidgetNotFoundException wnfe){
			wnfe.printStackTrace();
		}
	}
	
	@Test
	public void checkIfFavoritesViewLoadedAsExpected(){
		SWTBotView favoritesView = bot.viewByTitle("Favorites");
		assertNotNull(favoritesView);
		assertEquals("Favorites",favoritesView.getTitle());
	}

}
