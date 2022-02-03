package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Risorse e1,cpu1,r1,f2;
		boolean c1, c2, c3, c4,c5;
		
		e1= new Energia(0);
		cpu1= new Cpu(5);
		r1= new Ram(10);
		f2= new Firewall(10);
		
		System.out.println(cpu1.getE_richiesta());
		c1= e1.potenziamento();
		c2= cpu1.potenziamento();
		c3= r1.potenziamento();
		c4= f2.potenziamento();
		
	}

}
