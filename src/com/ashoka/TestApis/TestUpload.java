package com.ashoka.TestApis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class TestUpload {
	
	public static void main(String arg[]){
		URLConnection urlconnection = null;
		try {
			File file = new File("C:\\Users\\AppsFreedom3\\Desktop\\MyTest PDF.pdf");
			URL url = new URL("https://s3.amazonaws.com/drive.afm-dev/FRA110087/MyTest%2520PDF_1.pdf?X-Amz-Security-Token=IQoJb3JpZ2luX2VjECwaCXVzLWVhc3QtMSJIMEYCIQDhrwd8vCuzOEAzAav9g2q9Otb%2BhGaAvW%2BWvRXGi8Qd0QIhALL6OX03K0SHu7yIcKwKYyMvsHLLnha2UFUvLcM0Fs0rKrQDCHQQABoMODU4MTc4MDYxOTU2IgyR1jjGjDdc2FrWeA0qkQOg9FOuS0NjGDTXrEZAe8qxxybC%2BDWuXmQXTl2iUxCnQz6N4xbEd8D0sICGNDGxb1sxZ7Ff%2F4vUtbRTcv3%2BLZ7uFp3%2F3V%2FzD%2BwzJhJm5YxLSHvt6t84598Op1FoY9OzqhO%2B6oh2DF42s65qj4laY8ZU0PsgChj5zUaKUM4S2MMdfZ%2BPc1IZzP7gN6lleA1xfUWGG6t%2FHfjG%2B0QdiN2YsayY%2F1V0MbgMbFWLftJ4RTFv1z6yHwGhql1gBTmG722HJ7%2BKg3pJWRKewH0WFKZ0VSTVG%2BikwYYMaFsw600bIkxAuwJ8rZGJmVY3Ab2k0G35qPlE3FygF8kKJnTHw7mQ3nmi3tILDsV0WgpAwgFIA82El9iRTMKHucog2YkwdW9SLG%2Bv8MUESr5p4aKJCzCw4QjSuugPHUmRUls8Vu%2B2cM7hTHFIFGp8xdagavo6fsQO9cHTjM%2BHgKpqGenxdvg1BPkHygQpwSsnMbZT2J7Jmo5U%2BqIFCiyXgpp7AZeiNZwWb6jVywGNXD3pCp%2BPap1Zkut8iDCjk4yDBjrqASo4q8VIz3fi6mrkH%2Bg4%2BYpVZX6cTeHaeMguutPtjCOTLodyPJEqyJwMYHb8Xa4umRYeDpCETcyr1wm%2FcNnRk5rKz99D757xWuDDBywa%2BB6ajJxEPGHek15JwtX3Zk%2FSwsoIldF3UsmVzQL087MPNVQzZJEEDNq2X9SgX1h%2FBFm%2F5iuYletXedo%2FOJIEGjdjqCRIBshLJcBdEibOxBq8wMOYhY13EgqDxpBdOV%2BYfBa%2BDbi%2BkwAlDVzu%2B8a0g6aFjBYgnkpYgfNjf%2Bhbv%2FYY2w6Mrcw1YxCISvuevxn7KTW0K7aIkZhSSduLiQ%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210330T115103Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3599&X-Amz-Credential=ASIA4PT3GA2CHEW6FSHA%2F20210330%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=f5082e5460b1572a7c856ff819422ef3732a7b901f387934e1018098baf1e810");
			urlconnection = url.openConnection();
			urlconnection.setDoOutput(true);
			
			if (urlconnection instanceof HttpsURLConnection) {
				((HttpsURLConnection) urlconnection).setRequestMethod("PUT");
				((HttpsURLConnection) urlconnection).connect();
			}

			BufferedOutputStream bos = new BufferedOutputStream(urlconnection.getOutputStream());
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			int i;
			// read byte by byte until end of stream
			byte[] buffer = new byte[4096];
			while ((i = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, i);
			}
			
			bis.close();
			bos.close();
			System.out.println(((HttpsURLConnection) urlconnection).getResponseMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
