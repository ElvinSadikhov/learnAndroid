package Week1;


/**
 * Write a description of class CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1, shiftedAlphabet2;
    private int mainKey1, mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        mainKey1=key1;
        mainKey2=key2;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);  
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2); 
    }
    
    public String encryptTwoKeys(String input){
        StringBuilder result = new StringBuilder();
        for (int i=0;i<input.length();i++){
            if (i%2==0) {
                CaesarCipher cc = new CaesarCipher(mainKey1);
                result.append(cc.encrypt(input.substring(i,i+1)));
            }
            else {
                CaesarCipher cc = new CaesarCipher(mainKey2);
                result.append(cc.encrypt(input.substring(i,i+1)));
            }
        }
        System.out.println(result.toString());
        return result.toString();
    }
    
    public int indexOfMax(int[] values){
        int maxIndex=0;
        for (int i=0;i<values.length;i++){
            if (values[i]>values[maxIndex]) maxIndex=i;
        }
        return maxIndex;
    }
    public int[] countLetters(String s){
        int[] counting = new int[26];
        String alphabetUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0;i<s.length();i++){
            if (!(Character.isLetter(s.charAt(i)))) continue;
            if (alphabetUp.indexOf(s.charAt(i))==-1 && alphabetLow.indexOf(s.charAt(i))==-1) continue;
            if (Character.isUpperCase(s.charAt(i))) counting[alphabetUp.indexOf(s.charAt(i))]+=1;
            else counting[alphabetLow.indexOf(s.charAt(i))]+=1;
        }
        return counting;
    }
    public String halfOfString(String message, int start){
        StringBuilder res = new StringBuilder();
        for (int i=start;i<message.length();i+=2){
            res.append(message.charAt(i));
        }
        return res.toString();
    }
    public int getKey(String s){
        int maxIndex = indexOfMax(countLetters(s));
        System.out.println(maxIndex);
        if (maxIndex>=4) return maxIndex-4;
        return 26 -(4-maxIndex);
    }
    public String decryptTwoKeys(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        String res = cc.encryptTwoKeys(input);
        /*
        StringBuilder res = new StringBuilder();
        for (int k=0;k<decrypted1.length()+decrypted2.length();k++) res.append(" ");
        int temp = 0;
        int index = 0;
        for (int i=0;i<res.length();i++){
            if (temp%2==0) {
                res.setCharAt(i,decrypted1.charAt(index));
                temp+=1;
            }
            else {
                res.setCharAt(i,decrypted2.charAt(index));
                index+=1;
                temp+=1;
            }
        }*/
        System.out.println(res.toString());
        return res.toString();
    }
}


