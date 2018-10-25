public interface Chiffrement<S,T,U> {

  //Methodes chiffre

  char chiffrer_caract(U message, T clef);

  String chiffrer_str(S message, T clef);


  //Methodes dechiffre

  char dechiffrer_caract(U message,T clef);

  String dechiffrer_str(S message,T clef);

}
