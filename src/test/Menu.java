package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import metiers.*;
public class Menu {

	public static void main(String[] args) {
		
	}
		/*AbonnementM abo = new AbonnementM();
		ClientM cl = new ClientM();
		RevueM rev = new RevueM();
		PeriodiciteM per = new PeriodiciteM();
		int dedans = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (dedans == 1) {
			System.out.println("Sur quelle table voulez vous operer ? (Abonnement 1,Client 2,Periodicite 3,Revue 4)");
			int choix = sc.nextInt();
			switch (choix) {
			case 1:
				menuAbo(abo);
				break;
			case 2:
				menuClient(cl);
				break;
			case 3:
				menuPer(per);
				break;
			case 4:
				menuRevue(rev);
				break;
			default:
				System.out.println("Mauvais choix");
				break;
			}

			System.out.println("Voulez vous continuer ?(0 non,1 oui)");
			do {
				dedans = sc.nextInt();
			} while (dedans != 0 && dedans != 1);

		}

	}

	public static void menuAbo(AbonnementM m) {
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = nbonly();
		System.out.println("id de l'abonnement");
		int idabo = nbonly();
		System.out.println("id revue");
		int idrev = nbonly();
		String datedeb;
		String datefin;
		if (choix == 1) {
			do {
				System.out.println("date debut (année/mois/jours)");
				datedeb = dateonly();
				System.out.println("date fin (année/mois/jours)");
				datefin = dateonly();
			} while (!compdate(datedeb, datefin));
			m.AjoutAbo(idabo, idrev, datedeb, datefin);
		} else if (choix == 2) {
			System.out.println("Que voulez vous modifier(date debut 1,date fin 2)");
			choix = nbonly();

			System.out.println("Donnez la date :");
			String date = dateonly();
			if (choix == 1) {
				m.ModifAboDeb(idabo, idrev, date);
			} else {
				m.ModifAboFin(idabo, idrev, date);
			}

		} else if (choix == 3) {
			m.SupprAbo(idabo, idrev);
		}

	}

	private static boolean compdate(String datedeb, String datefin) {
		char a = '/';
		char b = '-';
		datedeb.trim();
		datefin.trim();
		datefin = datefin.replace(a, b);
		datedeb = datedeb.replace(a, b);
		LocalDate d1 = LocalDate.parse(datedeb);
		LocalDate d2 = LocalDate.parse(datefin);
		if (d2.isBefore(d1)) {
			System.out.println("Mauvaises dates");
			return false;
		} else if (d1.getYear() > LocalDate.now().getYear()) {
			System.out.println("Année de début impossible");
			return false;
		} else

			return true;
	}

	public static void menuPer(PeriodiciteM m) {
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = nbonly();
		System.out.println("id de la periode");
		int id = nbonly();
		if (choix == 1) {
			System.out.println("Libelle");
			String libelle = textonly();
			m.AjoutPer(id, libelle);
		} else if (choix == 2) {
			System.out.println("Donnez le nouveau libelle :");
			String libelle = textonly();
			m.ModifPer(id, libelle);
		} else if (choix == 3) {
			m.SupprPer(id);
		}

	}

	public static void menuClient(ClientM m) {
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = nbonly();
		System.out.println("id du Client");
		int id = nbonly();
		if (choix == 1) {
			System.out.println("Nom");
			textonly();
			String nom = textonly();
			System.out.println("Prenom");
			String prenom = textonly();
			System.out.println("numero rue");
			int norue = nbonly();
			System.out.println("voie");
			textonly();
			String voie = textonly();
			System.out.println("code_postal");
			int codep = nbonly();
			System.out.println("ville");
			textonly();
			String ville = textonly();
			System.out.println("pays");
			textonly();
			String pays = textonly();

			m.AjoutCl(id, nom, prenom, norue, voie, codep, ville, pays);
		} else if (choix == 2) {
			System.out.println(
					"Que voulez vous modifier(nom 1,prenom 2,numero rue 3,voie 4,code postal 5,ville 6,pays 7)");
			choix = nbonly();
			if (choix == 1) {
				System.out.println("Donnez le nouveau nom :");
				String nom = textonly();
				m.ModifClNom(id, nom);
			} else if (choix == 2) {
				System.out.println("Donnez le nouveau prenom :");
				String prenom = textonly();
				m.ModifClPrenom(id, prenom);
			} else if (choix == 3) {
				System.out.println("Donnez la nouvelle rue :");
				int rue = nbonly();
				m.ModifClRue(id, rue);
			} else if (choix == 4) {
				System.out.println("Donnez la nouvelle voie :");
				String voie = textonly();
				m.ModifClVoie(id, voie);
			} else if (choix == 5) {
				System.out.println("Donnez le nouveau code postal :");
				int codep = nbonly();
				m.ModifClCode(id, codep);
			} else if (choix == 6) {
				System.out.println("Donnez la nouvelle ville :");
				String ville = textonly();
				m.ModifClVille(id, ville);
			} else if (choix == 7) {
				System.out.println("Donnez le nouveau pays :");
				String pays = textonly();
				m.ModifClPays(id, pays);
			}

		} else if (choix == 3) {
			m.SupprCl(id);
		}

	}

	public static void menuRevue(RevueM m) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = nbonly();
		System.out.println("id de la revue");
		int id = nbonly();
		textonly();
		if (choix == 1) {
			System.out.println("titre");
			String titre = textonly();
			textonly();
			System.out.println("description	");
			String desc = textonly();
			System.out.println("tarif");
			double tarif = sc.nextDouble();
			textonly();
			System.out.println("visuel");
			String visuel = textonly();

			System.out.println("id periode");
			int idp = nbonly();

			m.AjoutRev(id, titre, desc, tarif, visuel, idp);
		} else if (choix == 2) {
			System.out.println("Que voulez vous modifier(titre 1,description 2,tarif 3,visuel 4,periode 5)");
			choix = nbonly();
			if (choix == 1) {
				System.out.println("Donnez le nouveau titre :");
				String titre = textonly();
				m.ModifRevTitre(id, titre);
			} else if (choix == 2) {
				System.out.println("Donnez la nouvelle Description :");
				String desc = textonly();
				m.ModifRevDesc(id, desc);
			} else if (choix == 3) {
				System.out.println("Donnez le nouveau tarif :");
				double tarif = sc.nextDouble();
				m.ModifRevTarif(id, tarif);
			} else if (choix == 4) {
				System.out.println("Donnez le nouveau visuel :");
				String visu = textonly();
				m.ModifRevVisuel(id, visu);
			} else if (choix == 5) {
				System.out.println("Donnez la nouvelle periode :");
				int idp = nbonly();
				m.ModifRevPerio(id, idp);
			}

		} else if (choix == 3) {
			m.SupprRev(id);
		}

	}*/

	public static String textonly() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String txt = new String();
		do {
			txt = sc.nextLine();
			if (!txt.matches("[a-zA-z\\s]*"))
				System.out.println("Uniquement du texte réessayer");
		} while (!txt.matches("[a-zA-z\\s]*"));
		return txt;

	}

	public static String dateonly() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String txt = new String();
		ArrayList<String> ex = new ArrayList<String>();
		String Rendu = new String();
		boolean sortie = false;
		txt.matches(".*\\d.*");
		char a = ' ';
		char b = '/';
		do {
			txt = sc.nextLine();
			Rendu = txt;
			if (!txt.contains("/"))
				System.out.println("Il n'y a pas de /");
			else {
				txt = txt.replace(b, a);
				if (!txt.matches("[0-9 ]+"))
					System.out.println("Uniquement des chiffres entre les /");
				else {
					for (String i : txt.split(" "))
						ex.add(i);
					if (ex.size() > 3)
						System.out.println("Trop de paramètre");
					else if (Integer.parseInt(ex.get(2)) > 31 || Integer.parseInt(ex.get(2)) < 0
							|| Integer.parseInt(ex.get(1)) == 2 && Integer.parseInt(ex.get(2)) > 28)
						System.out.println("jours incorrecte");
					else if (Integer.parseInt(ex.get(1)) > 12 || Integer.parseInt(ex.get(2)) < 0)
						System.out.println("Mois incorrecte");
					else
						sortie = true;
				}
			}
			ex.clear();
		} while (!sortie);
		return Rendu;

	}

	public static int nbonly() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String txt = new String();
		do {
			txt = sc.nextLine();
			if (!txt.matches("[0-9]+"))
				System.out.println("Uniquement des chiffres réessayer");
		} while (!txt.matches("[0-9]+"));
		return Integer.parseInt(txt);

	}

}
