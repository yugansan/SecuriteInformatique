import java.io.*;

public class decrypt extends SecuriteL3 {

  public static void main (String[] args) {

    if (args.length >= 2 && args.length <= 4) {
      try {

        String typeChiffrage = args[0];
        String filename = args[1];
        //verification si le fichier existe
        FileReader aDecrypter = new FileReader(new File(filename));

        if (typeChiffrage.equals("c") || typeChiffrage.equals("C")){
          System.out.println("Chiffre de Cesar > Decryptage :");
          DecryptageCesar decryptage = new DecryptageCesar();
          decryptage.decryptage_cesar(args, filename);
        }else if (typeChiffrage.equals("p") || typeChiffrage.equals("P")){
          System.out.println("Chiffre par permutation > Decryptage : ");
          // a faire
        }else if (typeChiffrage.equals("v") || typeChiffrage.equals("V")){
          System.out.println("Chiffre de Vigenere > Decryptage : ");
          DecryptageVigenere decryptage = new DecryptageVigenere();
          decryptage.decryptage_vigenere(args, filename);
        }else{
          System.out.println("Erreur! Type de chiffrage invalide !");
          System.out.println("c : chiffre de Cesar\np : chiffre par permutation\nv : chiffre de Vigenere");
        }

      } catch (FileNotFoundException e) {
        System.out.println("Erreur! Fichier a decrypter inexistant !");
      }

    }else {
      System.out.println("\nErreur! Au moins deux arguments requis !\n");
    }

  }//fin main()

}
