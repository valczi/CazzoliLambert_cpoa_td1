package controleur;

import java.util.ArrayList;
import java.util.regex.Pattern;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metiers.PeriodiciteM;
import metiers.RevueM;

public class RevueControleur {

	@FXML
	private TextField edt_titre;

	@FXML
	private TextArea edt_desc;

	@FXML
	private TextField edt_tarif;

	@FXML
	private ComboBox<PeriodiciteM> cbb_per;

	@FXML
	private Label lbl_alerte_titre;

	@FXML
	private Label lbl_alerte_desc;

	@FXML
	private RadioButton rdb_Liste;

	@FXML
	private RadioButton rdb_sql;

	@FXML
	private Label lbl_alerte_tarif;

	@FXML
	private TextField edt_RecTitre;

	@FXML
	private TextField edt_RecTarif;

	@FXML
	private Button btnRec;

	@FXML
	private Label lbl_empty;

	@FXML
	private Label lbl_alerte_perio;

	@FXML
	private Button btn_creer;

	@FXML
	private Button btnMod2;

	@FXML
	private Button btnModif;

	@FXML
	private Button btnSuppr;

	@FXML
	private TableView<RevueM> tableRev;

	@FXML
	private TableColumn<RevueM, Integer> titreColumn;

	@FXML
	private TableColumn<RevueM, String> DescColumn;

	@FXML
	private TableColumn<RevueM, Double> tarifColumn;

	@FXML
	private TableColumn<RevueM, Integer> idpColumn;

	private Daofactory dao;
	private PeriodiciteDAO perio;
	private RevueDAO rev;
	boolean actif = false;

	@FXML
	private void initialize() {
		this.dao = Daofactory.getDaofactory(Persistance.Liste);
		rev = this.dao.getRevue();
		perio = this.dao.getPeriodicite();
		this.cbb_per.setItems(FXCollections.observableArrayList(perio.tout()));
		// tableRev.setCellValueFactory(new PropertyValueFactory<>("userName"));
		titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
		DescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		tarifColumn.setCellValueFactory(new PropertyValueFactory<>("tarif_numero"));
		idpColumn.setCellValueFactory(new PropertyValueFactory<>("id_periodicite"));
		tableRev.setItems(FXCollections.observableArrayList(rev.tout()));

	}

	@FXML
	void clear() {

		this.lbl_empty.setVisible(false);
		this.lbl_alerte_desc.setVisible(false);
		this.lbl_alerte_perio.setVisible(false);
		this.lbl_alerte_tarif.setVisible(false);
		this.lbl_alerte_titre.setVisible(false);
		this.cbb_per.getSelectionModel().clearSelection();
		this.tableRev.getSelectionModel().clearSelection();
		this.edt_desc.clear();
		this.edt_tarif.clear();
		this.edt_titre.clear();
		perio = this.dao.getPeriodicite();
		rev = this.dao.getRevue();
		this.cbb_per.setItems(FXCollections.observableArrayList(perio.tout()));
		this.tableRev.setItems(FXCollections.observableArrayList(rev.tout()));
		this.tableRev.refresh();
	}

	@FXML
	void creationRev() {
		RevueM revue = null;
		if (!SomethingEmpty()) {
			if (ChampCorrecte()) {
				revue = new RevueM(this.edt_titre.getText().trim(), this.edt_desc.getText().trim(),
						Double.valueOf(this.edt_tarif.getText().trim()),
						this.cbb_per.getSelectionModel().getSelectedItem().getId());
				dao.getRevue().ajout(revue);
				clear();
			}
		} else {
			this.lbl_empty.setText("Champ vide(s)");
			this.lbl_empty.setVisible(true);
		}
	}

	@FXML
	void goListe() {
		this.dao = Daofactory.getDaofactory(Persistance.Liste);
		clear();
	}

	@FXML
	void goSQL() {
		this.dao = Daofactory.getDaofactory(Persistance.Mysql);
		clear();
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

	// Renvois vrai si il y a un ï¿½lï¿½ment vide
	boolean SomethingEmpty() {
		return this.edt_desc.getText().isEmpty() || this.edt_tarif.getText().isEmpty()
				|| this.edt_titre.getText().isEmpty();
	}

	boolean ChampCorrecte() {
		boolean reponse = true;
		if (!textonly(this.edt_titre.getText())) {
			this.lbl_alerte_titre.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_titre.setVisible(false);

		if (!textonly(this.edt_desc.getText())) {
			this.lbl_alerte_desc.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_desc.setVisible(false);

		// Check Double
		final String Digits = "(\\p{Digit}+)";
		final String HexDigits = "(\\p{XDigit}+)";
		// an exponent is 'e' or 'E' followed by an optionally
		// signed decimal integer.
		final String Exp = "[eE][+-]?" + Digits;
		final String fpRegex = ("[\\x00-\\x20]*" + // Optional leading "whitespace"
				"[+-]?(" + // Optional sign character
				"NaN|" + // "NaN" string
				"Infinity|" + // "Infinity" string
				// Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
				"(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" +

				// . Digits ExponentPart_opt FloatTypeSuffix_opt
				"(\\.(" + Digits + ")(" + Exp + ")?)|" +

				// Hexadecimal strings
				"((" +
				// 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
				"(0[xX]" + HexDigits + "(\\.)?)|" +

				// 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
				"(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

				")[pP][+-]?" + Digits + "))" + "[fFdD]?))" + "[\\x00-\\x20]*");// Optional trailing "whitespace"

		if (!Pattern.matches(fpRegex, this.edt_tarif.getText())) {
			reponse = false;
			this.lbl_alerte_tarif.setVisible(true);
		} else
			this.lbl_alerte_tarif.setVisible(false);

		if (this.cbb_per.getSelectionModel().getSelectedIndex() == -1) {
			this.lbl_alerte_perio.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_perio.setVisible(false);
		return reponse;
	}

	@FXML
	void modifRev(ActionEvent event) {
		if (tableRev.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucun élément sélectionné");
			this.lbl_empty.setVisible(true);
		} else {
			if (!this.actif) {
				this.actif = true;
				btnMod2.setVisible(true);
				btnModif.setVisible(false);
				RevueM revueT = this.tableRev.getSelectionModel().getSelectedItem();
				this.edt_titre.setText(revueT.getTitre());
				this.edt_desc.setText(revueT.getDescription());
				this.edt_tarif.setText(String.valueOf(revueT.getTarif_numero()));
				PeriodiciteM a = perio.getById(revueT.getId_periodicite());
				this.cbb_per.getSelectionModel().select(a);
				this.tableRev.setVisible(false);
			} else {
				if (!SomethingEmpty()) {
					if (ChampCorrecte()) {
						RevueM revu = this.tableRev.getSelectionModel().getSelectedItem();
						revu.setDescription(this.edt_desc.getText().trim());
						revu.setTarif_numero(Double.valueOf(this.edt_tarif.getText().trim()));
						revu.setTitre(this.edt_titre.getText().trim());
						revu.setId_periodicite(this.cbb_per.getSelectionModel().getSelectedItem().getId());
						rev.modifier(revu);
						this.actif = false;
						this.tableRev.setVisible(true);
						clear();
					}
				} else
					this.lbl_empty.setVisible(true);
			}

		}
	}

	@FXML
	void supprRev(ActionEvent event) {
		if (tableRev.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucun élément sélectionné");
			this.lbl_empty.setVisible(true);
		} else {
			RevueM r = this.tableRev.getSelectionModel().getSelectedItem();
			if (dao.getAbonnement().RevExist(r.getId_revue())) {
				this.lbl_empty.setText("Impossible de supprimer cette Revue");
				this.lbl_empty.setVisible(true);
			} else {
				rev.supprimer(r);
				clear();
			}

		}
	}

	@FXML
	void Rechercher(ActionEvent event) {
		ArrayList<RevueM> liste = new ArrayList<>();
		if (this.edt_RecTarif.getText().isEmpty() && this.edt_RecTitre.getText().isEmpty()) {
			this.lbl_empty.setText("Champ recherche vide");
			this.lbl_empty.setVisible(true);
		} else {
			String titre = null;
			Double tarif = null;
			if (!this.edt_RecTitre.getText().isEmpty())
				titre = this.edt_RecTitre.getText().trim();
			if (!this.edt_RecTarif.getText().isEmpty())
				tarif = Double.valueOf(this.edt_RecTarif.getText().trim());
			for (RevueM a : rev.tout())

				if (tarif != null) {

					if (titre != null) {

						if (a.getTitre().contains(titre) && a.getTarif_numero() == tarif) {
							liste.add(a);
						}

					} else if (a.getTarif_numero() <= tarif) {
						liste.add(a);

					}
				} else if (a.getTitre().contains(titre))
					liste.add(a);

			this.tableRev.setItems(FXCollections.observableArrayList(liste));
			this.tableRev.refresh();
		}

	}
}
