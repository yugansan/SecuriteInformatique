class Permutation implements Chiffrement<String,String,String>{

	public char chiffrer_caract(String str, String cle){
        str=str.toLowerCase();
        int clef=0;
        try{
            // clef=Integer.parseInt(cle.charAt(0)+"");
            clef=Character.getNumericValue(cle.charAt(0));
        }catch(Exception e){
            return str.charAt(clef);
        }

		if(str.length()>=clef){
			return str.charAt(clef-1);
		}else{
			return 'x';
		}
	}

	public char dechiffrer_caract(String str, String clef){
		return chiffrer_caract(str,clef);
	}

	public String chiffrer_str(String str,String clef){
    	String msgChiffrer="";
    	String []permut=clef.split(",");
    	String code;
    	int j;
    	char car;
    	//str 
        String oldString=str;
    	char []newStr=str.toCharArray();
    	str="";
    	for(int k=0;k<newStr.length;k++){
    		car=newStr[k];
    		//pour prendre que les lettre et non les point virgule ect
    		if(96<car && car<123 || 64<car && car<91){
    			str+=car;
    		}
    	}
    	for (j=0;j<str.length()-permut.length;j+=(permut.length)) {
    		code=str.substring(j,j+permut.length);
    		for(int i=0; i<permut.length;i++){
    			msgChiffrer+=chiffrer_caract(code,(permut[i]));
    		}
    	}
    	code=str.substring(j,str.length());
    	for(int i=0; i<permut.length;i++){
    		msgChiffrer+=chiffrer_caract(code,permut[i]);
    	}

        //met les espace ou il faut
        /*String newMsgChiffrer="";
        for(int i=0;i<oldString.length();i++){
            if(i>msgChiffrer.length()-1){
                //i=oldString.length();
                return newMsgChiffrer;
            }
            if(oldString.charAt(i)==' '){
                newMsgChiffrer+=" "; 
            }else{
                newMsgChiffrer+=msgChiffrer.charAt(i);
            }
        }
    	return newMsgChiffrer;
        */
        return msgChiffrer;
    }

    //la meme chose
	public String dechiffrer_str(String str,String clef){
		return chiffrer_str(str,clef);
	}

}