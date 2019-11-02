package controleur;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import daofactory.Daofactory;
import daoobjects.AbonnementDAO;
import daoobjects.ClientDAO;
import daoobjects.RevueDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import metiers.AbonnementM;
import metiers.ClientM;
import metiers.RevueM;

public class AboControleur {

	@FXML
	private Button btn_creer;

	@FXML
	private RadioButton rdb_Liste;

	@FXML
	private ToggleGroup Persistance;

	@FXML
	private RadioButton rdb_sql;

	@FXML
	private Button btnMod2;

	@FXML
	private Button btnModif;

	@FXML
	private Button btnSuppr;

	@FXML
	private TableView<AbonnementM> tableAbo;

	@FXML
	private CheckBox checkEnCours;

	@FXML
	private TableColumn<AbonnementM, Integer> idclColumn;

	@FXML
	private TableColumn<AbonnementM, Integer> idrevColumn;

	@FXML
	private TableColumn<AbonnementM, Date> datedebColumn;

	@FXML
	private TableColumn<AbonnementM, Date> datefinColumn;

	@FXML
	private Label lbl_alerte_cl;

	@FXML
	private Label lbl_alerte_Rev;

	@FXML
	private Label lbl_alerte_DateD;

	@FXML
	private Label lbl_alerte_DateF;

	@FXML
	private DatePicker dtDateDeb;

	@FXML
	private DatePicker dtDateFin;

	@FXML
	private ComboBox<ClientM> cbbClient;

	@FXML
	private ComboBox<RevueM> cbbRevue;

	@FXML
	private Label lbl_empty;

	private Daofactory dao;
	private RevueDAO rev;
	private ClientDAO cli;
	private AbonnementDAO abo;
	boolean actif = false;

	@FXML
	private void initialize() {
		this.dao = Daofactory.getDaofactory(daofactory.Persistance.Liste);
		this.rev = this.dao.getRevue();
		this.cli = this.dao.getClient();
		this.abo = this.dao.getAbonnement();
		this.cbbClient.setItems(FXCollections.observableArrayList(this.cli.tout()));
		this.cbbRevue.setItems(FXCollections.observableArrayList(this.rev.tout()));
		this.tableAbo.setItems(FXCollections.observableArrayList(abo.tout()));
		this.idclColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
		this.idrevColumn.setCellValueFactory(new PropertyValueFactory<>("id_revue"));
		this.datedebColumn.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
		this.datefinColumn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
	}

	@FXML
	private void clear() {
		actif = false;
		this.rev = this.dao.getRevue();
		this.cli = this.dao.getClient();
		this.abo = this.dao.getAbonnement();
		this.lbl_empty.setVisible(false);
		this.lbl_alerte_DateD.setVisible(false);
		this.lbl_alerte_DateF.setVisible(false);
		this.lbl_alerte_Rev.setVisible(false);
		this.lbl_alerte_Rev.setVisible(false);
		this.btnMod2.setVisible(false);
		this.btn_creer.setVisible(true);
		this.btnModif.setVisible(true);
		this.tableAbo.setVisible(true);
		this.tableAbo.getSelectionModel().clearSelection();
		this.tableAbo.setItems(FXCollections.observableArrayList(abo.tout()));
		this.cbbClient.setItems(FXCollections.observableArrayList(this.cli.tout()));
		this.cbbRevue.setItems(FXCollections.observableArrayList(this.rev.tout()));
		this.cbbClient.getSelectionModel().clearSelection();
		this.cbbRevue.getSelectionModel().clearSelection();
		this.dtDateDeb.setValue(null);
		this.dtDateFin.setValue(null);
		this.tableAbo.refresh();

	}

	@FXML
	void creationAbo(ActionEvent event) {
		if (ChampCorrecte()) {
			AbonnementM a = new AbonnementM(this.cbbClient.getSelectionModel().getSelectedItem().getId(),
					this.cbbRevue.getSelectionModel().getSelectedItem().getId_revue(), this.dtDateDeb.getValue(),
					this.dtDateFin.getValue());
			if (this.abo.getById(a.getId_client(), a.getId_revue()) != null) {
				this.lbl_empty.setText("Abonnement Déjà existant");
				this.lbl_empty.setVisible(true);
			} else {
				this.abo.ajout(a);
				clear();
			}
		}

	}

	@FXML
	boolean ChampCorrecte() {
		boolean reponse = true;
		LocalDate deb = this.dtDateDeb.getValue();
		LocalDate fin = this.dtDateFin.getValue();
		if (this.cbbClient.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_alerte_cl.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_cl.setVisible(false);
		if (this.cbbRevue.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_alerte_Rev.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_Rev.setVisible(false);
		if (deb == null) {
			reponse = false;
			this.lbl_alerte_DateD.setVisible(true);
		} else
			this.lbl_alerte_DateD.setVisible(false);
		if (fin == null) {
			reponse = false;
			this.lbl_alerte_DateF.setVisible(true);
		} else
			this.lbl_alerte_DateF.setVisible(false);
		
		if (deb != null && fin != null)
			if (deb.isAfter(fin)) {
				this.lbl_empty.setText("Date debut superieur a date fin");
				this.lbl_empty.setVisible(true);
				reponse = false;
			} else
				this.lbl_empty.setVisible(false);
		return reponse;
	}

	@FXML
	void goListe() {
		this.dao = Daofactory.getDaofactory(daofactory.Persistance.Liste);
		clear();
	}

	@FXML
	void goSQL() {
		this.dao = Daofactory.getDaofactory(daofactory.Persistance.Mysql);
		clear();
	}

	@FXML
	void modifRev(ActionEvent event) {
		if (this.tableAbo.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucun abonnement selectionné");
			this.lbl_empty.setVisible(true);
		} else if (!actif) {
			AbonnementM abone = this.tableAbo.getSelectionModel().getSelectedItem();
			this.cbbClient.getSelectionModel().select(this.cli.getById(abone.getId_client()));
			this.cbbRevue.getSelectionModel().select(this.rev.getById(abone.getId_revue()));
			this.dtDateDeb.setValue(abone.getDate_debut());
			this.dtDateFin.setValue(abone.getDate_fin());
			actif = true;
			this.btnModif.setVisible(false);
			this.btnMod2.setVisible(true);
			this.btn_creer.setVisible(false);
			this.tableAbo.setVisible(false);
		} else if (actif) {
			AbonnementM abone = this.tableAbo.getSelectionModel().getSelectedItem();
			abone.setId_client(this.cbbClient.getSelectionModel().getSelectedItem().getId());
			abone.setId_revue(this.cbbRevue.getSelectionModel().getSelectedItem().getId_revue());
			abone.setDate_debut(this.dtDateDeb.getValue());
			abone.setDate_fin(this.dtDateFin.getValue());
			this.abo.modifier(abone);
			clear();
		}
	}

	@FXML
	void supprRev(ActionEvent event) {
		if (this.tableAbo.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucun abonnement selectionné");
			this.lbl_empty.setVisible(true);
		} else {
			this.abo.supprimer(this.tableAbo.getSelectionModel().getSelectedItem());
			clear();
		}

	}

	@FXML
	void EnCours() {
		LocalDate mtn = LocalDate.now();
		ArrayList<AbonnementM> liste = new ArrayList<>();
		if (this.checkEnCours.isSelected()) {
			for (AbonnementM a : this.abo.tout())
				if (a.getDate_fin().isAfter(mtn) && a.getDate_debut().isBefore(mtn))
					liste.add(a);
			this.tableAbo.setItems(FXCollections.observableArrayList(liste));
		} else if (!this.checkEnCours.isSelected())
			clear();
	}

}