import java.io.*;
import java.util.Scanner;

public class DecryptageCesar extends decrypt {

  Scanner sc = new Scanner(System.in);
  Cesar cesar = new Cesar();
  String ficAdecrypter, aDecrypter;
  int cleChiffrage = 0;
  String[] dictionnaire = Dictionnaire("lexique.php");

  public void decryptage_cesar(String[] args, String filename) {
    try {
      if (args.length == 2 || (args.length == 3 && args[3].equals("1"))) {
        System.out.println("1. Decryptage basee sur connaissance d'un mot");
        System.out.println("2. Decryptage basee sur les frequences");
        System.out.println("3. Decryptage par force brute");
        System.out.print("Choisissez une méthode : ");
        boolean pasDeChoix = true;
        while (pasDeChoix){
          sc.nextLine(); //vider la ligne avant de lire une nouvelle
          int choix = sc.nextInt();
          if (choix==1) {
            pasDeChoix = false;
            System.out.println("Donner un mot du texte clair : ");
            sc.nextLine(); //vider la ligne avant de lire une nouvelle
            String mot = sc.nextLine();
            motTexteClair(filename, mot);
          } else if (choix==2) {
            pasDeChoix = false;
            frequences(filename);
          } else if (choix==3) {
            pasDeChoix = false;
            forceBrute(filename);
          } else {
            System.out.print("Erreur! Choix de la methode invalide !\nVeuillez entrer un chiffre entre 1 et 3.\nChoisissez une méthode : ");
          }
        }
      }
      else if (args.length == 4 && args[3].equals("1"))  motTexteClair(filename, args[4]);
      else if (args.length == 3 && args[3].equals("2"))  frequences(filename);
      else if (args.length == 3 && args[3].equals("3"))  forceBrute(filename);
      else  System.out.print("\nErreur! Commande invalide !");
    } catch (Exception e) {
      System.out.println("\nErreur! Veuillez entrer un chiffre !");
      decryptage_cesar(args, filename);
    }
  }//fin decryptage_cesar

  public void motTexteClair(String filename, String motIndice) {
    filename = fichierClair(filename);
    ficAdecrypter = fichier_en_str(filename);

    int tailleMotIndice = motIndice.length();
    String[] lignesAdecrypter, motsAdecrypter;
    lignesAdecrypter = ficAdecrypter.split("\n");
    for(int i = 0; i<lignesAdecrypter.length; i++) {
      motsAdecrypter = lignesAdecrypter[i].split(" ");
      for(int j = 0; j<motsAdecrypter.length; j++) {
        if (motsAdecrypter[j].length() == tailleMotIndice) {
          for (int c = 0; c<26; c++) {
            aDecrypter = cesar.dechiffrer_str(motsAdecrypter[j], c);
            if (motIndice.equals(aDecrypter)) cleChiffrage = c;
          }//fin for_cleChiffrage
        }//fin if_meme_taille
      }//fin for_motsAdecrypter
    }//fin for_lignesAdecrypter

    aDecrypter = cesar.dechiffrer_str(ficAdecrypter, cleChiffrage);
    System.out.println(aDecrypter);
    System.out.println("cle du chiffrement : "+cleChiffrage);

  }//fin motTexteClair

  public void frequences(String filename) {
    ficAdecrypter = fichier_en_str(fichierClair(filename));

    char lettreFrequenteFrancaise = 'e'; //lettre la plus frequente en francais
    char lettreFrequenteChiffre;

    /* occurences de chaque lettre de l'alphabet dans le fichier a decrypter */
    int[] nbOccurences = new int[26];
    for (int i = 0; i < 26; i++) {
      nbOccurences[i] = compterOccurrences(ficAdecrypter, (char)(i+97));
      // System.out.println((char)(i+97) + " -> " + nbOccurences[i] + " fois");
    }

    /* lettre la plus frequente dans fichier a decrypter */
    int max = 0, indiceMax = 0; //max = lettre la plus frequente dans int[]nbOccurences
    for (int k = 1; k < nbOccurences.length; k++) {
      if (nbOccurences[k] > max) {
        max = nbOccurences[k];
        indiceMax = k;
      }
    }
    lettreFrequenteChiffre = (char)(indiceMax+97);
    // System.out.println("max : " + max + " indiceMax : " + indiceMax + " lettre : " + lettreFrequenteChiffre);

    /* calcul de la cle de chiffrage */
    cleChiffrage = (int)((lettreFrequenteChiffre-lettreFrequenteFrancaise));
    if (lettreFrequenteChiffre<lettreFrequenteFrancaise) cleChiffrage = (int)(((26+lettreFrequenteChiffre)-lettreFrequenteFrancaise));
    // System.out.println("cle : " + cleChiffrage);

    /* decryptage et affichage */
    aDecrypter = cesar.dechiffrer_str(ficAdecrypter, cleChiffrage);
    System.out.println(aDecrypter);
    System.out.println("cle du chiffrement : "+cleChiffrage);

  }// fin frequences

  public void forceBrute(String filename) {
    filename = fichierClair(filename);
    ficAdecrypter = fichier_en_str(filename);

    String[] lignesAdecrypter, motsAdecrypter;
    lignesAdecrypter = ficAdecrypter.split("\n");
    for(int i = 0; i<lignesAdecrypter.length; i++) {
      motsAdecrypter = lignesAdecrypter[i].split(" ");
      for(int j = 0; j<motsAdecrypter.length; j++) {
        for (int c = 0; c<26; c++) {
          if (motsAdecrypter[j].length()>3) {
            // System.out.println("mot a decryter : "+motsAdecrypter[j]+"\n");
            aDecrypter = cesar.dechiffrer_str(motsAdecrypter[j], c);
            for (int k = 0; k<dictionnaire.length; k++) {
              if (dictionnaire[k].equals(aDecrypter)) {
                // System.out.println("mot decryter : "+aDecrypter+" cle : "+c+"\n");
                cleChiffrage = c;
              }
            }
          }
        }//fin for_cleChiffrage
      }//fin for_motsAdecrypter
    }//fin for_lignesAdecrypter

    aDecrypter = cesar.dechiffrer_str(ficAdecrypter, cleChiffrage);
    System.out.println(aDecrypter);
    System.out.println("cle du chiffrement : "+cleChiffrage);

    // FileReader aDecrypter = new FileReader(new File(filename));
    // BufferedReader br = new BufferedReader(aDecrypter);
    // String ligne, aDecrypter;
    // while ((ligne = br.readligne()) != null) {
    //   // aDecrypter = c.dechiffrer_str(texteClair(ligne), intCleChiffrage);
    //   System.out.println(aDecrypter);
    // }
    // br.close();
  }// fin forceBrute

}
