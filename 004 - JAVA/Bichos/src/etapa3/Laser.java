package etapa3;

public class Laser extends Personaje {
	
	protected static final int LASER_VELOCIDAD = 3;
	
	public Laser(Escenario e) {
		super(e);
		setSpriteNombres( new String[] {"disparo0.gif", "disparo1.gif", "disparo2.gif"});
		setFrameVelocidad(10);
	}
	
	public void actua() {
		super.actua();
		y += LASER_VELOCIDAD;
		if ( y > Escenario.LIMITE ) borra();
	}

}
