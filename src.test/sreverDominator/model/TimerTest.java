package sreverDominator.model;

import java.time.Duration;
import java.time.LocalDateTime;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Timer;

public class TimerTest {
	
	public static void  testCountdown() {
		Timer t=new Timer();
		LocalDateTime t0=LocalDateTime.now();
		t.timer(5);
		if(Duration.between(t0, LocalDateTime.now()).toSeconds()==5) {
			System.out.println("1 test passato");
		}else {
			System.out.println("1 test fallito");
		}
		t.countdown(3);
	}
	
	
	public static void main(String[] args) {
		testCountdown();
	}
}
