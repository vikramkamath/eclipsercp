package org.rcpmail.favorites.service;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.rcpmail.favorites.controller.FavoritesManager;

public class FavoritesViewContentProvider implements IStructuredContentProvider{
	private static FavoritesManager manager;
	private static TableViewer tableViewer;

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		tableViewer = (TableViewer) viewer;
		if (null != newInput && newInput instanceof FavoritesManager){
			manager = (FavoritesManager) newInput;
		}
		if (null != manager && null != tableViewer){
			Object[] elements = manager.getFavorites();
			/*for (Object element: elements){
				System.out.println("element:"+element.toString());
			}*/
			tableViewer.add(elements);
			
		}
		tableViewer.getTable().setRedraw(true);
	}

	public Object[] getElements(Object inputElement) {
		return manager.getFavorites();
	}
}
