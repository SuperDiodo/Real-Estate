package Dati;
import java.util.Vector;
/**
 * 
 * Questa classe raggruppa un insieme di concessioni in un vettore per rendere più comoda la gestione del data-set.
 *
 */
public class Demanio {
private Vector<Concessione> concessioni;
/**
 * 
 * @param concessioni Vettore di concessioni gestite dal demanio
 */
public Demanio(Vector<Concessione> concessioni) {
	this.concessioni = concessioni;
}

public Vector<Concessione> getConcessioni() {
	return concessioni;
}

public void setConcessioni(Vector<Concessione> concessioni) {
	this.concessioni = concessioni;
}

}
