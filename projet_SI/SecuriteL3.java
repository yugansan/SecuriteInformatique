import java.io.*;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class SecuriteL3 {

  //lettres majuscules remplacees par les lettres minuscules correspondantes
  public static String texteMinuscule(String texte) {
    return texte == null ? null : texte.toLowerCase();
  }

  //lettres accentuees remplacees par les memes lettres sans accent
  public static String eliminerAccents(String texte) {
    return texte == null ? null : Normalizer.normalize(texte, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
  }

  //ponctuations remplacees par des espaces
  public static String ponctuation(String texte) {
    return texte == null ? null : texte.replaceAll("[\\s\\p{Punct}]"," ");
  }

  //String en param => texte clair
  public static String texteClair(String texte) {
    return texte == null ? null : ponctuation(eliminerAccents(texteMinuscule(texte))).replaceAll("\\W"," ");
  }

  //fichier en param entierement clair
  public static String fichierClair(String filename) {
    File entree = new File(filename);
    String filenameSortie = "./texteClair/fichierClair_"+entree.getName();
    File sortie = new File(filenameSortie);
    try {
      BufferedReader br = new BufferedReader(new FileReader(entree));
      PrintWriter pw = new PrintWriter(new FileWriter(sortie));
      String ligne;
      while ((ligne = br.readLine()) != null) {
        ligne = texteClair(ligne);
        pw.println(ligne);
        pw.flush();
      }
      br.close();
      pw.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nErreur! Fichier inexistant !");
      // e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return filenameSortie;
  }

  //contenu d'un fichier dans un String
  public static String fichier_en_str(String filename) {
    StringWriter sortie = new StringWriter();
    try {
      File file = new File(filename);
      BufferedInputStream entree = new BufferedInputStream(new FileInputStream(file));
      int i;
      while ((i=entree.read()) != -1)
      sortie.write(i);
      sortie.flush();
      sortie.close();
      entree.close();
    } catch (FileNotFoundException e) {
      System.out.println("\nErreur! Fichier inexistant !\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sortie.toString();
  }//fin fichier_en_str

  //nb occurences du char dans le String
  public static int compterOccurrences(String str, char car) {
    int nbOcc = 0;
    for (int i=0 ; i<str.length(); i++) {
      if (str.charAt(i) == car) nbOcc++;
    }
    return nbOcc;
  }

  //generer un dictionnaire a partir du lexique en param
  public static String[] Dictionnaire (String filename) {
    filename = fichierClair(filename);
    String motsDico = fichier_en_str(filename);
    String[] dico = motsDico.split("\n");
    //renommer le fichier en "dictionnaire.txt"
    File file = new File(filename);
    file.renameTo(new File("dictionnaire.txt"));
    return dico;
  }

}
