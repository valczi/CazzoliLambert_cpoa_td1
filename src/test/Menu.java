package test;

import java.util.Scanner;

import objmetiers.Abonnement;
import objmetiers.Client;
import objmetiers.Periodicite;
import objmetiers.Revue;

public class Menu {

	public static void main(String[] args) {
		Abonnement abo = new Abonnement();
		Client cl=new Client();
		Revue rev=new Revue();
		Periodicite per=new Periodicite();
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
			dedans = sc.nextInt();
			
		}
	
		
	}

	public static void menuAbo(Abonnement m) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = sc.nextInt();
		System.out.println("id de l'abonnement");
		int idabo = sc.nextInt();
		System.out.println("id revue");
		int idrev = sc.nextInt();
		if (choix == 1) {
			System.out.println("date debut");
			String datedeb = sc.next();
			System.out.println("date fin");
			String datefin = sc.next();
			m.AjoutAbo(idabo, idrev, datedeb, datefin);
		} else if (choix == 2) {
			System.out.println("Que voulez vous modifier(date debut 1,date fin 2)");
			choix = sc.nextInt();

			System.out.println("Donnez la date :");
			String date = sc.next();
			if (choix == 1) {
				m.ModifAboDeb(idabo, idrev, date);
			} else {
				m.ModifAboFin(idabo, idrev, date);
			}

		} else if (choix == 3) {
			m.SupprAbo(idabo, idrev);
		}
	

	}

	public static void menuPer(Periodicite m) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = sc.nextInt();
		System.out.println("id de la periode");
		int id = sc.nextInt();
		if (choix == 1) {
			System.out.println("Libelle");
			String libelle = sc.next();
			m.AjoutPer(id, libelle);
		} else if (choix == 2) {
			System.out.println("Donnez le nouveau libelle :");
			String libelle = sc.next();
			m.ModifPer(id, libelle);
		} else if (choix == 3) {
			m.SupprPer(id);
		}
		

	}

	public static void menuClient(Client m) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = sc.nextInt();
		System.out.println("id du Client");
		int id = sc.nextInt();
		if (choix == 1) {
			System.out.println("Nom");
			sc.nextLine();
			String nom = sc.nextLine();
			System.out.println("Prenom");
			String prenom = sc.nextLine();
			System.out.println("numero rue");
			int norue = sc.nextInt();
			System.out.println("voie");
			sc.nextLine();
			String voie = sc.nextLine();
			System.out.println("code_postal");
			int codep = sc.nextInt();
			System.out.println("ville");
			sc.nextLine();
			String ville = sc.next();
			System.out.println("pays");
			sc.nextLine();
			String pays = sc.nextLine();

			m.AjoutCl(id, nom, prenom, norue, voie, codep, ville, pays);
		} else if (choix == 2) {
			System.out.println(
					"Que voulez vous modifier(nom 1,prenom 2,numero rue 3,voie 4,code postal 5,ville 6,pays 7)");
			choix = sc.nextInt();
			if (choix == 1) {
				System.out.println("Donnez le nouveau nom :");
				String nom = sc.next();
				m.ModifClNom(id, nom);
			} else if (choix == 2) {
				System.out.println("Donnez le nouveau prenom :");
				String prenom = sc.next();
				m.ModifClPrenom(id, prenom);
			} else if (choix == 3) {
				System.out.println("Donnez la nouvelle rue :");
				int rue = sc.nextInt();
				m.ModifClRue(id, rue);
			} else if (choix == 4) {
				System.out.println("Donnez la nouvelle voie :");
				String voie = sc.next();
				m.ModifClVoie(id, voie);
			} else if (choix == 5) {
				System.out.println("Donnez le nouveau code postal :");
				int codep = sc.nextInt();
				m.ModifClCode(id, codep);
			} else if (choix == 6) {
				System.out.println("Donnez la nouvelle ville :");
				String ville = sc.next();
				m.ModifClVille(id, ville);
			} else if (choix == 7) {
				System.out.println("Donnez le nouveau pays :");
				String pays = sc.next();
				m.ModifClPays(id, pays);
			}

		} else if (choix == 3) {
			m.SupprCl(id);
		}
		

	}

	public static void menuRevue(Revue m) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choix;
		System.out.println("Que voulez vous faire ? (Ajouter 1,Modifier 2,Supprimer 3)");
		choix = sc.nextInt();
		System.out.println("id de la revue");
		int id = sc.nextInt();
		sc.nextLine();
		if (choix == 1) {
			System.out.println("titre");
			String titre = sc.next();
			sc.nextLine();
			System.out.println("description	");
			String desc = sc.nextLine();
			System.out.println("tarif");
			double tarif = sc.nextDouble();
			sc.nextLine();
			System.out.println("visuel");
			String visuel = sc.next();
			sc.nextLine();
			System.out.println("id periode");
			int idp = sc.nextInt();

			m.AjoutRev(id, titre, desc, tarif, visuel, idp);
		} else if (choix == 2) {
			System.out.println("Que voulez vous modifier(titre 1,description 2,tarif 3,visuel 4,periode 5)");
			choix = sc.nextInt();
			if (choix == 1) {
				System.out.println("Donnez le nouveau titre :");
				String titre = sc.next();
				m.ModifRevTitre(id, titre);
			} else if (choix == 2) {
				System.out.println("Donnez la nouvelle Description :");
				String desc = sc.next();
				m.ModifRevDesc(id, desc);
			} else if (choix == 3) {
				System.out.println("Donnez le nouveau tarif :");
				double tarif = sc.nextDouble();
				m.ModifRevTarif(id, tarif);
			} else if (choix == 4) {
				System.out.println("Donnez le nouveau visuel :");
				String visu = sc.next();
				m.ModifRevVisuel(id, visu);
			} else if (choix == 5) {
				System.out.println("Donnez la nouvelle periode :");
				int idp = sc.nextInt();
				m.ModifRevPerio(id, idp);
			}

		} else if (choix == 3) {
			m.SupprRev(id);
		}
		

	}

	}


