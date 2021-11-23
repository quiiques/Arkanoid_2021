package codigo;

import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;

public class Bola extends GOval{

	int dx = 1; //velocidad eje x
	int dy = 1; //velocidad eje y

	public Bola(double width, double height) {
		super(width, height);

	}

	public Bola(double width, double height, Color c) {
		super(width, height);
		this.setFillColor(c);
		this.setFilled(true);
	}

	public void muevete(Arkanoid ark){
		//rebote con el suelo y rebote con techo
		if(getY()> ark.getHeight()||getY()<0){
			dy = dy*-1;
		}

		//rebote con pared derecha y  rebote con pared izquierda
		if(getX()> ark.getWidth()||getX()<0){
			dx = dx*-1;
		}

		//chequeo el rebote en las esquinas
		if(chequeaColision(getX(), getY(), ark)){// esquina superior izquierda

			if(chequeaColision(getX()+ getWidth(), getY(), ark)){//esquina superior derecha

				if(chequeaColision(getX(), getY()+getHeight(), ark)){// esquina inferior izquierda

					if(chequeaColision(getX()+getWidth(), getY()+getHeight(), ark)){//esquina inferior derecha


					}
				}
			}
		}

		//mueve la bola en la direccion correcta
		move(dx,dy);
	}

	private boolean chequeaColision(double posx, double posy, Arkanoid ark){
		boolean noHaChocado = true;
		GObject auxiliar;

		auxiliar = ark.getElementAt(posx, posy);

		if(auxiliar == ark.miCursor){ //si entra aqui es que choca con ell cursor
			dy = dy*-1;
			noHaChocado = false;
		}else if(auxiliar == null){ //si vale nll es que no hab�a nada ah�

		}else{ //suponemos que es un ladrillo
			ark.remove(auxiliar);
			dy = dy*-1;
			dx = dx*-1;
			noHaChocado = false;
		}

		return noHaChocado;
	}

}