package com.hailm.megaman.view;

import javax.swing.JPanel;

public abstract class BasePanel extends JPanel implements SetUp {
    protected OnPanelStateListener onPanelStateListener;
    public BasePanel() {
        initcomponents();
        addComponents();
        registerListener();
    }
    public void setOnPanelStateListener(
            OnPanelStateListener onPanelStateListener) {
        this.onPanelStateListener = onPanelStateListener;
    }
}
