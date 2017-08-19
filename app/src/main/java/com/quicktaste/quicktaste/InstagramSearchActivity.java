package com.quicktaste.quicktaste;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InstagramSearchActivity extends AppCompatActivity {

    //private String insta_search_sample = "https://www.instagram.com/explore/tags/seoul/{ \"count\" : \"1\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20687927_869109316599100_2149211513343180800_n.jpg\"}, { \"count\" : \"2\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c125.0.750.750/20759193_166882123877208_2079021388265422848_n.jpg\"}, { \"count\" : \"3\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c107.0.866.866/20766125_2098374873522186_9042552217181093888_n.jpg\"}, { \"count\" : \"4\" \"tag\" : [\"#강남\" ,\"#Korea\" ,\"#SouthKorea\" ,\"#Seoul\" ,\"#Gangnam\" ,\"#Corea\" ,\"#CoreadelSur\" ,\"#Seul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/c80.0.480.480/20688539_107926883260265_983156419651960832_n.jpg\"}, { \"count\" : \"5\" \"tag\" : [\"#비치클럽에코\" ,\"#ECCO 맥주는\" ,\"#DESPERADOS\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/e15/20766979_467479023611693_1408298370241396736_n.jpg\"}, { \"count\" : \"6\" \"tag\" : [\"#vante\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20759554_262946624221636_2693829141890859008_n.jpg\"}, { \"count\" : \"7\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20688739_469435896767166_6201756527146041344_n.jpg\"}, { \"count\" : \"8\" \"tag\" : [\"#aboutlastnight\" ,\"#seoul\" ,\"#dongdaemun\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20766196_272054373294150_2825086507847516160_n.jpg\"}, { \"count\" : \"9\" \"tag\" : [\"#somuchlove\" ,\"#thankyouall\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20688637_734826856704803_9174899456858914816_n.jpg\"}, { \"count\" : \"10\" \"tag\" : [\"#서울\" ,\"#이태원\" ,\"#리차드카피캣\" ,\"#봄베이\" ,\"#봄베이사파이어\" ,\"#티본스테이크\" ,\"#seoul\" ,\"#itaewon\" ,\"#richardcopycat\" ,\"#bombay\" ,\"#bombaysaphire\" ,\"#tbornsteak\" ,\"#with\" ,\"#friends\" ,\"#and\" ,\"#GF\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20766512_110988369570871_6002275924974764032_n.jpg\"}, { \"count\" : \"11\" \"tag\" : [\"#mykoreanjagiya\" ,\"#heartevangelista\" ,\"#philippines\" ,\"#daebak\" ,\"#saranghaeyo\" ,\"#ukiss\" ,\"#seoul\" ,\"#manila\" ,\"#southkorea\" ,\"#3peasinapod\" ,\"#moorimschool\" ,\"#gwapo\" ,\"#halohalo\" ,\"#denizen\" ,\"#sillyboy\" ,\"#instagwapo\" ,\"#hottest\" ,\"#oppa\" ,\"#in\" ,\"#town\" ,\"#welovexander\" ,\"#bebot\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.90.720.720/20766874_347275635727621_3799949903996649472_n.jpg\"}, { \"count\" : \"12\" \"tag\" : [\"#ProjectUlssigu2017\" ,\"#Norrikkot\" ,\"#프로젝트얼씨구\" ,\"#노리꽃\" ,\"#얼씨구좋다\" ,\"#서울\" ,\"#IndependenceDayKorea\" ,\"#광복절\" ,\"#프로젝트얼씨구2017\" ,\"#Seoul\" ,\"#Korea\" ,\"#서울\" ,\"#한국\" ,\"#풍물\" ,\"#TraditionalKoreanmusic\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c55.0.969.969/20759104_1613521895333975_8703245295376400384_n.jpg\"}, { \"count\" : \"13\" \"tag\" : [\"#Repost\" ,\"#EtudeHouse\" ,\"#flagship\" ,\"#store\" ,\"#DalzielPow\" ,\"#Seoul\" ,\"#SouthKorea\" ,\"#cosmetics\" ,\"#retaildesign\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20687797_2185831501643215_354195382986604544_n.jpg\"}, { \"count\" : \"14\" \"tag\" : [\"#goodnight\" ,\"#dailypic\" ,\"#seoul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/e15/20837243_1435854993118099_3131734919240220672_n.jpg\"}, { \"count\" : \"15\" \"tag\" : [\"#speakeasybar\" ,\"#스피크이지\" ,\"#앨리스바\" ,\"#르체임버\" ,\"#청담동\" ,\"#Seoul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20688767_1888068401512624_1832042390580363264_n.jpg\"}, { \"count\" : \"16\" \"tag\" : [\"#thankyou\" ,\"#love\" ,\"#bdaycelebration\" ,\"#blessed\" ,\"#cake\" ,\"#birthdaycake\" ,\"#instagood\" ,\"#igers\" ,\"#nomnom\" ,\"#sweet\" ,\"#foodlovers\" ,\"#foodporn\" ,\"#dessert\" ,\"#foodpics\" ,\"#instadaily\" ,\"#picoftheday\" ,\"#french\" ,\"#travel\" ,\"#skydining\" ,\"#igtravel\" ,\"#signielhotels\" ,\"#signiel\" ,\"#korea\" ,\"#stayseoul\" ,\"#seoul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20688218_1724188611218150_2418528382531665920_n.jpg\"}, { \"count\" : \"17\" \"tag\" : [\"#remicone\" ,\"#seoul\" ,\"#icecream\" ,\"#gelato\" ,\"#vintage\" ,\"#prettythings\" ,\"#travelgram\" ,\"#travelblogger\" ,\"#travelphotography\" ,\"#citylife\" ,\"#art\" ,\"#wall\" ,\"#white\" ,\"#colors\" ,\"#ddp\" ,\"#lotte\" ,\"#래미콘\" ,\"#아이스크림\" ,\"#이쁘다\" ,\"#레미콘\" ,\"#서울\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c135.0.810.810/20687248_278990705842218_5369932726117859328_n.jpg\"}, { \"count\" : \"18\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c135.0.810.810/20759159_460014811041947_200145579072815104_n.jpg\"}, { \"count\" : \"19\" \"tag\" : [\"#seoul\" ,\"#orlando\" ,\"#houston\" ,\"#milan\" ,\"#vancouver\" ,\"#riodejaneiro\" ,\"#atlanta\" ,\"#rome\" ,\"#boston\" ,\"#sandiego\" ,\"#bangkok\" ,\"#sanfrancisco\" ,\"#sydney\" ,\"#amsterdam\" ,\"#hongkong\" ,\"#madrid\" ,\"#berlin\" ,\"#lasvegas\" ,\"#tokyo\" ,\"#toronto\" ,\"#moscow\" ,\"#losangeles\" ,\"#barcelona\" ,\"#chicago\" ,\"#miami\" ,\"#istanbul\" ,\"#dubai\" ,\"#paris\" ,\"#london\" ,\"#nyc\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20766052_174270323117393_5176155990839525376_n.jpg\"}, { \"count\" : \"20\" \"tag\" : [\"#아카미\" ,\"#난방즈케\" ,\"#Beautiful\" ,\"#tuna\" ,\"#sushi \" ,\"#dinner\" ,\"#Seoul \" ,\"#Saturday\" ,\"#night\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/20687312_869011729914067_4526388556339347456_n.jpg\"}";
    private String insta_search_sample_result = "{\"item\":[{\"count\":\"1\",\n" +
            "\t\t  \"tag\":[\"#Korean\" ,\"#KoreanDrama\" ,\"#Kdrama\" ,\"#Seoul\" ,\"#Hallyu\" ,\"#KoreanHallyu\" ,\"#Drakor\" ,\"#Kpopindonesia\" ,\"#dagelan_kpop \" ,\"#kpopnews\" ,\"#kpopupdate Owner\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20838900_505099349870562_1406638455575805952_n.jpg\"},\n" +
            "\t\t {\"count\":\"2\",\n" +
            "\t\t  \"tag\":[], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20901895_1481191188570405_3180841294675050496_n.jpg\"},\n" +
            "\t\t {\"count\":\"3\",\n" +
            "\t\t  \"tag\":[], \n" +
            "\t\t   \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20905701_1924928391097751_4179837978916421632_n.jpg\"},\n" +
            "\t\t {\"count\":\"4\",\n" +
            "\t\t  \"tag\":[\"#صباح_العربية\" ,\"#العربية_في_كوريا\" ,\"#بارك_شين_هاي\" ,\"#شينهاي\" ,\"#ممثلة\" ,\"#الدراما_الكورية\" ,\"#لقاء\" ,\"#مقابلة\" ,\"#العربية\" ,\"#العربية_في_كوريا\" ,\"#الورثة\" ,\"#بينوكيو\" ,\"#الاطباء\" ,\"#kpop\" ,\"#kdrama\" ,\"#korea\" ,\"#interview\" ,\"#seoul\" ,\"#parkshinhye\" ,\"#doctors\" ,\"#theheirs\" ,\"#you'rebeautiful\" ,\"#goongs\" ,\"#shinhye\" ,\"#interview\" ,\"#alarabiya\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20838677_1226809214131559_755863297193934848_n.jpg\"},\n" +
            "\t\t {\"count\":\"5\",\n" +
            "\t\t  \"tag\":[], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20968739_276719042829393_5735535799940677632_n.jpg\"},\n" +
            "\t\t {\"count\":\"6\",\n" +
            "\t\t  \"tag\":[], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20838608_1945787875643877_7591933017321373696_n.jpg\"},\n" +
            "\t\t {\"count\":\"7\",\n" +
            "\t\t  \"tag\":[], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20838619_132496224032661_2089016691991248896_n.jpg\"},\n" +
            "\t\t {\"count\":\"8\",\n" +
            "\t\t  \"tag\":[\"#myeongdong\" ,\"#seoul\" ,\"#teddylove\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20968993_507737126240439_621089190540476416_n.jpg\"},\n" +
            "\t\t  {\"count\":\"9\",\n" +
            "\t\t   \"tag\":[], \n" +
            "\t\t   \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20901991_276870052795191_8901948624431218688_n.jpg\"},\n" +
            "\t\t {\"count\":\"10\",\n" +
            "\t\t  \"tag\":[\"#63building\" ,\"#korea\" ,\"#southkorea\" ,\"#seoul\" ,\"#travel\" ,\"#sightseeing\" ,\"#sky\" ,\"#63square\" ,\"#art\" ,\"#installation\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/20901961_2016687641884325_1359118318937571328_n.jpg\"},\n" +
            "\t\t {\"count\":\"11\",\n" +
            "\t\t  \"tag\":[\"#seoul\" ,\"#korea\" ,\"#incheonairport\" ,\"#hongkong\" ,\"#concert\" ,\"#band\" ,\"#fnc\" ,\"#boice\" ,\"#cnblue\" ,\"#인천공항\" ,\"#출국\" ,\"#홍콩\" ,\"#밴드\" ,\"#이종현\" ,\"#이정신\" ,\"#강민혁\" ,\"#씨엔블루\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/e15/c0.90.720.720/20986670_532813597065408_1738844636799041536_n.jpg\"},\n" +
            "\t\t {\"count\":\"12\",\n" +
            "\t\t  \"tag\":[\"#INSTADAILY\" ,\"#INSTA\" ,\"#SEOUL\" ,\"#INSTAGRAM\" ,\"#SOUTHKOREA\" ,\"#COOKING\" ,\"#FOOD\" ,\"#INSTAFOOD\" ,\"#KOREA\" ,\"#먹스타그램\" ,\"#먹스타\" ,\"#유기농\" ,\"#먹거리\" ,\"#서울\" ,\"#사진\" ,\"#picture\" ,\"#먹빵\" ,\"#인스타데일리\" ,\"#건강식\" ,\"#요리\" ,\"#건강\" ,\"#부자\" ,\"#평생부자\" ,\"#미래준비\" ,\"#passiveincome\" ,\"#travel\" ,\"#여행\" ,\"#사업\" ,\"#business\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c120.0.839.839/20905322_275503059616602_4536997731150856192_n.jpg\"},\n" +
            "\t\t {\"count\":\"13\",\n" +
            "\t\t  \"tag\":[\"#evening\" ,\"#afterwork\" ,\"#friday\" ,\"#me\" ,\"#instagram\" ,\"#selfie \" ,\"#пятница\" ,\"#вечер\" ,\"#послеработы\" ,\"#я\" ,\"#инстаграм\" ,\"#селфи\" ,\"#셀스타그램\" ,\"#셀카\" ,\"#인스타그램\" ,\"#인스타\" ,\"#데일리\" ,\"#토요일\" ,\"#seoul\uD83C\uDDF0\uD83C\uDDF7\" ,\"#spb\" ,\"#almaty\" ,\"#seoul\" ,\"#맞팔\" ,\"#russia\" ,\"#korea\" ,\"#kazakhstan\" ,\"#서울\" ,\"#부산\" ,\"#한국\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20839063_1730193533951839_8049192003205857280_n.jpg\"},\n" +
            "\t\t {\"count\":\"14\",\n" +
            "\t\t  \"tag\":[\"#Asia\" ,\"#southkorea\" ,\"#seoul\" ,\"#apple\" ,\"#iphonese\" ,\"#photography\" ,\"#animals\" ,\"#otters\" ,\"#turtle\" ,\"#fish\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20905263_1921416241442944_401280078660501504_n.jpg\"},\n" +
            "\t\t {\"count\":\"15\",\n" +
            "\t\t  \"tag\":[\"#peaceminusone\" ,\"#peaceminusoneseoul\" ,\"#Korea\" ,\"#seoul\" ,\"#kwonjiyong\" ,\"#jiyong\" ,\"#gd\" ,\"#gdragon\" ,\"#피스마이너스원\" ,\"#피마원\" ,\"#한국\" ,\"#서울\" ,\"#권지용\" ,\"#지용\" ,\"#지디\" ,\"#지드래곤\" ,\"#ジヨン\" ,\"#もおすぐ会える\" ,\"#今から福岡いってきます\" ,\"#やっとイルコン始まった\" ,\"#待ってました\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20904850_1848570335458934_5562097302471966720_n.jpg\"},\n" +
            "\t\t {\"count\":\"16\",\n" +
            "\t\t  \"tag\":[\"#seoul\" ,\"#publiclibrary\" ,\"#korea2017\" ,\"#travel2017\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/c257.0.565.565/20838685_338057883312795_5465921512856879104_n.jpg\"},\n" +
            "\t\t {\"count\":\"17\",\n" +
            "\t\t  \"tag\":[\"#63building\" ,\"#korea\" ,\"#southkorea\" ,\"#seoul\" ,\"#travel\" ,\"#sightseeing\" ,\"#architecture\" ,\"#sky\" ,\"#63square\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/20968676_1704669716508877_1554648311405740032_n.jpg\"},\n" +
            "\t\t {\"count\":\"18\",\n" +
            "\t\t  \"tag\":[\"#canon\" ,\"#eosm3\" ,\"#canoneosm3\" ,\"#canonm3\" ,\"#japan\" ,\"#discoverjapan\" ,\"#beautifuljapan\" ,\"#retrip_nippon\" ,\"#写真が好きな人と繋がりたい\" ,\"#写真を撮ってる人と繋がりたい\" ,\"#ファインダー越しの私の世界\" ,\"#seoul\" ,\"#catslover\" ,\"#ソウル\" ,\"#myongdong\" ,\"#明洞\" ,\"#猫\" ,\"#catstagram\" ,\"#韓国\" ,\"#旅行\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20902148_1139593289476181_8678383155628998656_n.jpg\"},\n" +
            "\t\t {\"count\":\"19\",\n" +
            "\t\t  \"tag\":[\"#korea\" ,\"#southkorea\" ,\"#northkorea\" ,\"#dmz\" ,\"#travel\" ,\"#warhistory\" ,\"#seoul\" ,\"#exchangestudent\" ,\"#studyabroad\" ,\"#韓国\" ,\"#朝鮮\" ,\"#留学生\" ,\"#留学\" ,\"#旅行\" ,\"#日本語\" ,\"#日本\" ,\"#ソウル\" ,\"#歴史\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c94.0.892.892/20838834_114305912566823_5094925897140535296_n.jpg\"},\n" +
            "\t\t {\"count\":\"20\",\n" +
            "\t\t  \"tag\":[\"#셀카\" ,\"#셀스타그램\" ,\"#얼스타그램\" ,\"#selca\" ,\"#instasize\" ,\"#iphone7plus\" ,\"#selfie\" ,\"#성수동카페\" ,\"#대림창고\" ,\"#서울\" ,\"#여행\" ,\"#daily\" ,\"#dailylook\" ,\"#소통\" ,\"#좋아요\" ,\"#선팔\" ,\"#맞팔\" ,\"#seoul\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.117.937.937/20837267_110802336267205_8112631320162598912_n.jpg\"},\n" +
            "\t\t {\"count\":\"21\",\n" +
            "\t\t  \"tag\":[\"#GD\" ,\"#Seoul\" ,\"#Fukuoka\" ,\"#motteinfukuoka . . . ___________________(cr.\" ,\"#BIGBANG11 \" ,\"#GDRAGON\" ,\"#gd\" ,\"#kwonjiyong\" ,\"#kwonleader\" ,\"#PEACEMINUSONE\" ,\"#권지용\" ,\"#權志龍 @xxxibgdrgn  \" ,\"#TOP\" ,\"#tabi\" ,\"#Choiseunghyun\" ,\"#崔勝鉉\" ,\"#최승현@choi_seung_hyun_tttop \" ,\"#TAEYANG\" ,\"#youngbae\" ,\"#東永裴\" ,\"#동영배 @__youngbae__\" ,\"#DAESUNG\" ,\"#dlite\" ,\"#姜大聲\" ,\"#강대성 \" ,\"#SEUNGRI\" ,\"#vi\" ,\"#李昇賢\" ,\"#이승현\" ], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20838921_351700961925566_1519065636934778880_n.jpg\"},]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_search);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String keyword = intent.getStringExtra(MainActivity.KEYWORD_INSTA);

        // Capture the layout's TextView and set the string as its text
        TextView tv_insta_search_result = (TextView) findViewById(R.id.tv_insta_search_result);
        tv_insta_search_result.setText("keyword : " + keyword);

        //TODO 현재 url 연결 실패
        String url = "http://147.46.114.98:3000/search_insta_sample";

        //ContentValues param = new ContentValues();

        //param.put("keyword", keyword);

        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);
            System.out.println("Instga Result 1: " + result);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Log.d("onPost", s);
            System.out.println("Instga Result : " + s);

            //[TODO] need to fix this declaration.
            TextView tv_insta_search_result = (TextView) findViewById(R.id.tv_insta_search_result);
            //tv_insta_search_result.setText(s);

            tv_insta_search_result.setText(insta_search_sample_result);

            //TODO JSON Data 처리해서 그리드뷰에 사진 보여주고 -> 사진 클릭 시 해시 태그 등 상세 정보 액티비티로 이동
            JSONParser_Parse(s);
        }

        void JSONParser_Parse(String str){
            BlogListAdapter mMyAdapter = new BlogListAdapter();

            try {
                JSONObject jObject = new JSONObject(str);
//                JSONObject item = jObject.getJSONObject("item");
                JSONArray itemArray = jObject.getJSONArray("item");
                for(int i=0; i < itemArray.length(); i++) {
                    JSONObject itemInstance = itemArray.getJSONObject(i);  // JSONObject 추출

                    int count = itemInstance.getInt("count");
                    String link = itemInstance.getString("link");

                    System.out.println("Item[" + i + "]" +" count : " + count + " link : " + link);

                    JSONArray tagArray = itemInstance.getJSONArray("tag");
                    String tags[] = new String[tagArray.length()];
                    for(int j = 0; j < tagArray.length(); j ++){
                        tags[j] = tagArray.getString(j).toString();
                        System.out.println("tag["+j+"] : " + tags[j]);
                    }

//                    mMyAdapter.addItem(imgLink, title, description, bloggerName + "\n" + postDate, link);
                }

                /* 리스트뷰에 어댑터 등록 */
//                mListView.setAdapter(mMyAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



    }
}
