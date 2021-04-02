package com.ashoka.TestApis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class TestDownload {
	
	public static void main(String arg[]){
		URLConnection urlconnection = null;
		try {

			InputStream inputStream;
			URL url = new URL("http://drive.afm-dev.s3.amazonaws.com/FRA110087/ERAppAutomation_1.rar?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEBkaCXVzLWVhc3QtMSJGMEQCICXiGWUxR5j03uXOrkIE%2FtJgGW4O43eniHrpzbdC3sbPAiAOGeYORqgt5BqF1yrd1JzUGnpii%2B81cF%2BdkqPNP9HQeCq0AwhiEAAaDDg1ODE3ODA2MTk1NiIMeUA5%2Bo2fZE9tma%2FPKpEDmQnBKchy0osW6DReO4DbgBlwdgEc54OurpQfcT9ZU2B1I4LawBSpPZW9OqQXpIFtWo8WONOXcpFgHjBbChnDQN9JJviu6nMluTPTFfEG5T7pcmhfebb7kL6sphuUYy6TrZNdZ7wMHheWBLyMd6h8VeFmaG0iL4ymnmhmiA76y9LPEdufDgeuy13akJWyx0UO3P0VdetfzEd0dcUOvpOO4C1ZEBfPvGyjw%2Fx4Phg45UqfEuyrLEOUdFxtLXlme8E5hZSahYfPspCGOEMqIXePGNdHf9VuuPmXQsN%2BLkyYvuOlPzBWPKzUrBuJiLDZia7%2FgjrH0q9xNxFjKdbdVjnZX6FrckONyqSPsH1i3ofnwVz13AMdN4FyCSpf6qCDo6sT6Qjy8%2F58FxZgsiseU0SgHzJBR9qJVKxoqqMywBJQXditfTFDpToPOCljSO09jvi6CB9p%2FonCwpiKIFOeiJ9uqwIOorHsXAfE64WhYEntayX5SxkSDwk5Xgd528lcVaaml1eXr6NRasiGD2RAWmVIoE8wwYWIgwY67AFus6J1Xc6f8wEA8AVmD6X0wZbopt8s4l5M1Cz%2BpLy18ctvat0ih1Ol%2F6RPb5V%2F3r6FU9KH3YeAYtJyat7sv87qY3BkrT35H5E%2F8X6Yaf4MUG2dM2QmkWGsAbFOK56ZSQxoxvPQN0hGpBAGZ5dHFRd1P1OWsleHnCrexga%2B4laxaF5pgbvnzsj6%2FQCuhtvBBIuhRXhnatMrwSAAapiTKKCKWW%2B5RQNRWU1tevD1aNrT4NlHIxRia0a7IN5xU8tD2hNjvfvnBexKlsIJHQTrj%2FZEdIaLWt2BOz3R%2FiSLpfBz%2BKd4XsXEjTHeuoyMeA%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210329T171553Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3599&X-Amz-Credential=ASIA4PT3GA2CIVHBBXQB%2F20210329%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=e60605bdf22cc28626ab283a332203f1eb6d8ea67de16a815f033da05c784b58");
			urlconnection = url.openConnection();
			urlconnection.setDoOutput(true);
			
			if (urlconnection instanceof HttpURLConnection) {
				((HttpURLConnection) urlconnection).setRequestMethod("GET");
				((HttpURLConnection) urlconnection).connect();
			}
			int responseCode = ((HttpURLConnection) urlconnection).getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 202)) {
				inputStream = ((HttpURLConnection) urlconnection).getInputStream();
				OutputStream os = new FileOutputStream("C:\\Users\\AppsFreedom3\\Desktop\\ERAppAutomation.rar");
				int j;
				// read byte by byte until end of stream
				byte[] buffer = new byte[4096];
				while ((j = inputStream.read(buffer)) > 0) {
					os.write(buffer, 0, j);
				}
				

			} else {
				inputStream = ((HttpURLConnection) urlconnection).getErrorStream();
			}
			System.out.println(((HttpURLConnection) urlconnection).getResponseMessage());
			((HttpURLConnection) urlconnection).disconnect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
