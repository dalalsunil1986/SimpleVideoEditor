/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shaman.sve;

import com.l2fprod.common.propertysheet.Property;
import com.l2fprod.common.propertysheet.PropertySheet;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.undo.UndoableEditSupport;
import org.shaman.sve.model.Project;
import org.shaman.sve.model.TimelineObject;

/**
 *
 * @author Sebastian
 */
public class PropertyPanel extends javax.swing.JPanel implements PropertyChangeListener {

	private static final Logger LOG = Logger.getLogger(PropertyPanel.class.getName());

	private Project project;
	private UndoableEditSupport undoSupport;
	private Selections selections;
	
	private PropertySheetPanel sheet;
	
	/**
	 * Creates new form PropertyPanel
	 */
	public PropertyPanel() {
		sheet = new PropertySheetPanel();
		initComponents();
		sheet.setMode(PropertySheet.VIEW_AS_CATEGORIES);
		sheet.setDescriptionVisible(true);
		sheet.setSortingCategories(true);
		sheet.setSortingProperties(true);
		sheet.setRestoreToggleStates(true);
		setSelectedObject(null);
	}

	public void setProject(Project project) {
		this.project = project;
		project.addPropertyChangeListener(this);
	}

	public void setUndoSupport(UndoableEditSupport undoSupport) {
		this.undoSupport = undoSupport;
	}

	public void setSelections(Selections selections) {
		this.selections = selections;
		selections.addPropertyChangeListener(this);
	}
	
	public void setSelectedObject(Object obj) {
		LOG.log(Level.INFO, "{0} selected", obj);
		if (obj == null) {
			currentObjectLabel.setText("no object selected");
			sheet.setProperties(new Property[0]);
		} else {
			currentObjectLabel.setText(obj.toString());
			try {
				if (obj instanceof TimelineObject) {
					Property[] px = ((TimelineObject) obj).getPropertySheetProperties();
					sheet.setProperties(px);
				}
				sheet.readFromObject(obj);
			} catch (Exception ex) {
				Logger.getLogger(PropertyPanel.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		}
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        currentObjectLabel = new javax.swing.JLabel();
        mainPanel = sheet;

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(150, 200));

        currentObjectLabel.setText("jLabel1");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentObjectLabel)
                .addContainerGap(154, Short.MAX_VALUE))
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentObjectLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentObjectLabel;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables

	@Override
	public void propertyChange(PropertyChangeEvent pce) {
		if (pce.getSource() == selections) {
			if (pce.getPropertyName() == Selections.PROP_SELECTED_TIMELINE_OBJECT) {
				setSelectedObject(pce.getNewValue());
			}
		}
	}
}
