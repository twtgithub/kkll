package com.twt.Jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.twt.Jsoup.util.JsoupUtil;

public class Test {
	public static void main(String[] args) {
		String destUrl = null;		//图片地址
		String name = null;			//图片名
		String FullName = null;		//图片全名
		String describe = null; 	//英雄描述
		Document document = null;
		try {
			document = Jsoup.connect("http://lol.duowan.com/hero/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements div = document.select(".champion_tooltip");
		for (Element element : div) {
			destUrl = element.select("img").attr("src");
			name = element.select(".champion_name").text();
			FullName = element.select(".champion_title").text();
			describe = element.select("p").text();
			JsoupUtil.saveToFile(destUrl, name);
			System.out.println("英雄全名："+FullName);
			System.out.println("英雄描述："+describe);
		}
	}
}
