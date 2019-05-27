package com.twt.Jsoup.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil{

	public static int count = 0;

	// 爬取网络的图片到本地(图片名是写死的)
	/**
	 * 
	 * @param destUrl 图片地址
	 */
	public static void saveToFile(String destUrl) {

		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			String imgName = destUrl.substring(7, destUrl.lastIndexOf("."));
			System.out.println(imgName);
			File dir = new File("f://img");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File("f:\\img\\haha" + count + ".jpg");
			fos = new FileOutputStream(file);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (ClassCastException e) {
			System.out.println("ClassCastException");
		} finally {
			count++;
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
	}
	
	/**
	 * 
	 * @param destUrl 图片地址
	 * @param name 英雄名
	 */
	public static void saveToFile(String destUrl,String name) {

		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			String imgName = destUrl.substring(7, destUrl.lastIndexOf("."));
			System.out.println(imgName);
			File dir = new File("f://img");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File("f:\\img\\"+name+".jpg");
			System.out.println(file.getAbsolutePath());
			fos = new FileOutputStream(file);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (ClassCastException e) {
			System.out.println("ClassCastException");
		} finally {
			count++;
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	//爬取网络的图片到本地(图片文件夹和图片名是按图片种类区别)
	/**
	 * 
	 * @param imgUrl 图片地址
	 * @param imgTitle 图片标题
	 * @param imgFileName 图片类型名(图片文件夹名)
	 * @param imgName  图片名
	 * @return 返回爬取图片的数量
	 */
	public static void saveImgToFile(String imgUrl,String imgTitle,String imgFileName,String imgName) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(imgUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			String pan = "f://img"; //图片盘符
			//创建F盘img文件夹
			File dir = new File(pan);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			//创建F盘img下图片标题文件夹
			File dirImgTitle = new File(pan+"//"+imgTitle);
			if (!dirImgTitle.exists()) {
				dirImgTitle.mkdirs();
			}
			//创建F盘下img下图片标题文件夹下图片类别文件夹
			File dirImgType = new File(pan+"//"+imgTitle+"//"+imgFileName);
			if (!dirImgType.exists()) {
				dirImgType.mkdirs();
			}
			//通过图片地址设置图片名
			imgName = imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.lastIndexOf("."));
			//创建创建F盘下img下图片标题文件夹下图片类别文件夹下图片
			File file = new File(pan+"\\"+imgTitle+"\\"+imgFileName+"\\"+imgName+".jpg");
			System.out.println(file.getAbsolutePath());
			fos = new FileOutputStream(file);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (ClassCastException e) {
			System.out.println("ClassCastException");
		} finally {
			count++;
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
	}

	// 解析url的元素
	private static void getHtmlElements(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			// 获取后缀名为jpg的img元素
			Elements pngs = doc.select("img[src$=.jpg]");
			for (Element element : pngs) {
				saveToFile(element.attr("src"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
