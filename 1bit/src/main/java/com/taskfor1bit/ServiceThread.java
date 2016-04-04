package com.taskfor1bit;

import com.taskfor1bit.database.DataBaseController;
import com.taskfor1bit.email.EmailSession;
import com.taskfor1bit.models.ModelNews;
import com.taskfor1bit.models.NewsStrategy;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;


public class ServiceThread extends TimerTask {

    private NewsStrategy newsStrategy;


    public ServiceThread(NewsStrategy newsStrategy) {
        this.newsStrategy = newsStrategy;

    }

    @Override
    public void run() {

            List<ModelNews> result = new ArrayList<>();
            List<ModelNews> listDB = DataBaseController.getDataFromDBToArray();
            ModelNews[] news = newsStrategy.getNews();
            boolean temp = false;

            if(listDB.isEmpty() || listDB == null){
                for(ModelNews modelNews : news){
                    result.add(modelNews);
                }
            }
            else {
                for (int i = 0; i < news.length; i++) {
                    for (ModelNews modelNews : listDB) {
                        if (news[i].getUrl().equals(modelNews.getUrl())) temp = true;
                    }

                    if(!temp){
                        result.add(news[i]);
                    } else temp = false;
                }
            }
        Collections.reverse(result);

        DataBaseController.insertToDBNews(result);

        /*    for(ModelNews modelNews : result){
                if(modelNews.getTitle().toLowerCase().contains("java")){
                    try {
                        EmailSession.generateAndSendEmail(modelNews.getTitle(), modelNews.getUrl());        //ЛОКАЛЬНО РАБОТАЕТ!!!
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }*/





    }
}
