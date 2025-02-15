package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	Graphics2D buffer;

	private final int delay = 70;

	private final Timer timer;

	private int numer = 0;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
//Program dodaje figury w sekwencji kwadrat-elipsa-trojkat
	void addFig() {
		switch(numer++%3) {
			case 0: {
				Figura fig = new Kwadrat(buffer, delay, getWidth(), getHeight());
				timer.addActionListener(fig);
				new Thread(fig).start();
				break;
			}
			case 1: {
				Figura fig = new Elipsa(buffer, delay, getWidth(), getHeight());
				timer.addActionListener(fig);
				new Thread(fig).start();
				break;
			}
			case 2: {
				Figura fig = new Trojkat(buffer, delay, getWidth(), getHeight());
				timer.addActionListener(fig);
				new Thread(fig).start();
				break;
			}
		}

	}

	void animate() {
		if (timer.isRunning()) timer.stop();
		else timer.start();
	}
	void colorSet(int red, int green, int blue) {
		KwadratKolor fig =  new KwadratKolor(buffer, delay, getWidth(), getHeight(), red, green, blue);
		timer.addActionListener(fig);
		new Thread(fig).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
	}


}
