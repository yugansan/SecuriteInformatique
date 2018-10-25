public class Cesar implements Chiffrement<String,Integer,Character>{

	@Override
	public char chiffrer_caract(Character car, Integer clef) {
		car=Character.toLowerCase(car);
		if(96<car && car<123){
			int carInt=((int)car)-97;
			char code=(char)(((carInt+clef)%26)+97);
			return (code);
		}/*}else if(64<car && car<91){
			int carInt=((int)car)-65;
			char code=(char)(((carInt+clef)%26)+65);
			return (code);
		}
		*/
		return car;
	}

	@Override
	public String chiffrer_str(String str, Integer clef) {
		String msgChiffrer="";
		for (int i=0;i<str.length();i++) {
			msgChiffrer+=chiffrer_caract(str.charAt(i),clef);
		}
		return msgChiffrer;
	}


	@Override
	public char dechiffrer_caract(Character car, Integer clef) {
		car=Character.toLowerCase(car);
		if(96<car && car<123){
			int carInt=((int)car)-97;
			if(carInt<clef){
				int reste=((carInt+97)-clef)%26;
				char code=(char) (123-reste);
				return (code);
			}
			char code=(char)(((carInt-clef)%26)+97);
			return (code);
		}
		/*else if(64<car && car<91){
			int clefInt=((int)clef)-65;
			int carInt=((int)car)-65;
			if(carInt<clef){
				int reste=clef-carInt;
				char code=(char) (91-reste);
				return (code);
			}
			char code=(char)(((carInt-clef)%26)+65);
			return (code);
		}
		*/
		return car;
	}

	@Override
	public String dechiffrer_str(String str, Integer clef) {
		String msgDechiffrer="";
		for (int i=0;i<str.length();i++) {
			msgDechiffrer+=dechiffrer_caract(str.charAt(i),clef);
		}
		return msgDechiffrer;
	}


}
