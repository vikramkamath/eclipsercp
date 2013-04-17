package org.rcpmail;


import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ControlFinder;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTabItem;
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelpMenuTest {

	private static SWTWorkbenchBot	bot	= new SWTWorkbenchBot();
	
	/*
	 * You can write your own finders for example, to find the buttons and their properties 
	 * on any given widget. Here is one such implementation.
	 * 
	 * You can reuse this code for checking other widgets!!
	 * 
	 */
	private List<SWTBotButton> buttons(){
		final List<SWTBotButton> swtButtonsList = new ArrayList<SWTBotButton>(); 
		return UIThreadRunnable.syncExec(new Result<List<SWTBotButton>>() {
			public List<SWTBotButton> run() {
				// TODO Auto-generated method stub
				ControlFinder cFinder = new ControlFinder();
				Matcher<? extends Button> buttonMatcher = allOf(widgetOfType(Button.class));
				List<? extends Button> buttonList = (List<? extends Button>) cFinder.findControls(buttonMatcher);
				//System.out.println("Button list:");
				for (Button button: buttonList){
					//System.out.println(button.getText());
					swtButtonsList.add(new SWTBotButton(button));
				}
				return swtButtonsList;
			}
		});		
	}
	
	@BeforeClass
	public static void beforeClass(){
		try{			
			bot.shell("Subclipse Usage").activate();
			bot.button("Cancel").click();
			//bot.shell("").activate();
		}catch(WidgetNotFoundException wnfe){
			wnfe.printStackTrace();
		}
	}
	
	@Test
	public void testHelpMenuOpenAboutClickOk(){
		/* 
		 * click on Help --> About RCP Product 
		 */
		bot.menu("Help").menu("About RCP Product").click();
		/*
		 * Check if the shell was RCP Product
		 */
		assertTrue(bot.activeShell().getText().equals("About RCP Product"));
		/*
		 * Click Ok button on this shell
		 */
		bot.button("OK").click();
		/*
		 * Check after disposing the "About RCP Product", 
		 * "RCP Product becomes active Shell again
		 */
		assertTrue(bot.activeShell().getText().equals("RCP Product"));		
	}
	
	
	@Test
	public void testHelpMenuOpenAboutClickInstallationDetails(){
		/* 
		 * click on Help --> About RCP Product 
		 */
		bot.menu("Help").menu("About RCP Product").click();
		/*
		 * Check if the shell was RCP Product
		 */
		assertTrue(bot.activeShell().getText().equals("About RCP Product"));
		/*
		 * Click Ok button on this shell
		 */
		bot.button("Installation Details").click();
		
		/*
		 * Check if the default view was "Plug-ins"
		 */
		assertEquals("RCP Product Installation Details",bot.activeShell().getText());
		
		/*
		 * get the Class of Focused widget - 
		 * Use this snippet to figure out Class of Control.
		 */
		/*Control control = bot.getFocusedWidget();
		System.out.println("Control:"+control.getClass());*/
		
	
		SWTBotTabItem plugInsTabItem = bot.tabItem("Plug-ins");
		plugInsTabItem.activate();
		/*
		 * Ensure 4 buttons on this Tab
		 */
		List<SWTBotButton> pluginsButtons = buttons();
		assertEquals(4,pluginsButtons.size());		
		
		
		/*
		 * Check for buttons and their properties: Enabled and Visible
		 * NOTE buttons with Shortcuts will have '&' prefixed to 
		 * the shortcut. For example C<u>o</u>lumns will be represented
		 * as C&olumns
		 */
		SWTBotButton columnsButton = bot.button("C&olumns...");
		assertEquals("C&olumns...",columnsButton.getText());
		/*
		 * We know the button is visible and enabled. Check! 	
		 */
		assertTrue(columnsButton.isVisible() && columnsButton.isEnabled());
	}	
	
	
}
