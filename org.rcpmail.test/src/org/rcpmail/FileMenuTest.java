package org.rcpmail;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;

@RunWith(SWTBotJunit4ClassRunner.class)
public class FileMenuTest {
	private static SWTWorkbenchBot	bot	= new SWTWorkbenchBot();
	@BeforeClass
	public static void beforeClass(){
		try{			
			bot.shell("Subclipse Usage").activate();
			bot.button("Cancel").click();
			bot.shell("").activate();
		}catch(WidgetNotFoundException wnfe){
			wnfe.printStackTrace();
		}
	}
		
	@Test
	public void testFileMenuItems(){
		SWTBotMenu fileMenu = bot.menu("File").click();
		fileMenu.menu("Open in New Window").click();
		assertEquals(2,countShells(bot, "").size());
		bot.shell("").activate().close();
		assertEquals(1,countShells(bot, "").size());
	}
	
	private List<Shell> countShells(SWTWorkbenchBot bot, String shellName){
		return bot.shells(shellName);
	}
}
