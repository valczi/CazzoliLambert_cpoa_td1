package controleur;

import java.util.regex.Pattern;
import daofactory.Daofactory;
import daofactory.Persistance;
import daoobjects.PeriodiciteDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	private Label lbl_empty;

	@FXML
	private Label lbl_alerte_perio;

	@FXML
	private Button btn_creer;

	@FXML
	private Label lbl_creation;

	private Daofactory dao;
	private PeriodiciteDAO perio;

	@FXML
	private void initialize() {
		this.dao = Daofactory.getDaofactory(Persistance.Liste);
		perio = this.dao.getPeriodicite();
		this.cbb_per.setItems(FXCollections.observableArrayList(perio.tout()));
	}

	@FXML
	void clear() {
		this.lbl_empty.setVisible(false);
		this.lbl_alerte_desc.setVisible(false);
		this.lbl_alerte_perio.setVisible(false);
		this.lbl_alerte_tarif.setVisible(false);
		this.lbl_alerte_titre.setVisible(false);
		//this.lbl_creation.setText("");
		this.cbb_per.getSelectionModel().clearSelection();
		this.edt_desc.clear();
		this.edt_tarif.clear();
		this.edt_titre.clear();
		perio = this.dao.getPeriodicite();
		this.cbb_per.setItems(FXCollections.observableArrayList(perio.tout()));
	}

	@FXML
	void creationRev() {
		RevueM revue = null;
		if (!SomethingEmpty()) {
			if (ChampCorrecte()) {
				revue = new RevueM(this.edt_titre.getText().trim(), this.edt_desc.getText().trim(),
						Double.valueOf(this.edt_tarif.getText().trim()), this.edt_titre.getText().trim() + ".jpg",
						this.cbb_per.getSelectionModel().getSelectedItem().getId());
				dao.getRevue().ajout(revue);
				this.lbl_creation.setText(revue.affichage());
				clear();
			}
		} else
			this.lbl_empty.setVisible(true);
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
		texte = texte.replace("�", "e");
		texte = texte.replace("�", "o");
		texte = texte.replace("�", "i");
		if (!texte.matches("[a-zA-z\\s]*"))
			return false;
		else
			return true;
	}

	// Renvois vrai si il y a un �l�ment vide
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

		//Check Double
		final String Digits     = "(\\p{Digit}+)";
		final String HexDigits  = "(\\p{XDigit}+)";
		// an exponent is 'e' or 'E' followed by an optionally 
		// signed decimal integer.
		final String Exp        = "[eE][+-]?"+Digits;
		final String fpRegex    =
		    ("[\\x00-\\x20]*"+ // Optional leading "whitespace"
		    "[+-]?(" +         // Optional sign character
		    "NaN|" +           // "NaN" string
		    "Infinity|" +      // "Infinity" string
		    // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
		    "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

		    // . Digits ExponentPart_opt FloatTypeSuffix_opt
		    "(\\.("+Digits+")("+Exp+")?)|"+

		    // Hexadecimal strings
		    "((" +
		    // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
		    "(0[xX]" + HexDigits + "(\\.)?)|" +

		    // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
		    "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

		    ")[pP][+-]?" + Digits + "))" +
		    "[fFdD]?))" +
		    "[\\x00-\\x20]*");// Optional trailing "whitespace"

		if (!Pattern.matches(fpRegex, this.edt_tarif.getText())){
		    reponse=false;
		    this.lbl_alerte_tarif.setVisible(true);}
		else
			this.lbl_alerte_tarif.setVisible(false);
		
		if (this.cbb_per.getSelectionModel().getSelectedIndex() == -1) {
			this.lbl_alerte_perio.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_perio.setVisible(false);
		return reponse;
	}

}
