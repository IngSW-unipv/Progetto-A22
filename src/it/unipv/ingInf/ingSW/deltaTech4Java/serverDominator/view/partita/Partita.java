package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.FinePartitaObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.IDrawable;

public class Partita extends PartitaStage {
	private IDrawable drawable;
	private FinePartitaObserver fineObserver;
	private MainDefinitivo mainDefinitivo;
	
	public Partita(MainDefinitivo main, Nodo baseUtente, int durataPartitaSeconds, FinePartitaObserver fineObserver) {
		super(main, baseUtente, durataPartitaSeconds);
		this.drawable=fineObserver;
		this.fineObserver=fineObserver;
		this.mainDefinitivo=main;
		this.initDrowableFinePartita();
	}
	
	public Partita(MainDefinitivo main, Nodo baseUtente, int durataPartitaSeconds) {
		super(main, baseUtente, durataPartitaSeconds);
		this.drawable=fineObserver;
		this.mainDefinitivo=main;
		
	}
	@Override
	public void doOnClic() {
		boolean samePlayer = this.getSelectedBase().getPossessore().getNome().equals(this.getSelectedNode().getPossessore().getNome());
		//this.setButtonsVisibilityInActionPane(true, samePlayer, samePlayer); 
		super.getActionPane().getDevelop().setDisable(!samePlayer);
		super.getActionPane().getPowerUpL().setDisable(!samePlayer);
		
		boolean precedenteBase=mainDefinitivo.getTabellone().getScelta()>0? true:false;
		super.getBaseStatsPane().getButtonBack().setDisable(!precedenteBase);

		mainDefinitivo.getTabellone().checkbasi(super.getSelectedBase().getPossessore());
		boolean prossimaBase=mainDefinitivo.getTabellone().getContabasi()-mainDefinitivo.getTabellone().getScelta()<=0? false:true;
		super.getBaseStatsPane().getButtonNext().setDisable(!prossimaBase);
		
		boolean attaccabile=mainDefinitivo.nodecheck(this.getSelectedBase().getPossessore(),super.getSelectedPoint().getIntX(),
				super.getSelectedPoint().getIntY());
		super.getStatsNodePane().getButtonAttacca().setDisable(!attaccabile);

	}

	@Override
	public void fineGioco() {
		fineObserver.finePartita();

	}
	
	public void initDrowableFinePartita() {
		super.getFineProgress().addDrawable(drawable);

	}

	public IDrawable getDrawable() {
		return drawable;
	}

	public void setDrawable(IDrawable drawable) {
		this.drawable = drawable;
	}

	public FinePartitaObserver getFineObserver() {
		return fineObserver;
	}

	public void setFineObserver(FinePartitaObserver fineObserver) {
		this.fineObserver = fineObserver;
		initDrowableFinePartita();
	}

	public MainDefinitivo getMainDefinitivo() {
		return mainDefinitivo;
	}

	public void setMainDefinitivo(MainDefinitivo mainDefinitivo) {
		this.mainDefinitivo = mainDefinitivo;
	}
	
}
