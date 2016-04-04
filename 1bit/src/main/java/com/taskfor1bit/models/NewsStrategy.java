package com.taskfor1bit.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ivanb on 30.03.2016.
 */

@Component
public class NewsStrategy {   //стратегия парсинга https://habrahabr.ru/all/


    public ModelNews[] getNews(){
        ModelNews[] modelNewses = new ModelNews[10]; //массив новостей
        int count = 0;//индекс

        try{

            while (count < 10) { //пока массив не заполнится новостями
                Document doc = getDocument();

                if (doc == null) break;

                Elements elements = doc.select("[class=post shortcuts_item]");      // выбираем новости без перевода
                Elements temp = doc.select("[class=post translation shortcuts_item]"); //выбираем все новости с переводом
                elements.addAll(temp);

                if(elements == null) break;

                for(Element element : elements){

                    Element titleElement = element.select("[class=post_title]").first();
                    String title = titleElement.text(); //титул новости



                    String url = titleElement.attr("href"); //url новости


                    ModelNews model = new ModelNews(title, url); //создаем модель на основе полученных данных


                    modelNewses[count] = model;//заносим в массив
                    count++;



                }

            }
            count = 0;


        }catch (Exception e){
            System.out.println("Что-то пошло не так в NewsStrategy");
            e.printStackTrace();
        }

        return modelNewses;
    }

    private Document getDocument() throws IOException {


        String url = "https://habrahabr.ru/search/?target_type=posts&q=java&order_by=date";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();

        return document;
    }
}
