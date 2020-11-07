import java.util.Scanner;

interface Validacion{
	Scanner input = new Scanner(System.in);
	
	public static int validacionNatural() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = input.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("entrada no valida, intente de nuevo:");
				input.nextLine();
				err=true;
			}
			if (ret>0) {
				err=false;
			}else {
				System.out.println("solo números naturales");
				err=true;
			}
		}while(err);
		return ret;
	}
}

class Nodo{
	
	private Nodo nodoAnterior;
	private int dato;
	private Nodo nodoSiguiente;
	
	public Nodo(int dato) {
		this.dato = dato;
		nodoAnterior=nodoSiguiente=null;
	}
	public Nodo() {
		nodoAnterior=nodoSiguiente=null;
	}

	public Nodo getNodoAnterior() {
		return nodoAnterior;
	}
	public void setNodoAnterior(Nodo nodoAnterior) {
		this.nodoAnterior = nodoAnterior;
	}
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
	public Nodo getNodoSiguiente() {
		return nodoSiguiente;
	}
	public void setNodoSiguiente(Nodo nodoSiguiente) {
		this.nodoSiguiente = nodoSiguiente;
	}
	
	@Override
	public String toString() {
		return "Nodo [nodoAnterior=" + nodoAnterior + ", dato=" + dato + ", nodoSiguiente=" + nodoSiguiente + "]";
	}
	
}

class ListaDoblementeEnlazada{
	Nodo nodoInicio;
	Nodo nodoFin;
	
	public ListaDoblementeEnlazada() {
		this.nodoInicio = null;
		this.nodoFin = null;
	}

	public boolean listaVacia() {
		return (nodoInicio == null)&&(nodoFin == null);
	}
	
	public void agregarElementoAlInicio(int dato){
		Nodo nuevoNodo=new Nodo(dato);
		if (this.listaVacia()) {
			this.nodoInicio=this.nodoFin=nuevoNodo;
		}else {
			nuevoNodo.setNodoSiguiente(nodoInicio);
			nodoInicio.setNodoAnterior(nuevoNodo);
			this.nodoInicio = nuevoNodo;
		}
	}
	public void mostrarElementos() {
		Nodo nodoActual = nodoInicio;
		while(nodoActual!=null){
			System.out.print("<--["+nodoActual.getDato()+"]-->");
			nodoActual=nodoActual.getNodoSiguiente();
		}
		System.out.println();
	}
	
}

public class PruebaListaDoblementeEnlazada {

	public static void main(String[] args) {
		

	}

}
