package com.musiccrawl.crawl;

import com.musiccrawl.crawl.enums.QQTypeEnum;
import com.musiccrawl.crawl.enums.TypeEnum;
import com.musiccrawl.crawl.interfacies.ICrawl;
import com.musiccrawl.crawl.qq.QQCrawl;
import com.musiccrawl.crawl.wangyiyun.WangyiyunCrawl;
import com.musiccrawl.crawl.xiami.XiamiCrawl;
import com.musiccrawl.entity.PlayList;
import com.musiccrawl.myexception.FailedCrawlResultException;
import com.musiccrawl.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class AutoCrawl {

    @Autowired
    private PlayListRepository playListRepository;

    public void starCrawl() throws UnsupportedEncodingException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        List<Callable<String>> callables = createTask();
        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            executorService.shutdown();
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("跑完了");
    }

    private List<Callable<String>> createTask() {
        List<Callable<String>> callables = new ArrayList<>();
        for (TypeEnum typeEnum : TypeEnum.values()) {
            callables.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String nextUrl = ICrawl.WYY_PLAYLIST_BASEUTL + "?cat=" + URLEncoder.encode(typeEnum.getName(), "utf-8");
                    while (nextUrl != null) {
                        if (nextUrl.equals("")) {
                            System.out.println("end");
                            break;
                        }
                        System.out.println("next" + nextUrl);
                        try {
                            Map<String, Object> map = new WangyiyunCrawl().getPlayList(nextUrl, typeEnum.getCode());
                            List<PlayList> playList = (List) map.get("playList");
                            nextUrl = (String) map.get("nextUrl");
                            playListRepository.save(playList);
                        } catch (Exception e) {
                            //System.out.println("抛异常了....");
                        }
                    }
                    return "success";
                }
            });

        }
        for (TypeEnum typeEnum : TypeEnum.values()) {
            callables.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String base = "http://www.xiami.com/search/collect/?key=";
                    String nextUrl = base + typeEnum.getName();
                    while (nextUrl != null) {
                        System.out.println("next" + nextUrl);
                        try {
                            Map<String, Object> map = new XiamiCrawl().getPlayList(nextUrl, typeEnum.getCode());
                            List<PlayList> playList = (List) map.get("playList");
                            nextUrl = (String) map.get("nextUrl");
                            playListRepository.save(playList);
                        } catch (FailedCrawlResultException e) {
                            e.printStackTrace();
                            break;
                        } catch (Exception e) {
                            //System.out.println("抛异常了....");
                        }
                    }
                    return "success";
                }
            });
        }
        for (QQTypeEnum typeEnum : QQTypeEnum.values()) {
            callables.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    int curr = 0;
                    while (true) {
                        //System.out.println("next" + curr);
                        try {
                            Map<String, Object> map = new QQCrawl().getPlayList(ICrawl.QQ_PLAYLIST_BASEURL,curr,typeEnum.getCode(), typeEnum.getLink());
                            List<PlayList> playList = (List) map.get("playList");
                            curr = (int) map.get("nextUrl");
                            playListRepository.save(playList);
                        } catch (FailedCrawlResultException e) {
                            e.printStackTrace();
                            break;
                        } catch (Exception e) {
                            //System.out.println("抛异常了....");
                        }
                    }
                    return "success";
                }
            });
        }
        return callables;
    }

}

