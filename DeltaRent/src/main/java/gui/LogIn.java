package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.raven.component.PanelCover;
import com.raven.component.PanelLoginAndRegister;

import net.miginfocom.swing.MigLayout;

public class LogIn extends JPanel {
	private static final long serialVersionUID = 1L;
	private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
	private MigLayout layout;
	private PanelCover cover;
	private PanelLoginAndRegister loginAndRegister;
	private boolean isLogin = true;
	private final double addSize = 30;
	private final double coverSize = 40;
	private final double loginSize = 60;
	private JLayeredPane bg;
	private Animator animator;

	public LogIn() {
		initComponents();
		init();
	}

	private void initComponents() {
		bg = new JLayeredPane();
		bg.setBackground(new Color(28, 51, 84));
		bg.setOpaque(true);

		setLayout(new BorderLayout());
		add(bg, BorderLayout.CENTER);
	}

	private void init() {
		layout = new MigLayout("fill, insets 0");
		cover = new PanelCover();
		loginAndRegister = new PanelLoginAndRegister();

		// Add PropertyChangeListener to listen for the "switchToLogin" event
		loginAndRegister.addPropertyChangeListener("switchToLogin", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!animator.isRunning()) {
					animator.start();
				}
			}
		});

		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				double fractionCover;
				double fractionLogin;
				double size = coverSize;
				if (fraction <= 0.5f) {
					size += fraction * addSize;
				} else {
					size += addSize - fraction * addSize;
				}
				if (isLogin) {
					fractionCover = 1f - fraction;
					fractionLogin = fraction;
					if (fraction >= 0.5f) {
						cover.registerRight(fractionCover * 100);
					} else {
						cover.loginRight(fractionLogin * 100);
					}
				} else {
					fractionCover = fraction;
					fractionLogin = 1f - fraction;
					if (fraction <= 0.5f) {
						cover.registerLeft(fraction * 100);
					} else {
						cover.loginLeft((1f - fraction) * 100);
					}
				}
				if (fraction >= 0.5f) {
					loginAndRegister.showRegister(isLogin);
				}
				fractionCover = Double.valueOf(df.format(fractionCover));
				fractionLogin = Double.valueOf(df.format(fractionLogin));
				layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
				layout.setComponentConstraints(loginAndRegister,
						"width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
				bg.revalidate();
			}

			@Override
			public void end() {
				isLogin = !isLogin;
			}
		};
		animator = new Animator(800, target);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);
		animator.setResolution(0); // for smooth animation
		bg.setLayout(layout);
		bg.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
		bg.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%"); // 1al as
																												// 100%
		loginAndRegister.showRegister(!isLogin);
		cover.login(isLogin);
		cover.addEvent(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (!animator.isRunning()) {
					animator.start();
				}
			}
		});
	}
}