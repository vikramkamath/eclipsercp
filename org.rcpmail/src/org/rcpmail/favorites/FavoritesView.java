package org.rcpmail.favorites;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.rcpmail.favorites.controller.FavoritesManager;
import org.rcpmail.favorites.service.FavoritesViewContentProvider;
import org.rcpmail.favorites.service.FavoritesViewLabelProvider;

public class FavoritesView extends ViewPart {
	public static final String ID = "org.rcpmail.favoritesView";
	
	public FavoritesView(){}
	
	private TableColumn typeColumn;
	private TableColumn nameColumn;
	private TableColumn locationColumn;
	
	private TableViewer viewer;
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent,
				SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		final Table table = viewer.getTable();
		
		typeColumn = new TableColumn(table, SWT.LEFT);
		typeColumn.setWidth(18);
		typeColumn.setText("Type");
		
		nameColumn = new TableColumn(table, SWT.LEFT);
		nameColumn.setWidth(200);
		nameColumn.setText("Name");
		
		locationColumn = new TableColumn(table, SWT.LEFT);
		locationColumn.setWidth(450);
		locationColumn.setText("Location");
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		viewer.setContentProvider(new FavoritesViewContentProvider());
		viewer.setLabelProvider(new FavoritesViewLabelProvider());
		viewer.setInput(FavoritesManager.getInstance());
		
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
