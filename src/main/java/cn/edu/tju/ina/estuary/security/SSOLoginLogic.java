package cn.edu.tju.ina.estuary.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

@Component
public class SSOLoginLogic {
	// url contains JSESSIONID, so we should get it dynamic
	private String url = null;
	private final String COMMON_PARAM = "&_eventId=submit&submit=µÇÂ¼";

	public boolean login(String username, String password) {
		BufferedReader in = null;
		StringBuffer reqParam = new StringBuffer();
		reqParam.append("username=");
		reqParam.append(username);
		reqParam.append("&password=");
		reqParam.append(password);
		reqParam.append(COMMON_PARAM);
		
		try {
			reqParam.append(getParam());
			URL u = new URL("https://sso.tju.edu.cn" + url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.getOutputStream().write(reqParam.toString().getBytes());
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.indexOf("<title>") > 0) {
					String title = line.substring(line.indexOf("<title>") + 7);
					title = title.substring(0, title.indexOf("</title>"));
					if (title.trim().toLowerCase().equals("tjusso")) {
						return true;
					} else {
						return false;
					}
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getParam() {
		BufferedReader in = null;
		String retParam = "";
		
		try {
			URL u = new URL("https://sso.tju.edu.cn/cas/login");
			URLConnection connection = u.openConnection();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.trim().startsWith("<form")) {
					String t = line.substring(line.indexOf("action=\"") + 8);
					url = t.substring(0, t.indexOf("\""));
				} else if (line.indexOf("name=\"lt\"") > 0) {
					String t = line.substring(line.indexOf("value=\"") + 7);
					String lt = t.substring(0, t.indexOf("\""));
					retParam += "&lt=" + lt;
				} else if (line.indexOf("name=\"execution\"") > 0) {
					String t = line.substring(line.indexOf("value=\"") + 7);
					String execution = t.substring(0, t.indexOf("\""));
					retParam += "&execution=" + execution;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retParam;
	}
}
