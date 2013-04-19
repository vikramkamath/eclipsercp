package org.rcpmail.favorites.model;


import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * This Test only checks the types returned by its subject
 */
public class FavoriteItemTypeTest {
	private FavoriteItemType type;
	private static IWorkspace workspace;
	
	@BeforeClass
	public static void beforeClass(){
		 workspace = ResourcesPlugin.getWorkspace();
	}
	
	@Test
	public void getFileTypeTest(){
		IPath filePath = Path.fromPortableString("org.rcpmail/icons/sample.gif");
		IResource resource = workspace.getRoot().getFile(filePath);
		type = FavoriteItemType.getType(resource);
		assertEquals(org.eclipse.core.resources.IFile.class, type.getRunTimeClass());
	}
		
	@Test
	public void getFolderTypeTest(){
		IPath folderPath = Path.fromPortableString("org.rcpmail/icons");
		IResource resource = workspace.getRoot().getFolder(folderPath);
		type = FavoriteItemType.getType(resource);
		assertEquals(org.eclipse.core.resources.IFolder.class, type.getRunTimeClass());
	}
	
	
	@Test
	public void getProjectTypeTest(){
		IResource resource = workspace.getRoot().getProject("org.rcpmail");
		type = FavoriteItemType.getType(resource);
		assertEquals(org.eclipse.core.resources.IProject.class, type.getRunTimeClass());
	}
	
	@Test
	public void getWorkSpaceRootTypeTest(){
		IResource resource = workspace.getRoot();
		type = FavoriteItemType.getType(resource);
		assertEquals(org.eclipse.core.resources.IWorkspaceRoot.class, type.getRunTimeClass());
	}
	
	@Test(expected=ResourceNullException.class)
	public void getTypeforNonExistentResourceType(){
		type = FavoriteItemType.getType(null);
	}	
	
}
