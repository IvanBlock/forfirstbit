package com.taskfor1bit.controllers;

import com.taskfor1bit.ServiceThread;
import com.taskfor1bit.database.DataBaseController;
import com.taskfor1bit.models.NewsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


@Controller
public class IndexController{
    private NewsStrategy strategy;
    private Timer timer;

    @Autowired
    public void startTimerAndSetStrategy(NewsStrategy strategy){
        this.strategy = strategy;
        this.timer = new Timer();
        TimerTask timerTask = new ServiceThread(strategy);


        Date date = new Date();
        int hour = date.getHours(); //получаем сколько часов прошло от 00.00
        int min = date.getMinutes(); //получаем минуты
        int millisec = hour*3600000 + min*60000; //переводим часы и минуты в миллисекунды
        int requiredTime = 7200000; //старт треда 02.00
        if(millisec <= 7200000)    //если millisec от 00.00 до 02.00
        requiredTime = requiredTime - millisec;
        else{                   //если millisec от 2.00 до 24.00
            requiredTime = 86400000 - (millisec - requiredTime);
        }
        System.out.println(requiredTime);

        timer.schedule(timerTask, requiredTime, 86400000);  //запускам тред в 2 часа ночи и обновляем данные в БД через 24 часа


    }

    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("newsList", DataBaseController.getDataFromDBToArray());

        return "index";
    }
}
