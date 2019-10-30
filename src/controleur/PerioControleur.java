package controleur;

import daofactory.Daofactory;
import daofactory.Persistance;
import daoobjects.PeriodiciteDAO;
import daoobjects.RevueDAO;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import metiers.PeriodiciteM;
import metiers.RevueM;

public class PerioControleur {

	@FXML
	private TextField edt_libelle;

	@FXML
	private Label lbl_alerte_titre;

	@FXML
	private Label lbl_empty;

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
	private TextField edt_RecLibelle;

	@FXML
	private TableView<PeriodiciteM> tablePerio;

	@FXML
	private TableColumn<PeriodiciteM, String> libelleColumn;

	private Daofactory dao;
	private PeriodiciteDAO perio;
	private RevueDAO rev;
	boolean actif = false;

	@FXML
	private void initialize() {

		this.lbl_alerte_titre.setVisible(false);
		this.lbl_empty.setVisible(false);
		this.dao = Daofactory.getDaofactory(daofactory.Persistance.Liste);
		rev = this.dao.getRevue();
		perio = this.dao.getPeriodicite();
		libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));

/////// Rechercher////////////
		// 0. Initialize the columns.
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<PeriodiciteM> filteredData = new FilteredList<>(FXCollections.observableArrayList(perio.tout()),
				p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		this.edt_RecLibelle.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(RevueM -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare title of every RevueM with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (RevueM.getLibelle().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches
				}
				return false; // Does not match.
			});
		});

		SortedList<PeriodiciteM> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tablePerio.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		tablePerio.setItems(sortedData);
	}

	@FXML
	void clear() {
		this.btnModif.setVisible(true);
		this.btnMod2.setVisible(false);
		this.btn_creer.setVisible(true);
		this.tablePerio.setVisible(true);
		this.lbl_empty.setVisible(false);
		this.lbl_alerte_titre.setVisible(false);
		this.rev = this.dao.getRevue();
		this.perio = this.dao.getPeriodicite();
		this.tablePerio.setItems(FXCollections.observableArrayList(perio.tout()));
		this.tablePerio.getSelectionModel().clearSelection();
		this.tablePerio.refresh();
		this.edt_libelle.clear();
		this.edt_RecLibelle.clear();
		addListener();
	}

	@FXML
	void creationRev(ActionEvent event) {
		if (this.edt_libelle.getText().isEmpty()) {
			this.lbl_empty.setText("Champ vide");
			this.lbl_empty.setVisible(true);
		} else if (textonly(this.edt_libelle.getText())) {
			this.perio.ajout(new PeriodiciteM(this.edt_libelle.getText()));
			clear();
		} else
			this.lbl_alerte_titre.setVisible(true);

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
		if (this.tablePerio.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucune periodicité selectionné");
			this.lbl_empty.setVisible(true);
		} else if (!this.actif) {
			this.actif = true;
			this.edt_libelle.setText(this.tablePerio.getSelectionModel().getSelectedItem().getLibelle());
			this.btnModif.setVisible(false);
			this.btnMod2.setVisible(true);
			this.btn_creer.setVisible(false);
			this.tablePerio.setVisible(false);
		} else {
			if (this.edt_libelle.getText().isEmpty()) {
				this.lbl_empty.setText("Champ vide");
				this.lbl_empty.setVisible(true);
			} else {
				PeriodiciteM p = this.tablePerio.getSelectionModel().getSelectedItem();
				p.setLibelle(this.edt_libelle.getText().trim());
				this.perio.modifier(p);
				clear();
			}
		}

	}

	@FXML
	void supprRev(ActionEvent event) {
		if (this.tablePerio.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucune periodicité selectionné");
			this.lbl_empty.setVisible(true);
		} else if (this.rev.perioExist(this.tablePerio.getSelectionModel().getSelectedItem().getId())) {
			this.lbl_empty.setText("Impossible de supprimet cette periodicité");
			this.lbl_empty.setVisible(true);
		} else {
			this.perio.supprimer(this.tablePerio.getSelectionModel().getSelectedItem());
			clear();
		}
	}

	@FXML
	void addListener() {
		// 0. Initialize the columns.
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<PeriodiciteM> filteredData = new FilteredList<>(FXCollections.observableArrayList(perio.tout()),
				p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		this.edt_RecLibelle.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(RevueM -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare title of every RevueM with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (RevueM.getLibelle().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches
				}
				return false; // Does not match.
			});
		});

		SortedList<PeriodiciteM> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tablePerio.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		tablePerio.setItems(sortedData);
	}

	boolean textonly(String texte) {
		texte = texte.replace(",", "");
		texte = texte.replace("é", "e");
		texte = texte.replace("'", "");
		texte = texte.replace("è", "");

		//

		if (!texte.matches("[a-zA-z\\s]*"))
			return false;
		else
			return true;
	}
}
