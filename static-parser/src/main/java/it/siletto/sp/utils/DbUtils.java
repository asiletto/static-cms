package it.siletto.sp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbUtils {

	public static Properties load(String filename) throws IOException{
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filename);
		prop.load(in);
		in.close();
		return prop;
	}
}
