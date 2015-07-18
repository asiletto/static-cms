package it.siletto.sp;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestMenuTree {

	static String[] inputDir = new String[]{"src/main/java/template/includes/","src/main/java/template/pages/"};

	public static void main(String[] args) throws Exception {

		Configuration cfg = TestBuilder.configuration(inputDir);
	    
	    Map<String, Object> input = new HashMap<String, Object>();
	    
	    input.put("navbar", TestBuilder.navbar());

	    Template template = cfg.getTemplate("navbar.ftl");

	    Writer consoleWriter = new OutputStreamWriter(System.out);
	    template.process(input, consoleWriter);

	  }

}
