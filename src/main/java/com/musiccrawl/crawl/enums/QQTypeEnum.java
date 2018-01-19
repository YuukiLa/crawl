package com.musiccrawl.crawl.enums;

public enum QQTypeEnum {
    HUAYU("国语",165,1),OUMEI("英语",167,2),HANYU("韩语",168,4),YUEYU("粤语",166,5),RIYU("日语",169,3),XIAOYUZHONG("小语种",170,6),MINNNAYU("闽南语",203,6),
    FAYU("法语",204,6),LADINGYU("拉丁语",205,6),LIUXING("流行",6,7),QINGYINYUE("轻音乐",15,13),YAOGUN("摇滚",11,8),MINGYAO("民谣",28,9),RB("R&B",8,16),
    XIHA("嘻哈",153,12),DIANZI("电子",24,10),GUDIAN("古典",27,17),XIANGCUN("乡村",18,15),LANDIAO("蓝调",22,22),JUESHI("爵士",21,14),NEWAGE("新世纪",164,27),
    LADING("拉丁",25,25),HOUYAO("后摇",218,29),CHUANTONG("中国传统",219,28),SHIJIEYINYUE("世界音乐",220,24),ACG("ACG",39,56),JINGDIAN("经典",136,64),
    WANGLUOGEQU("网络歌曲",146,62),YINGSHI("影视",133,55),KTV("KTV热歌",141,63),ERGE("儿歌",131,69),ZHONGGUOFENG("中国风",145,28),GUFENG("古风",194,28),
    QINGGE("情歌",148,44),CHENGSHI("城市",196,40),XIANCHENG("现场音乐",197,37),BEIJING("背景音乐",199,41),FOJIAO("佛教音乐",200,43),UP("UP主",201,48),
    YUEQI("乐器",202,68),DJ("DJ",14,26),SHANGGAN("伤感",52,46),ANJING("安静",122,53),KUAILE("快乐",117,52),ZHIYU("治愈",116,47),LIZHI("励志",125,32),
    TIANMI("甜蜜",59,44),JIMO("寂寞",55,49),XUANXIE("宣泄",126,48),SINIAN("思念",68,54),SHUIQIAN("睡前",78,31),YEDIAN("夜店",102,41),XUEXI("学习",101,32),
    YUNDONG("运动",99,38),KAICHE("开车",85,37),YUEHUI("约会",76,44),GONGZUO("工作",94,33),LVXING("旅行",81,39),PAIDUI("派对",103,63),HUNLI("婚礼",222,44),
    KAFEI("咖啡馆",223,35),TIAOYU("跳舞",224,51),XIAOYUAN("校园",16,57);



    private String name;
    private int code;
    private int link;

    private QQTypeEnum(String name, int code,int link) {
        this.name = name;
        this.code = code;
        this.link = link;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
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

    public static QQTypeEnum getEnum(int link) {
        for (QQTypeEnum typeEnum:QQTypeEnum.values() ){
            if(typeEnum.getLink() == link)
                return typeEnum;
        }
        return null;
    }
}
