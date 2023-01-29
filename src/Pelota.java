

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Objects;

//Movimiento de la pelota-----------------------------------------------------------------------------------------

class Pelota {
	
	public Integer id;

	private static final int ANCHURA = 15;

	private static final int ALTURA = 15;

	private double x = 0;

	private double y = 0;

	private double avanceX = 1;

	private double avanceY = 1;
	
	private Rectangle2D limites;
	private  PanelRebote panelRebote ;
	

	
	public Pelota(Integer id, PanelRebote panelRebote) {
		this.id = id;
		this.limites = panelRebote.getBounds();
		this.panelRebote = panelRebote;
		;
	}

	public void update() {

		x += avanceX;

		y += avanceY;

		if (x < limites.getMinX()) {

			x = limites.getMinX();

			avanceX = -avanceX;
		}

		if (x + ANCHURA >= limites.getMaxX()) {

			x = limites.getMaxX() - ANCHURA;

			avanceX = -avanceX;
		}

		if (y < limites.getMinY()) {

			y = limites.getMinY();

			avanceY = -avanceY;
		}

		if (y + ALTURA >= limites.getMaxY()) {

			y = limites.getMaxY() - ALTURA;

			avanceY = -avanceY;

		}

	}

	// Forma de la pelota en su posici�n inicial
	public Ellipse2D getForma() {

		return new Ellipse2D.Double(x, y, ANCHURA, ALTURA);

	}
	
	public Integer getId() {
		return this.id;
	}

	public void paint ()
	{ this.update();
	  panelRebote.paint(panelRebote.getGraphics());
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelota other = (Pelota) obj;
		return Objects.equals(id, other.id);
	}
	

}
