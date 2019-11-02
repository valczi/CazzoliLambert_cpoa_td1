package controleur;

import java.util.ArrayList;
import daofactory.Daofactory;
import daoobjects.AbonnementDAO;
import daoobjects.ClientDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metiers.ClientM;

public class ClientControleur {

	@FXML
	private TextField edtPays;

	@FXML
	private TextField edtNom;

	@FXML
	private Label lbl_alerte_pays;

	@FXML
	private Label lbl_alerte_ville;

	@FXML
	private Label lbl_alerte_voie;

	@FXML
	private Label lbl_alerte_nom;

	@FXML
	private Label lbl_alerte_prenom;

	@FXML
	private Label lbl_alerte_code;

	@FXML
	private Label lbl_alerte_rue;

	@FXML
	private TextField edtVoie;

	@FXML
	private TextField edtVille;

	@FXML
	private TextField edtPrenom;

	@FXML
	private TextField edtCodeP;

	@FXML
	private TextField edtNoRue;

	@FXML
	private Label lbl_empty;

	@FXML
	private Button btn_creer;

	@FXML
	private RadioButton rdb_Liste;
	@FXML
	private RadioButton rdb_sql;

	@FXML
	private Button btnMod2;

	@FXML
	private Button btnModif;

	@FXML
	private Button btnSuppr;

	@FXML
	private TextField edt_RecNom;

	@FXML
	private TextField edt_RecPrenom;

	@FXML
	private Button btnRec;

	@FXML
	private TableView<ClientM> tableCli;

	@FXML
	private TableColumn<ClientM, String> nomColumn;

	@FXML
	private TableColumn<ClientM, String> paysColumn;

	@FXML
	private TableColumn<ClientM, String> PrenomColumn;

	@FXML
	private TableColumn<ClientM, String> VilleColumn;

	@FXML
	private TableColumn<ClientM, String> VoieColumn;

	@FXML
	private TableColumn<ClientM, Integer> CodeColumn;

	@FXML
	private TableColumn<ClientM, Integer> rueColumn;

	private Daofactory dao;
	private ClientDAO cli;
	private AbonnementDAO abo;
	boolean actif = false;

	@FXML
	public void initialize() {
		this.dao = Daofactory.getDaofactory(daofactory.Persistance.Liste);
		abo = this.dao.getAbonnement();
		cli = this.dao.getClient();
		this.CodeColumn.setCellValueFactory(new PropertyValueFactory<>("code_postal"));
		this.nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		this.PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		this.rueColumn.setCellValueFactory(new PropertyValueFactory<>("no_rue"));
		this.VilleColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
		this.VoieColumn.setCellValueFactory(new PropertyValueFactory<>("voie"));
		this.paysColumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
		this.tableCli.setItems(FXCollections.observableArrayList(cli.tout()));
	}

	@FXML
	public void clear() {
		this.actif = false;
		this.tableCli.setVisible(true);
		this.lbl_empty.setVisible(false);
		this.lbl_alerte_code.setVisible(false);
		this.lbl_alerte_nom.setVisible(false);
		this.lbl_alerte_pays.setVisible(false);
		this.lbl_alerte_prenom.setVisible(false);
		this.lbl_alerte_rue.setVisible(false);
		this.lbl_alerte_ville.setVisible(false);
		this.lbl_alerte_voie.setVisible(false);
		this.tableCli.getSelectionModel().clearSelection();
		this.edt_RecNom.clear();
		this.edt_RecPrenom.clear();
		this.edtPrenom.clear();
		this.edtNom.clear();
		this.edtCodeP.clear();
		this.edtNoRue.clear();
		this.edtPays.clear();
		this.edtVille.clear();
		this.edtVoie.clear();
		abo = this.dao.getAbonnement();
		cli = this.dao.getClient();
		this.tableCli.setItems(FXCollections.observableArrayList(cli.tout()));
		this.tableCli.refresh();

	}

	@FXML
	void Rechercher(ActionEvent event) {
		ArrayList<ClientM> liste = new ArrayList<>();
		if (this.edt_RecNom.getText().isEmpty() && this.edt_RecPrenom.getText().isEmpty()) {
			clear();
		} else {
			String Nom = null;
			String Prenom = null;
			if (!this.edt_RecNom.getText().isEmpty())
				Nom = this.edt_RecNom.getText().trim().toLowerCase();
			if (!this.edt_RecPrenom.getText().isEmpty())
				Prenom = this.edt_RecPrenom.getText().trim().toLowerCase();
			for (ClientM a : cli.tout())

				if (Prenom != null) {

					if (Nom != null) {

						if (a.getNom().toLowerCase().contains(Nom) && a.getPrenom().toLowerCase().contains(Prenom)) {
							liste.add(a);
						}

					} else if (a.getPrenom().toLowerCase().contains(Prenom)) {
						liste.add(a);

					}
				} else if (a.getNom().toLowerCase().contains(Nom))
					liste.add(a);

			this.tableCli.setItems(FXCollections.observableArrayList(liste));
			this.tableCli.refresh();
		}

	}

	@FXML
	void creationCli(ActionEvent event) {
		if (!SomethingEmpty()) {
			if (ChampCorrecte()) {
				this.cli.ajout(new ClientM(this.edtNom.getText().trim(), this.edtPrenom.getText().trim(),
						this.edtNoRue.getText(), this.edtVoie.getText(), this.edtCodeP.getText(),
						this.edtVille.getText(), this.edtPays.getText()));
				clear();
			}
		} else {
			this.lbl_empty.setText("Champ vide");
			this.lbl_empty.setVisible(true);
		}
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
		if (tableCli.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucun élément sélectionné");
			this.lbl_empty.setVisible(true);
		} else if (!actif) {
			ClientM c = this.tableCli.getSelectionModel().getSelectedItem();
			this.tableCli.setVisible(false);
			this.btnMod2.setVisible(true);
			this.btnModif.setVisible(false);
			this.btn_creer.setVisible(false);
			this.actif = true;
			this.edtCodeP.setText(c.getCode_postal());
			this.edtNom.setText(c.getNom());
			this.edtNoRue.setText(c.getNo_rue());
			this.edtPays.setText(c.getPays());
			this.edtPrenom.setText(c.getPrenom());
			this.edtVille.setText(c.getVille());
			this.edtVoie.setText(c.getVoie());
		} else {
			if (!SomethingEmpty()) {
				this.lbl_empty.setVisible(false);
				if (ChampCorrecte()) {
					ClientM c = this.tableCli.getSelectionModel().getSelectedItem();
					c.setNo_rue(this.edtNoRue.getText().trim());
					c.setNom(this.edtNom.getText().trim());
					c.setPays(this.edtPays.getText().trim());
					c.setPrenom(this.edtPrenom.getText().trim());
					c.setVille(this.edtVille.getText().trim());
					c.setVoie(this.edtVoie.getText().trim());
					c.setCode_postal(this.edtCodeP.getText().trim());
					this.cli.modifier(c);
					clear();
				}
			} else {
				this.lbl_empty.setText("Champ vide");
				this.lbl_empty.setVisible(true);
			}
		}

	}

	@FXML
	void supprRev(ActionEvent event) {
		if (tableCli.getSelectionModel().getSelectedIndex() < 0) {
			this.lbl_empty.setText("Aucun élément sélectionné");
			this.lbl_empty.setVisible(true);
		} else {
			ClientM c = this.tableCli.getSelectionModel().getSelectedItem();
			if (abo.cliExist(c.getId())) {
				this.lbl_empty.setText("Impossible de supprimer cet Client");
				this.lbl_empty.setVisible(true);
			} else {
				cli.supprimer(c);
				clear();
			}
		}
	}

	boolean ChampCorrecte() {
		boolean reponse = true;
		if (!nbonly(this.edtCodeP.getText())) {
			this.lbl_alerte_code.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_code.setVisible(false);

		if (!textonly(this.edtNom.getText())) {
			this.lbl_alerte_nom.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_nom.setVisible(false);

		if (!nbonly(this.edtNoRue.getText())) {
			this.lbl_alerte_rue.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_rue.setVisible(false);

		if (!textonly(this.edtPays.getText())) {
			this.lbl_alerte_pays.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_pays.setVisible(false);

		if (!textonly(this.edtPrenom.getText())) {
			this.lbl_alerte_prenom.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_prenom.setVisible(false);

		if (!textonly(this.edtVille.getText())) {
			this.lbl_alerte_ville.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_ville.setVisible(false);

		if (!textonly(this.edtVoie.getText())) {
			this.lbl_alerte_voie.setVisible(true);
			reponse = false;
		} else
			this.lbl_alerte_voie.setVisible(false);

		return reponse;
	}

	boolean SomethingEmpty() {
		return this.edtCodeP.getText().isEmpty() || this.edtNom.getText().isEmpty() || this.edtNoRue.getText().isEmpty()
				|| this.edtPays.getText().isEmpty() || this.edtVille.getText().isEmpty()
				|| this.edtPrenom.getText().isEmpty() || this.edtVoie.getText().isEmpty();
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

	boolean nbonly(String texte) {
		if (!texte.matches("[0-9]+"))
			return false;
		else
			return true;
	}

}