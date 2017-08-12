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
    private String insta_search_sample_result = "{ \"count\" : \"1\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20687927_869109316599100_2149211513343180800_n.jpg\"}, \n" +
            "{ \"count\" : \"2\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c125.0.750.750/20759193_166882123877208_2079021388265422848_n.jpg\"}, \n" +
            "{ \"count\" : \"3\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c107.0.866.866/20766125_2098374873522186_9042552217181093888_n.jpg\"}, \n" +
            "{ \"count\" : \"4\" \"tag\" : [\"#강남\" ,\"#Korea\" ,\"#SouthKorea\" ,\"#Seoul\" ,\"#Gangnam\" ,\"#Corea\" ,\"#CoreadelSur\" ,\"#Seul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/c80.0.480.480/20688539_107926883260265_983156419651960832_n.jpg\"}, \n" +
            "{ \"count\" : \"5\" \"tag\" : [\"#비치클럽에코\" ,\"#ECCO 맥주는\" ,\"#DESPERADOS\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/e15/20766979_467479023611693_1408298370241396736_n.jpg\"}, \n" +
            "{ \"count\" : \"6\" \"tag\" : [\"#vante\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20759554_262946624221636_2693829141890859008_n.jpg\"}, \n" +
            "{ \"count\" : \"7\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20688739_469435896767166_6201756527146041344_n.jpg\"}, \n" +
            "{ \"count\" : \"8\" \"tag\" : [\"#aboutlastnight\" ,\"#seoul\" ,\"#dongdaemun\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20766196_272054373294150_2825086507847516160_n.jpg\"},\n" +
            "{ \"count\" : \"9\" \"tag\" : [\"#somuchlove\" ,\"#thankyouall\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20688637_734826856704803_9174899456858914816_n.jpg\"},\n" +
            "{ \"count\" : \"10\" \"tag\" : [\"#서울\" ,\"#이태원\" ,\"#리차드카피캣\" ,\"#봄베이\" ,\"#봄베이사파이어\" ,\"#티본스테이크\" ,\"#seoul\" ,\"#itaewon\" ,\"#richardcopycat\" ,\"#bombay\" ,\"#bombaysaphire\" ,\"#tbornsteak\" ,\"#with\" ,\"#friends\" ,\"#and\" ,\"#GF\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20766512_110988369570871_6002275924974764032_n.jpg\"}, \n" +
            "{ \"count\" : \"11\" \"tag\" : [\"#mykoreanjagiya\" ,\"#heartevangelista\" ,\"#philippines\" ,\"#daebak\" ,\"#saranghaeyo\" ,\"#ukiss\" ,\"#seoul\" ,\"#manila\" ,\"#southkorea\" ,\"#3peasinapod\" ,\"#moorimschool\" ,\"#gwapo\" ,\"#halohalo\" ,\"#denizen\" ,\"#sillyboy\" ,\"#instagwapo\" ,\"#hottest\" ,\"#oppa\" ,\"#in\" ,\"#town\" ,\"#welovexander\" ,\"#bebot\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.90.720.720/20766874_347275635727621_3799949903996649472_n.jpg\"},\n" +
            "{ \"count\" : \"12\" \"tag\" : [\"#ProjectUlssigu2017\" ,\"#Norrikkot\" ,\"#프로젝트얼씨구\" ,\"#노리꽃\" ,\"#얼씨구좋다\" ,\"#서울\" ,\"#IndependenceDayKorea\" ,\"#광복절\" ,\"#프로젝트얼씨구2017\" ,\"#Seoul\" ,\"#Korea\" ,\"#서울\" ,\"#한국\" ,\"#풍물\" ,\"#TraditionalKoreanmusic\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c55.0.969.969/20759104_1613521895333975_8703245295376400384_n.jpg\"},\n" +
            "{ \"count\" : \"13\" \"tag\" : [\"#Repost\" ,\"#EtudeHouse\" ,\"#flagship\" ,\"#store\" ,\"#DalzielPow\" ,\"#Seoul\" ,\"#SouthKorea\" ,\"#cosmetics\" ,\"#retaildesign\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20687797_2185831501643215_354195382986604544_n.jpg\"},\n" +
            "{ \"count\" : \"14\" \"tag\" : [\"#goodnight\" ,\"#dailypic\" ,\"#seoul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/e15/20837243_1435854993118099_3131734919240220672_n.jpg\"},\n" +
            "{ \"count\" : \"15\" \"tag\" : [\"#speakeasybar\" ,\"#스피크이지\" ,\"#앨리스바\" ,\"#르체임버\" ,\"#청담동\" ,\"#Seoul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20688767_1888068401512624_1832042390580363264_n.jpg\"},\n" +
            "{ \"count\" : \"16\" \"tag\" : [\"#thankyou\" ,\"#love\" ,\"#bdaycelebration\" ,\"#blessed\" ,\"#cake\" ,\"#birthdaycake\" ,\"#instagood\" ,\"#igers\" ,\"#nomnom\" ,\"#sweet\" ,\"#foodlovers\" ,\"#foodporn\" ,\"#dessert\" ,\"#foodpics\" ,\"#instadaily\" ,\"#picoftheday\" ,\"#french\" ,\"#travel\" ,\"#skydining\" ,\"#igtravel\" ,\"#signielhotels\" ,\"#signiel\" ,\"#korea\" ,\"#stayseoul\" ,\"#seoul\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/20688218_1724188611218150_2418528382531665920_n.jpg\"},\n" +
            "{ \"count\" : \"17\" \"tag\" : [\"#remicone\" ,\"#seoul\" ,\"#icecream\" ,\"#gelato\" ,\"#vintage\" ,\"#prettythings\" ,\"#travelgram\" ,\"#travelblogger\" ,\"#travelphotography\" ,\"#citylife\" ,\"#art\" ,\"#wall\" ,\"#white\" ,\"#colors\" ,\"#ddp\" ,\"#lotte\" ,\"#래미콘\" ,\"#아이스크림\" ,\"#이쁘다\" ,\"#레미콘\" ,\"#서울\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c135.0.810.810/20687248_278990705842218_5369932726117859328_n.jpg\"},\n" +
            "{ \"count\" : \"18\" \"tag\" : [] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c135.0.810.810/20759159_460014811041947_200145579072815104_n.jpg\"}, \n" +
            "{ \"count\" : \"19\" \"tag\" : [\"#seoul\" ,\"#orlando\" ,\"#houston\" ,\"#milan\" ,\"#vancouver\" ,\"#riodejaneiro\" ,\"#atlanta\" ,\"#rome\" ,\"#boston\" ,\"#sandiego\" ,\"#bangkok\" ,\"#sanfrancisco\" ,\"#sydney\" ,\"#amsterdam\" ,\"#hongkong\" ,\"#madrid\" ,\"#berlin\" ,\"#lasvegas\" ,\"#tokyo\" ,\"#toronto\" ,\"#moscow\" ,\"#losangeles\" ,\"#barcelona\" ,\"#chicago\" ,\"#miami\" ,\"#istanbul\" ,\"#dubai\" ,\"#paris\" ,\"#london\" ,\"#nyc\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20766052_174270323117393_5176155990839525376_n.jpg\"},\n" +
            "{ \"count\" : \"20\" \"tag\" : [\"#아카미\" ,\"#난방즈케\" ,\"#Beautiful\" ,\"#tuna\" ,\"#sushi \" ,\"#dinner\" ,\"#Seoul \" ,\"#Saturday\" ,\"#night\" ] \"link\" : \"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/e35/20687312_869011729914067_4526388556339347456_n.jpg\"}";

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
        }



    }
}
