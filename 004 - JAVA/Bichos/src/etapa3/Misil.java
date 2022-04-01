package etapa3;

public class Misil extends Personaje{
	
	protected static final int VELOCIDAD_MISIL = 10;
	
	public Misil(Escenario e) {
		super(e);
		setSpriteNombres( new String[] {"misil.gif"});
	}
	
	public void actua() {
		super.actua();
		y -= VELOCIDAD_MISIL;
		if ( y < 0) borra();
	}

}
