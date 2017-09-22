package com.chenkp.test.util;


public class UnicodeToChina {

	public static void main(String[] args) {
		
		System.out.println(new String("\u77ed\u79df\u6392\u8f66\u5931\u8d25"));
//		String str = unicode2String("\\U5927\\U4f17");
//		System.out.println(str);
	}
	
	public static String unicode2String(String unicode) {
		 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {
	 
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	 
	        // 追加成string
	        string.append((char) data);
	    }
	 
	    return string.toString();
	}

	/**
	 * unicode->chinese
	 * 
	 * @param unicodeStr
	 * @return
	 */
	public static String decodeUnicode(final String dataStr) {     
        int start = 0;     
        int end = 0;     
        final StringBuffer buffer = new StringBuffer();     
        while (start > -1) {     
            end = dataStr.indexOf("\\u", start + 2);     
            String charStr = "";     
            if (end == -1) {     
                charStr = dataStr.substring(start + 2, dataStr.length());     
            } else {     
                charStr = dataStr.substring(start + 2, end);     
            }     
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。     
            buffer.append(new Character(letter).toString());     
            start = end;     
        }     
        return buffer.toString();     
     }  

	/**
	 * chinese->unicode
	 */
	public static String gbEncoding(final String gbString) {   //gbString = "测试"  
        char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]  
        String unicodeBytes = "";     
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {     
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串  
              if (hexB.length() <= 2) {     
                  hexB = "00" + hexB;     
             }     
             unicodeBytes = unicodeBytes + "\\u" + hexB;     
        }     
        System.out.println("unicodeBytes is: " + unicodeBytes);     
        return unicodeBytes;     
    }  

}
