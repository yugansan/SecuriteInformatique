import java.io.*;
import java.util.*;
import java.util.Scanner;

public class DecryptageVigenere extends decrypt {

  Scanner sc = new Scanner(System.in);
  Vigenere vigenere = new Vigenere();
  String ficAdecrypter, texteDecrypte;
  String cleChiffrage;
  String[] dictionnaire = Dictionnaire("lexique.php");

  public void decryptage_vigenere(String[] args, String filename) {
    try {
      if (args.length == 2) {
        decrypt_sans_cle(filename);
      }
      else if (args.length == 3){
        int taille = Integer.parseInt(args[3]);
        decrypt_avec_cle(filename, taille);
      }
      else {
        System.out.print("\nErreur! Commande invalide !");
        menu_vigenere(args, filename);
      }
    } catch (Exception e) {
      System.out.println("\nErreur! Veuillez refaire votre choix !");
      menu_vigenere(args, filename);
    }
  }//fin decryptage_vigenere

  public void menu_vigenere(String[] args, String filename) {
    try {
      System.out.println("Decryptage de Vigenere avec/sans taille du mot clef ?");
      System.out.println("1. Avec taille du mot clef");
      System.out.println("2. Sans taille du mot clef");
      System.out.print("Choisissez une méthode : ");
      boolean pasDeChoix = true;
      while (pasDeChoix){
        int choix = sc.nextInt();
        if (choix==1) {
          pasDeChoix = false;
          System.out.print("Donner la taille du mot clef : ");
          int taille = sc.nextInt();
          decrypt_avec_cle(filename, taille);
        } else if (choix==2) {
          pasDeChoix = false;
          decrypt_sans_cle(filename);
        } else {
          System.out.print("Erreur! Choix de la methode invalide !\nVeuillez entrer un chiffre entre 1 ou 2.\nChoisissez une méthode : ");
        }
      }
    } catch (Exception e) {
      System.out.println("\nErreur! Veuillez entrer un chiffre!");
      menu_vigenere(args, filename);
    }
  }//fin menu_vigenere

  public void decrypt_avec_cle(String filename, int tailleMotCle) {
    ficAdecrypter = fichier_en_str(fichierClair(filename));
    try {
      int cpt = 0;

      /* extraire du dictionnaire toutes les mots de meme taille que tailleMotCle */
      ArrayList<String> motsClefPossibles = new ArrayList<String>();
      for (int k = 0; k<dictionnaire.length; k++) {
        if (dictionnaire[k].length() == tailleMotCle) {
          motsClefPossibles.add(dictionnaire[k]);
          System.out.println("motsClefPossibles : "+motsClefPossibles.get(cpt));
          cpt++;
        }
      }

      /* decrypter le fichier avec les motsClefPossibles */
      boolean motCleTrouve = false;
      cpt = 0;
      int nbMotsCorrects=0;
      while(!motCleTrouve && cpt<motsClefPossibles.size()) {
        nbMotsCorrects=0;
        cleChiffrage = motsClefPossibles.get(cpt);
        System.out.println("cle actuel : "+cleChiffrage +" cpt : "+ cpt+" taillemax : "+ motsClefPossibles.size());
        texteDecrypte = vigenere.dechiffrer_str(ficAdecrypter, cleChiffrage);
        /* verifier si cleChiffrage est le bon */
        String[] lignes, mots;
        lignes = texteDecrypte.split("\n");
        for(int i = 0; i<lignes.length; i++) {
          mots = lignes[i].split(" ");
          for(int j = 0; j<mots.length; j++) {
            if (mots[j].length()>3) {
              for (int k = 0; k<dictionnaire.length; k++) {
                // System.out.println("mot dico : "+dictionnaire[k]+" mots teste : "+mots[j]+"\n");
                if (dictionnaire[k].equals(mots[j])) {
                  if(nbMotsCorrects>3){
                    motCleTrouve=true;
                  }
                  nbMotsCorrects++;
                }
              }
            }
          }//fin for_mots
        }//fin for_lignes
        cpt++;
      }// fin while_motCleTrouve

      if (motCleTrouve) {
        texteDecrypte = vigenere.dechiffrer_str(ficAdecrypter, cleChiffrage);
        System.out.println(texteDecrypte);
        System.out.println("cle du chiffrement : "+cleChiffrage);
      }else{
        System.out.println("Mot clef inconnu. Mot clef n'appartient pas au dictionnaire.");
      }

    }catch (Exception e) {
      e.printStackTrace();
    }
  }//fin decrypt

  public void decrypt_sans_cle(String filename) {
    ficAdecrypter = fichier_en_str(fichierClair(filename));
    try {
      int cpt = 0;

      /* decrypter le fichier avec les mots du dictionnaire */
      boolean motCleTrouve = false;
      cpt = 0;
      int nbMotsCorrects=0;
      while(!motCleTrouve) {
        nbMotsCorrects=0;
        cleChiffrage = dictionnaire[cpt];
        System.out.println("cle actuel : "+cleChiffrage);
        texteDecrypte = vigenere.dechiffrer_str(ficAdecrypter, cleChiffrage);
        /* verifier si cleChiffrage est le bon */
        String[] lignes, mots;
        lignes = texteDecrypte.split("\n");
        for(int i = 0; i<lignes.length; i++) {
          mots = lignes[i].split(" ");
          for(int j = 0; j<mots.length; j++) {
            if (mots[j].length()>3) {
              for (int k = 0; k<dictionnaire.length; k++) {
                // System.out.println("mot dico : "+dictionnaire[k]+" mots teste : "+mots[j]+"\n");
                if (dictionnaire[k].equals(mots[j])) {
                  if(nbMotsCorrects>3){
                    motCleTrouve=true;
                  }
                  nbMotsCorrects++;
                }
              }
            }
          }//fin for_mots
        }//fin for_lignes
        cpt++;
      }// fin while_motCleTrouve
    }catch (Exception e) {
      e.printStackTrace();
    }

    texteDecrypte = vigenere.dechiffrer_str(ficAdecrypter, cleChiffrage);
    System.out.println(texteDecrypte);
    System.out.println("cle du chiffrement : "+cleChiffrage);

  }//fin decrypt_sans_cle

}
