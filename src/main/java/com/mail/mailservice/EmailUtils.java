package com.mail.mailservice;

public class EmailUtils {
	public static String getEmailMesage(String name, String host, String token) {
		return "hello MR-" + name + ", \\n your new account has been created. please click the link blew\" "
				+ getVeriication(host, token);

	}

	public static String getVeriication(String host, String token) {
		return host + "/users?token="+token;
      
	}

}
