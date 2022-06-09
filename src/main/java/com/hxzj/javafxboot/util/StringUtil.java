package com.hxzj.javafxboot.util;

public class StringUtil {
    public static String intToHex(int val, int lens) {
        StringBuffer sb = new StringBuffer();
        String hex = Integer.toHexString(val).toUpperCase();
        if(hex.length() < lens){
            int len = lens-hex.length();
            while(len>0){
                sb.append(0);
                len--;
            }
        }
        sb.append(hex);
        return sb.toString();
    }
    public static String intToNex(int val, int lens) {
        StringBuffer sb = new StringBuffer();
        String nex = ""+val;
        if(nex.length() < lens){
            int len = lens-nex.length();
            while(len>0){
                sb.append(0);
                len--;
            }
        }
        sb.append(nex);
        return sb.toString();
    }
    public static String bytesToHex(byte[] bytes, int len) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < len; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF).toUpperCase();
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] hexToBytes(String str){
        int len = str.length();
        byte[] bytes = new byte[(len+1)/2];
        for(int i=0; i<len/2; i++){
            char c = str.charAt(i*2);
            if(c>='a'&&c<='f'){
                bytes[i] |= (byte)(c - 'a' + 10);
            }else if(c>='A'&&c<='F'){
                bytes[i] |= (byte)(c - 'A' + 10);
            }else if(c>='0'&&c<='9'){
                bytes[i] |= (byte)(c - '0');
            }
            bytes[i] <<= 4;
            c = str.charAt(i*2+1);
            if(c>='a'&&c<='f'){
                bytes[i] |= (byte)(c - 'a' + 10);
            }else if(c>='A'&&c<='F'){
                bytes[i] |= (byte)(c - 'A' + 10);
            }else if(c>='0'&&c<='9') {
                bytes[i] |= (byte) (c - '0');
            }
        }
        return bytes;
    }
}
