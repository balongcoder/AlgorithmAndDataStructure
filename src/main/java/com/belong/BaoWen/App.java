package com.belong.BaoWen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
	private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签   
//	private final static String regxpForBracket = "\\[+(^\\d+$)+\\]"; // 过滤所有以<开头以>结尾的标签   
	private final static String regxpForBracket = "\\[.+(^[0-9]*[1-9][0-9]*$)\\]"; // 过滤所有以<开头以>结尾的标签   
	
    public static void main( String[] args )
    {
//    	String str = "<HTML>im [123] [abc] long<\\HTML>";
//        System.out.println( removeIndex(str) );
        
        String xpath = "abc[aaa].[bb]bcd";
        String subXpath = "abc[aaa][1].[bb]bcd[1].qqq";
        
        App app = new App();
        
        System.out.println(app.getBeforeIndex2(xpath, subXpath));
        
        System.out.println(app.getBeforeIndex2("abc", "abc[1]"));
        
    }
    
    private int getBeforeIndex2(String originalKey, String returnKey) {
		String[] originalKeyArray = originalKey.split("\\.");
		String[] returnKeyArray = returnKey.split("\\.");
		
		String key = returnKeyArray[originalKeyArray.length - 1];
		int index = 0;
		int start = 0;
		int end = 0;
		String innerStr;
		
		if (key.indexOf("[") > -1){
			start = key.lastIndexOf("[");
	    	end = key.lastIndexOf("]");
	    	
	    	innerStr = key.substring(start + 1, end);
			
			try {
	    		index = Integer.parseInt(innerStr);
	    	} catch(NumberFormatException e) {
	    		index = 0;
	    	}
		}
    	return index;
	}
    
    private int getBeforeIndex(String originalKey, String returnKey) {
		String tempkey = returnKey;
		int index = 0;
		int start = 0;
		int end = 0;
		String innerStr;
		while(tempkey.indexOf("[") > -1) {
			
			start = tempkey.indexOf("[");
	    	end = tempkey.indexOf("]");
			if (tempkey.startsWith(originalKey)) {
				tempkey = tempkey.substring(originalKey.length(), tempkey.length());

		    	innerStr = tempkey.substring(start + 1, end);
		    	
		    	try {
		    		index = Integer.parseInt(innerStr);
		    	} catch(NumberFormatException e) {
		    		index = 0;
		    	}
		    	break;
			} else {
				innerStr = tempkey.substring(start + 1, end);
				
				try {
		    		index = Integer.parseInt(innerStr);
		    		tempkey = tempkey.substring(0, start) + tempkey.substring(end + 1, tempkey.length());
		    	} catch(NumberFormatException e) {

		    	}
			}	
		}
		
		return index;
		
	}
}
