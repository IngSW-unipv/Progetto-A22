package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Software s1,s2,s3;
		s1=new Antivirus(3,4);
		s2=new Antivirus(5,2);
		s3=new Rootcrash(2,1);
		
		System.out.println(s1.getVal_def());
		System.out.println(s2.getVal_def());
		System.out.println(s3.getVal_atk());
		
		System.out.println(s2.getVal_atk());
	}

}
