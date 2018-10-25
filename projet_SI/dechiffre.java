import java.io.*;

public class dechiffre extends SecuriteL3 {

  public static void main (String []args) {

    if (args.length != 3) System.out.println("\nErreur! Trois arguments requis !\n");
    else {

      try {

        String typeChiffrage = args[0];
        String cleChiffrage = args[1];
        File file = new File(args[2]);

        FileReader aDechiffrer = new FileReader(file);
        BufferedReader br = new BufferedReader(aDechiffrer);
        String ligne, msgDecode;

        if (typeChiffrage.equals("c") || typeChiffrage.equals("C")){
          try {
            int intCleChiffrage = Integer.parseInt(cleChiffrage);
            System.out.println("Chiffre de Cesar > Dechiffrement : ");
            Cesar c = new Cesar();
            while ((ligne = br.readLine()) != null) {
              msgDecode = c.dechiffrer_str(texteClair(ligne), intCleChiffrage);
              System.out.println(msgDecode);
            }
          } catch (NumberFormatException e) {
            System.out.println("\nErreur! Cle du chiffrage invalide !\nMerci de donner un entier !");
          }
        }else if (typeChiffrage.equals("p") || typeChiffrage.equals("P")){
          try {
            /* verifier si cle de chiffrage est une suite de nombres */
            String[] cles = cleChiffrage.split(",");
            int intCleChiffrage;
            for(String str:cles) intCleChiffrage = Integer.parseInt(str);

            System.out.println("Chiffre par permutation > Dechiffrement : ");
            Permutation p = new Permutation();
            while ((ligne = br.readLine()) != null) {
              msgDecode = p.dechiffrer_str(texteClair(ligne), cleChiffrage);
              System.out.println(msgDecode);
            }
          } catch (NumberFormatException e) {
            System.out.println("\nErreur! Cle du chiffrage invalide !\nMerci de donner une suite de nombres separe par ',' !\nPar exemple : '1,2,3'.");
          }
        }else if (typeChiffrage.equals("v") || typeChiffrage.equals("V")){
          System.out.println("Chiffre de Vigenere > Dechiffrement : ");
          Vigenere v = new Vigenere();
          while ((ligne = br.readLine()) != null) {
            msgDecode = v.dechiffrer_str(texteClair(ligne), cleChiffrage);
            System.out.println(msgDecode);
          }
        }else{
          System.out.println("Erreur! Type de chiffrage invalide !");
          System.out.println("c : chiffre de Cesar\np : chiffre par permutation\nv : chiffre de Vigenere");
        }

        br.close();

      } catch (FileNotFoundException e) {
        System.out.println("Erreur! Fichier a dechiffrer inexistant !");
      } catch (IOException e) {
        e.printStackTrace();
      }

    }// fin else

  }//fin main()

}
