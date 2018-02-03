package com.musiccrawl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.musiccrawl.crawl.AutoCrawl;
import com.musiccrawl.crawl.enums.TypeEnum;
import com.musiccrawl.common.entity.Song;
import com.musiccrawl.common.repository.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest()
@Rollback(false)
public class TestAutoCrawl {
    @Autowired
    private AutoCrawl autoCrawl;
    @Autowired
    private SongRepository songRepository;
    @Test
    public void sampleTest() throws UnsupportedEncodingException {
        autoCrawl.starCrawl();
    }
    @Test
    public void TestCrawlAllMusic(){
        autoCrawl.starCrawlMusic();
    }

    @Test
    public void enumTest(){
        System.out.println(TypeEnum.getEnum(2).getName());
    }

    @Test
    public void sqlTest(){
//        Song song = songRepository.findOne(1);
//        song.setId("1");
//        song.setMid(0);
        Song song  = new Song();
        song.setId("1");
        song.setName("name");
        songRepository.saveOne(song.getId(),song.getName(),song.getUrl(),song.getImgUrl(),song.getLrcText(),song.getSinger(),song.getPlantformCode(),song.getType());
    }







    @Test
    public void qqSpult(){
        String s = "{\"code\":0,\"subcode\":0,\"message\":\"\",\"default\":0,\"data\":{\"categories\":[{\"categoryGroupName\":\"热门\",\"items\":[{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"},{\"sortId\":1,\"sortName\":\"默认\"}],\"categoryId\":10000000,\"categoryName\":\"全部\",\"usable\":0}],\"usable\":0},{\"categoryGroupName\":\"语种\",\"items\":[{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":165,\"categoryName\":\"国语\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":167,\"categoryName\":\"英语\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":168,\"categoryName\":\"韩语\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":166,\"categoryName\":\"粤语\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":169,\"categoryName\":\"日语\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":170,\"categoryName\":\"小语种\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":203,\"categoryName\":\"闽南语\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":204,\"categoryName\":\"法语\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":205,\"categoryName\":\"拉丁语\",\"usable\":1}],\"usable\":1},{\"categoryGroupName\":\"流派\",\"items\":[{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":6,\"categoryName\":\"流行\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":15,\"categoryName\":\"轻音乐\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":11,\"categoryName\":\"摇滚\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":28,\"categoryName\":\"民谣\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":8,\"categoryName\":\"R&#38;B\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":153,\"categoryName\":\"嘻哈\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":24,\"categoryName\":\"电子\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":27,\"categoryName\":\"古典\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":18,\"categoryName\":\"乡村\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":22,\"categoryName\":\"蓝调\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":21,\"categoryName\":\"爵士\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":164,\"categoryName\":\"新世纪\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":25,\"categoryName\":\"拉丁\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":218,\"categoryName\":\"后摇\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":219,\"categoryName\":\"中国传统\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":220,\"categoryName\":\"世界音乐\",\"usable\":1}],\"usable\":1},{\"categoryGroupName\":\"主题\",\"items\":[{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":39,\"categoryName\":\"ACG\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":136,\"categoryName\":\"经典\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":146,\"categoryName\":\"网络歌曲\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":133,\"categoryName\":\"影视\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":141,\"categoryName\":\"KTV热歌\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":131,\"categoryName\":\"儿歌\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":145,\"categoryName\":\"中国风\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":194,\"categoryName\":\"古风\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":148,\"categoryName\":\"情歌\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":196,\"categoryName\":\"城市\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":197,\"categoryName\":\"现场音乐\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":199,\"categoryName\":\"背景音乐\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":200,\"categoryName\":\"佛教音乐\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":201,\"categoryName\":\"UP主\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":202,\"categoryName\":\"乐器\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":14,\"categoryName\":\"DJ\",\"usable\":1}],\"usable\":1},{\"categoryGroupName\":\"心情\",\"items\":[{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":52,\"categoryName\":\"伤感\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":122,\"categoryName\":\"安静\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":117,\"categoryName\":\"快乐\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":116,\"categoryName\":\"治愈\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":125,\"categoryName\":\"励志\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":59,\"categoryName\":\"甜蜜\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":55,\"categoryName\":\"寂寞\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":126,\"categoryName\":\"宣泄\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":68,\"categoryName\":\"思念\",\"usable\":1}],\"usable\":1},{\"categoryGroupName\":\"场景\",\"items\":[{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":78,\"categoryName\":\"睡前\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":102,\"categoryName\":\"夜店\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":101,\"categoryName\":\"学习\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":99,\"categoryName\":\"运动\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":85,\"categoryName\":\"开车\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":76,\"categoryName\":\"约会\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":94,\"categoryName\":\"工作\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":81,\"categoryName\":\"旅行\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":103,\"categoryName\":\"派对\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":222,\"categoryName\":\"婚礼\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":223,\"categoryName\":\"咖啡馆\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":224,\"categoryName\":\"跳舞\",\"usable\":1},{\"allsorts\":[{\"sortId\":2,\"sortName\":\"最新\"},{\"sortId\":3,\"sortName\":\"最热\"},{\"sortId\":4,\"sortName\":\"评分\"}],\"categoryId\":16,\"categoryName\":\"校园\",\"usable\":1}],\"usable\":1}]}\n" +
                "}";
        JSONObject object = JSONObject.parseObject(s);
        JSONArray array = object.getJSONObject("data").getJSONArray("categories");
        for(int i = 0;i<array.size();i++){
            JSONObject jsonObject = array.getJSONObject(i);
            JSONArray items = jsonObject.getJSONArray("items");
            for(int j =0;j<items.size();j++){
                JSONObject jsonObject1 = items.getJSONObject(j);
                System.out.print("(\""+jsonObject1.getString("categoryName")+"\","+jsonObject1.getInteger("categoryId")+"),");
            }
        }
    }

}
