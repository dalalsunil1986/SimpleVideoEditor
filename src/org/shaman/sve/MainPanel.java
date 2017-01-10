/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shaman.sve;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;
import javax.swing.undo.UndoableEditSupport;
import org.shaman.sve.model.Project;
import org.shaman.sve.player.Player;

/**
 *
 * @author Sebastian
 */
public class MainPanel extends javax.swing.JPanel implements PropertyChangeListener {
	private static final Logger LOG = Logger.getLogger(MainPanel.class.getName());
	
	private Project project;
	private Player player;
	private UndoableEditSupport undoSupport;
	private Selections selections;
	
	private FrameTime currentTime;
	
	/**
	 * Creates new form MainPanel
	 */
	public MainPanel() {
		initComponents();
	}

	public void setProject(Project project) {
		this.project = project;
		project.addPropertyChangeListener(this);
		currentTime = new FrameTime(project.getFramerate());
	}
	
	public void setUndoSupport(UndoableEditSupport undoSupport) {
		this.undoSupport = undoSupport;
	}

	public void setSelections(Selections selections) {
		this.selections = selections;
	}

	public void setPlayer(Player player) {
		this.player = player;
		player.addPropertyChangeListener(this);
	}

	private void setPlaying(boolean playing) {
		playButton.setEnabled(!playing);
		timeSpinner.setEnabled(!playing);
		timeSlider.setEnabled(!playing);
	}
	
	/**
	 * Sets the total time in the UI
	 */
	private void setTotalTime(FrameTime msec) {
		LOG.log(Level.INFO, "set total time to {0} ms", msec);
		//check current time
		boolean fireTimeChange = false;
		if (currentTime.compareTo(msec) > 0) {
			currentTime.fromMillis(0);
			fireTimeChange = true;
		}
		
		timeLabel.setText("/ "+msec+" s");
		timeSpinner.setModel(currentTime.getSpinnerModel(new FrameTime(project.getFramerate()).fromMillis(0), msec));
		timeSlider.setMinimum(0);
		timeSlider.setMaximum(msec.toMillis());
		timeSlider.setValue(currentTime.toMillis());
		
		if (fireTimeChange) {
			changeCurrentTime(currentTime);
		}
	}
	
	/**
	 * Sets the current time in the UI
	 */
	private void setCurrentTime(FrameTime msec) {
		if (msec == currentTime) return;
		currentTime = msec;
		timeSpinner.setValue(msec);
		timeSlider.setValue(msec.toMillis());
//		LOG.log(Level.INFO, "set current time to {0} ms", msec);
	}
	
	/**
	 * Sends the current time to the player
	 * @param time 
	 */
	private void changeCurrentTime(FrameTime time) {
		project.setTime(time);
		if (player != null) {
			player.setTime(time);
		}
		LOG.log(Level.INFO, "change current time to {0} ms", time);
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        playButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        timeSpinner = new javax.swing.JSpinner();
        timeLabel = new javax.swing.JLabel();
        timeSlider = new javax.swing.JSlider();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(400, 300));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/shaman/sve/icons/play16.png"))); // NOI18N
        playButton.setToolTipText("Play");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playEvent(evt);
            }
        });

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/shaman/sve/icons/stop16.png"))); // NOI18N
        stopButton.setToolTipText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopEvent(evt);
            }
        });

        jLabel1.setText(" time:");

        timeSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(10)));
        timeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSpinnerChanged(evt);
            }
        });

        timeLabel.setText("/ 0 s");

        timeSlider.setMajorTickSpacing(1000);
        timeSlider.setMinorTickSpacing(10);
        timeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeSliderChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(playButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopButton)
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeSpinner)
                    .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playEvent
		player.setTime(project.getTime());
		player.start();
    }//GEN-LAST:event_playEvent

    private void stopEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopEvent
        if (player.isPlaying()) {
			player.stop();
		} else if (currentTime.toMillis() != 0) {
			FrameTime ft = new FrameTime(project.getFramerate());
			setCurrentTime(ft);
			changeCurrentTime(ft);
		}
    }//GEN-LAST:event_stopEvent

    private void timeSpinnerChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpinnerChanged
        FrameTime ft = (FrameTime) timeSpinner.getValue();
		if (ft.equals(currentTime)) {
			return;
		}
		currentTime = ft;
		timeSlider.setValue(ft.toMillis());
		changeCurrentTime(ft);
    }//GEN-LAST:event_timeSpinnerChanged

    private void timeSliderChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSliderChanged
        int msec = timeSlider.getValue();
		FrameTime ft = new FrameTime(project.getFramerate()).fromMillis(msec);
		if (ft.equals(currentTime)) return;
		currentTime = ft;
		timeSpinner.setValue(ft);
		changeCurrentTime(ft);
    }//GEN-LAST:event_timeSliderChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton playButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JSlider timeSlider;
    private javax.swing.JSpinner timeSpinner;
    // End of variables declaration//GEN-END:variables

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == project) {
			switch (evt.getPropertyName()) {
				case Project.PROP_LENGTH:
					setTotalTime(project.getLength());
					break;
				case Project.PROP_TIME:
					setCurrentTime(project.getTime());
					break;
			}
		} else if (evt.getSource() == player) {
			switch (evt.getPropertyName()) {
				case Player.PROP_PLAYING:
					setPlaying((boolean) evt.getNewValue());
					break;
			}
		}
	}
}
