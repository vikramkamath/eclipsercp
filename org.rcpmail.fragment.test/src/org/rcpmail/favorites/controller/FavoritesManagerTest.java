package org.rcpmail.favorites.controller;

import static org.junit.Assert.*;

import java.util.Collection;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.rcpmail.favorites.controller.FavoritesManager;
import org.rcpmail.favorites.model.IFavoriteItem;

public class FavoritesManagerTest {
	private static FavoritesManager manager;
	private IWorkspaceRoot workSpaceRoot;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		manager = FavoritesManager.getInstance();
		assertNotNull(manager);
	}
	
	@Before
	public void setUp() throws Exception{
		workSpaceRoot = ResourcesPlugin.getWorkspace().getRoot();
	}
	
	@After
	public void tearDown() throws Exception{
		manager.truncateFavorites();
	}
	@Test
	public void testManagerWithDefaults(){
		IFavoriteItem[] favoriteItems = manager.getFavorites();
		//System.out.println(favoriteItems.length);
		assertEquals(1, favoriteItems.length);
	}
		
	@Test
	public void testManagerAddFile(){
		IPath filePath = Path.fromPortableString("org.rcpmail/icons/sample.gif");
		IResource resource = workSpaceRoot.getFile(filePath);
		assertNotNull(resource);
		manager.addFavorite(resource);
		IFavoriteItem[] favorites = manager.getFavorites();		
		assertEquals(1, favorites.length);		
	}
	
	@Test
	public void testManagerAddProject(){
		IPath projectPath = Path.fromPortableString("org.rcpmail");		
		IResource resource = workSpaceRoot.getProject(projectPath.toString());
		assertNotNull(resource);
		manager.addFavorite(resource);
		IFavoriteItem[] favorites = manager.getFavorites();		
		assertEquals(1, favorites.length);		
	}
	
	@Test
	public void testManagerAddFolder(){
		IPath folderPath = Path.fromPortableString("org.rcpmail/icons");
		IResource resource = workSpaceRoot.getFolder(folderPath);
		assertNotNull(resource);
		manager.addFavorite(resource);
		IFavoriteItem[] favorites = manager.getFavorites();		
		assertEquals(1, favorites.length);
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		manager = null;
	}

}
