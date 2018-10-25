public class Vigenere implements Chiffrement<String,String,Character> {

	@Override
	public char chiffrer_caract(Character car, String cle) {
		int clef=(int)(cle.toLowerCase()).charAt(0);
		car=Character.toLowerCase(car);
		//clef=clef.toLowerCase();
		if(96<car && car<123){
			int clefInt=(clef)-97;
			int carInt=((int)car)-97;
			char code=(char)(((carInt+clefInt)%26)+97);
			return (code);
		}
		/*else if(64<car && car<91){
			int clefInt=((int)clef)-65;
			int carInt=((int)car)-65;
			char code=(char)(((carInt+clefInt)%26)+65);
			return (code);
		}
		*/
		return car;
	}

	@Override
	public String chiffrer_str(String str, String clef) {
		String msgChiffrer="";
		int j=0;
		for (int i=0;i<str.length();i++) {
			if(j>=clef.length()){
				j=0;
			}
			msgChiffrer+=chiffrer_caract(str.charAt(i),(clef.charAt(j))+"");
			j++;
		}
		return msgChiffrer;
	}

	@Override
	public char dechiffrer_caract(Character car, String cle) {
		int clef=(int)(cle.toLowerCase()).charAt(0);
		car=Character.toLowerCase(car);
		if(96<car && car<123){
			int clefInt=(clef)-97;
			int carInt=((int)car)-97;
			if(carInt<clefInt){
				int reste=clefInt-carInt;
				char code=(char) (123-reste);
				return (code);
			}
			char code=(char)(((carInt-clefInt)%26)+97);
			return (code);
		}
		/*else if(64<car && car<91){
			int clefInt=((int)clef)-65;
			int carInt=((int)car)-65;
			if(carInt<clefInt){
				int reste=clefInt-carInt;
				char code=(char) (91-reste);
				return (code);
			}
			char code=(char)(((carInt-clefInt)%26)+65);
			return (code);
		}
		*/
		return car;
	}
	
	@Override
	public String dechiffrer_str(String str, String clef) {
		String msgDechiffrer="";
		int j=0;
		for (int i=0;i<str.length();i++) {
			if(j>=clef.length()){
				j=0;
			}
			msgDechiffrer+=dechiffrer_caract(str.charAt(i),(clef.charAt(j))+"");
			j++;
		}
		return msgDechiffrer;
	}

}
