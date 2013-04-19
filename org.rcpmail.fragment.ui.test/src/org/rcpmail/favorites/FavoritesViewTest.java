package org.rcpmail.favorites;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
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
	
	@Test
	public void checkIfTableShowsExpectedColumnsAndProperties(){
		SWTBotTable favoritesTable = bot.table();
		assertNotNull(favoritesTable);
		assertEquals(3,favoritesTable.columnCount());
		List<String> favoritesTableCols = favoritesTable.columns();	
		List<String> expectedList = new ArrayList<String>();
		expectedList.add("Name");
		expectedList.add("Type");
		expectedList.add("Location");
		assertTrue(compareSimpleStringCollections(favoritesTableCols, expectedList));
	}
	
	@Test
	public void checkIfTableShowsExpectedValues(){
		SWTBotTable favoritesTable = bot.table();
	}
	
	/*
	 * Utility to compare 1-D String Object Collections
	 */
	private boolean compareSimpleStringCollections(Collection<String> actualCollection,
												 Collection<String> expectedCollection){
		boolean matching = true;
		for (String expected: expectedCollection){
			if (!expectedCollection.contains(expected)){
				matching = false;
				break;
			}
		}
		return matching;
	}

}
