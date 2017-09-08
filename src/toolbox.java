import zPhys.Controll;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class toolbox extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox drawModeCheckBox;
    private JCheckBox pinModeCheckBox;
    private JButton playPauseButton;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JButton button3;
    private JButton button4;
    private JCheckBox crashModeCheckBox;
    private JSlider slider4;
    private JCheckBox boxDrawCheckBox;
    private JCheckBox circleCheckBox;

    public toolbox() {
        setContentPane(contentPane);
        //setModal(true);
       // getRootPane().setDefaultButton(buttonOK);
        getRootPane().setLocation(600,0);

        boxDrawCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Controll.isDrawBox = boxDrawCheckBox.isSelected();
            }
        });

        crashModeCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Controll.isCrashMode = crashModeCheckBox.isSelected();
            }
        });

        pinModeCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Controll.isPinMode = pinModeCheckBox.isSelected();
            }
        });


        drawModeCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                  Controll.isDrawMode = drawModeCheckBox.isSelected();
            }
        });



        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controll.isPause = !Controll.isPause;
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        getRootPane().invalidate();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
