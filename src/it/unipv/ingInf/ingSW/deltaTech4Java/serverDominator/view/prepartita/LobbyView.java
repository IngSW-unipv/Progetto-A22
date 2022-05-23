package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import java.util.ArrayList;
import java.util.Collections;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObPunteggio;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBObiettiviDOAFactory;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ClassificaPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.IDrawable;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LobbyView extends Stage implements IDrawable {
	private UserAccount userAccount;
	private int[] selectedDifecolta=new int[2];
	private Button avvioPartita;
	private MenuBar menu;
	
	private ToggleGroup group = new ToggleGroup();
	private ArrayList<RadioButton> radios;

	private HBox userInfo;
	private GridPane obiettivi;
	
	private Label titolo;
	
	
	public LobbyView(UserAccount userAccount) {
		super();
		super.setMinHeight(200);
		super.setMinWidth(300);
		this.radios=new ArrayList<RadioButton>();
		this.initTitolo();
		this.menu=new MenuBar();
		this.userAccount = userAccount;
		this.selectedDifecolta[0]=30;
		this.selectedDifecolta[1]=20;
		this.avvioPartita=ComponentCreator.getIstance().createButton("AvvioPartita", Pos.CENTER);
		this.userInfo=this.infoUserMaker();
		this.group=new ToggleGroup();
		this.makeSelezioneDifficolta();
		this.obiettivi=obiettiviGiocatoreMaker();
		this.disponi();
	}
	
	/**
	 * Metodo che dispone nello stage della lobby gli elementi radio buttons, classifica, obbiettivi, pulsante di avvio partita
	 * nelle rispettive posizioni
	 */
	
	public void disponi() {
		ScrollPane sp=new ScrollPane();
		
		GridPane borderPane=new GridPane();
		VBox vbox=new VBox();
		vbox.setSpacing(10);
		userInfo.setAlignment(Pos.CENTER);
		ScrollPane obiettivi=new ScrollPane();
		obiettivi.setContent(this.obiettivi);
		GridPane seeltaDifficol=new GridPane();
		seeltaDifficol.setBackground(new Background(new BackgroundFill(Color.web("#DCDCDC"), new CornerRadii(10), null)));
		
		seeltaDifficol.addRow(0, titolo);
		for(int i=0; i<radios.size();i++)
			seeltaDifficol.addRow(i+1, radios.get(i));
		seeltaDifficol.addRow(radios.size()+1, avvioPartita);
		GridPane center=new GridPane();
		center.addColumn(0, obiettivi);
		HBox space=new HBox();
		space.setMinWidth(20);
		center.addColumn(1, space);
		center.addColumn(2, seeltaDifficol);
		center.setAlignment(Pos.CENTER);
		borderPane.addColumn(1,center);
		borderPane.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(menu,userInfo,borderPane);
		sp.setContent(vbox);
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		super.setScene(new Scene(sp));
		super.show();
		vbox.setBackground(new Background(new BackgroundFill(Color.web("#6BC723"), new CornerRadii(10), null)));
		vbox.setAlignment(Pos.TOP_CENTER);
	}
	
	/**
	 * Sezione della lobby contenente il titolo del gioco
	 */
	
	private void initTitolo() {
		titolo=new Label();
		titolo.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		titolo.setTextFill(Color.DARKGREEN);
		titolo.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), null)));
	}
	
	/**
	 * Sezione di Prebattaglia che permette la scelta della difficolta della partita tramite radio buttons
	 */
	private void makeSelezioneDifficolta() {
		RadioButton facile = new RadioButton("FACILE");
		 facile.setOnAction(event->{
			 selectedDifecolta[0]=15;
			 selectedDifecolta[1]=10;
		 });
		 facile.setToggleGroup(group);
		 
		 RadioButton medio = new RadioButton("MEDIO");
		 medio.setToggleGroup(group);
		 medio.setOnAction(event->{
			 selectedDifecolta[0]=20;
			 selectedDifecolta[1]=15;
		 });
		 RadioButton difficile = new RadioButton("DIFFICILE");
		 difficile.setToggleGroup(group);
		 difficile.setOnAction(event->{
			 selectedDifecolta[0]=30;
			 selectedDifecolta[1]=20;
		 });
		 difficile.setSelected(true);
		 radios.add(difficile);
		 radios.add(medio);
		 radios.add(facile);
	}
	
	/**
	 * Tabella con informazioni da partite precedenti nel server dominator con punteggi rispettivi dei giocatori
	 * 
	 */
	private HBox infoUserMaker() {
		HBox b=new HBox();
		Label punteggio=new Label("Punteggio");
		HBox.setHgrow(punteggio, Priority.ALWAYS);
		punteggio.setFont(Font.font("Cambria", 18));
		Label punteggioUser=new Label(String.valueOf(userAccount.getPunteggio()));
		HBox.setHgrow(punteggioUser, Priority.ALWAYS);
		punteggioUser.setFont(Font.font("Cambria", 18));

		
		Label username=new Label("Username");
		HBox.setHgrow(username, Priority.ALWAYS);
		username.setFont(Font.font("Cambria", 18));
		Label usernameUser=new Label(userAccount.getUsername());
		HBox.setHgrow(usernameUser, Priority.ALWAYS);
		usernameUser.setFont(Font.font("Cambria", 18));
		
		Label soldi=new Label("DeltaCoin");
		HBox.setHgrow(soldi, Priority.ALWAYS);
		soldi.setFont(Font.font("Cambria", 18));
		Label soldiUser=new Label(String.valueOf(userAccount.getMny()));
		HBox.setHgrow(soldiUser, Priority.ALWAYS);
		soldiUser.setFont(Font.font("Cambria", 18));
		
		b.getChildren().addAll(punteggio,punteggioUser,soldi,soldiUser,username,usernameUser);
		b.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), null)));
		
		b.setSpacing(10);
		return b;		
	}
	
	/**
	 * Sezione della lobby che illustra gli obbiettivi per vincere una partita di server dominator
	 * 
	 */
	
	private GridPane obiettiviGiocatoreMaker() {
		GridPane grid=new GridPane();
		grid.setBackground(new Background(new BackgroundFill(Color.web("#000000"), new CornerRadii(10), null)));
		grid.setOpacity(0.8);
		final ArrayList<Obiettivo> obiettivi=DBObiettiviDOAFactory.getIObiettiviDAO(new ObPunteggio()).selectAll();
		ArrayList<ObPunteggio> obiettiviPunteggio=new ArrayList<ObPunteggio>();
		for(Obiettivo o:obiettivi) {
			obiettiviPunteggio.add((ObPunteggio)o);
		}
		int i=0;
		Collections.sort(obiettiviPunteggio);
		HBox nomeBox=new HBox();
		nomeBox.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));

		HBox ricompensaBox=new HBox();
		ricompensaBox.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		HBox punteggioBox=new HBox();
		punteggioBox.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));

		nomeBox.getChildren().add(ClassificaPane.lblCreate(Pos.CENTER, TextAlignment.CENTER, "#000000",15, "DESCRIZIONE\t",1));
		ricompensaBox.getChildren().add(ClassificaPane.lblCreate(Pos.BASELINE_LEFT, TextAlignment.CENTER, "#000000",15, "RICOMPENSA \t",1));
		punteggioBox.getChildren().add(ClassificaPane.lblCreate(Pos.CENTER, TextAlignment.CENTER, "#000000",15, "PUNTEGGIO\t",1));
		grid.add(nomeBox, 0, 0);
		grid.add(ricompensaBox, 1, 0);
		grid.add(punteggioBox, 2,0);
		grid.setAlignment(Pos.CENTER);
		
		
		
		for (ObPunteggio o:obiettiviPunteggio) {
			HBox b1=new HBox();
			b1.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			HBox b2=new HBox();
			b2.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			
			b2.setAlignment(Pos.BASELINE_LEFT);
			
			HBox b3=new HBox();
			b3.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			b3.setAlignment(Pos.BASELINE_LEFT);
			
			
			Label nome =ClassificaPane.lblCreate(Pos.BASELINE_LEFT, TextAlignment.RIGHT, "#000000", 
					15, o.getDescrizione(),0.8);

			Label ricompensa =ClassificaPane.lblCreate(Pos.BASELINE_LEFT, TextAlignment.RIGHT, "#000000", 
					20, ""+o.getRicompensa().intValue(),0.8);
			
			Label punteggio =ClassificaPane.lblCreate(Pos.BASELINE_LEFT, TextAlignment.RIGHT, "#000000", 
					15, " "+o.getPunteggioObiettivo(),0.8);
					new Label();
			
			b1.getChildren().add(nome);
			b2.getChildren().add(ricompensa);
			b3.getChildren().add(punteggio);
			grid.add(b1, 0, i+2);
			grid.add(b2, 1, i+2);
			grid.add(b3, 2, i+2);
			grid.setAlignment(Pos.CENTER);
			i++;
		}
		
		return grid;
	}

	/**
	 * Metodo che restituisce account dell'utente
	 * 
	 */
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
		this.userInfo=this.infoUserMaker();
		this.obiettivi=obiettiviGiocatoreMaker();
		this.disponi();
	}

	public int[] getSelectedDifecolta() {
		return selectedDifecolta;
	}

	public void setSelectedDifecolta(int[] selectedDifecolta) {
		this.selectedDifecolta = selectedDifecolta;
	}

	public Button getAvvioPartita() {
		return avvioPartita;
	}

	public void setAvvioPartita(Button attacca) {
		this.avvioPartita = attacca;
	}
	

	public MenuBar getMenu() {
		return menu;
	}

	public void setMenu(MenuBar menu) {
		this.menu = menu;
	}

	public ToggleGroup getGroup() {
		return group;
	}

	public void setGroup(ToggleGroup group) {
		this.group = group;
	}

	public HBox getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(HBox userInfo) {
		this.userInfo = userInfo;
	}

	public GridPane getObiettivi() {
		return obiettivi;
	}

	public void setObiettivi(GridPane obiettivi) {
		this.obiettivi = obiettivi;
	}


	public ArrayList<RadioButton> getRadios() {
		return radios;
	}

	public void setRadios(ArrayList<RadioButton> radios) {
		this.radios = radios;
	}

	public Label getTitolo() {
		return titolo;
	}

	public void setTitolo(Label titolo) {
		this.titolo = titolo;
	}

	@Override
	public void drow() {
		// TODO Auto-generated method stub
		
	}
	
}
