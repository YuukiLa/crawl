package com.musiccrawl.crawl.enums;

import java.util.Arrays;

public enum TypeEnum {

    HUAYU("华语", 1), OUMEI("欧美", 2), RIYU("日语", 3), HANYU("韩语", 4), YUEYU("粤语", 5), XIAOYUZHONG("小语种", 6),
    LIUXING("流行",7),YAOGUN("摇滚",8),MINYAO("民谣",9),DIANZI("电子",10),WUQU("舞曲",11),SHUOCHANG("说唱",12),QINGYINYUE("轻音乐",13),
    JUESHI("爵士",14),XIANGCUN("乡村",15),RB("R&B/Soul",16),GUDIAN("古典",17),MINZU("民族",18),YINGLUN("英伦",19),JINSHU("金属",20),
    PENGKE("朋克",21),LANDIAO("蓝调",22),LEIGUI("雷鬼",23),SHIJIEYINYUE("世界音乐",24),LADING("拉丁",25),LINGLEIDULI("另类/独立",26),
    NEWAGE("New Age",27),GUFENG("古风",28),HOUYAO("后摇",29),QINGCHEN("清晨",30),YEWAN("夜晚",31),XUEXI("学习",32),GONGZUO("工作",33),
    WUXIU("午休",34),XIAWUCHA("下午茶",35),DITIE("地铁",36),JIACHE("驾车",37),YUNDONG("运动",38),LVXING("旅行",39),SANBU("散步",40),
    JIUBA("酒吧",41),HUAIJIU("怀旧",42),QINGXIN("清新",43),LANGMAN("浪漫",44),XINGGAN("性感",45),SHANGGAN("伤感",46),ZHIYU("治愈",47),
    FANGSONG("放松",48),GUDU("孤独",49),GANDONG("感动",50),XINGFEN("兴奋",51),KUAILE("快乐",52),ANJING("安静",53),SINIAN("思念",54),
    YINGSHIYUANSHNEG("影视原声",55),ACG("ACG",56),XIAOYUAN("校园",57),YOUXI("游戏",58),QILINGHOU("70后",59),BALINGHOU("80后",60),
    JIULINGHOU("90后",61),WANGLUOGEQU("网络歌曲",62),KTV("KTV",63),JINGDIAN("经典",64),FANCHANG("翻唱",65),JITA("吉他",66),GANGQIN("钢琴",67),
    QIYUE("器乐",68),ERTONG("儿童",69),BANGDAN("榜单",70),LINGLINGHOU("00后",71);


    private String name;
    private int code;

    private TypeEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static TypeEnum getEnum(int code) {
        for (TypeEnum typeEnum:TypeEnum.values() ){
            if(typeEnum.getCode() == code)
                return typeEnum;
        }
        return null;
    }

}
