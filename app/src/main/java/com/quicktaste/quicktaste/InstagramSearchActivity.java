package com.quicktaste.quicktaste;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InstagramSearchActivity extends AppCompatActivity {
    private GridView mGridView;

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
            "\t\t  \"tag\":[\"\"], \n" +
            "\t\t  \"link\":\"https://scontent-icn1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20838921_351700961925566_1519065636934778880_n.jpg\"}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_search);

        /* get the gridview object */
        mGridView = (GridView)findViewById(R.id.insta_search_gridview);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String keyword = intent.getStringExtra(MainActivity.KEYWORD_INSTA);

        // Capture the layout's TextView and set the string as its text
        TextView tv_insta_search_result = (TextView) findViewById(R.id.tv_insta_search_result);
        tv_insta_search_result.setText("keyword : " + keyword);

        //TODO initial screen => show saved the last research result
        JSONParser_Parse(insta_search_sample_result);


//        String url = "http://147.46.114.98:3000/search_insta";
//
//        ContentValues param = new ContentValues();
//
//        param.put("keyword", keyword);
//
//        NetworkTask networkTask = new NetworkTask(url, param);
//        networkTask.execute();
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

            //TODO JSON Data 처리해서 그리드뷰에 사진 보여주고 -> 사진 클릭 시 해시 태그 등 상세 정보 액티비티로 이동
            JSONParser_Parse(s);
        }

    }


    void JSONParser_Parse(String str){
        InstagramGridAdapter mMyAdapter = new InstagramGridAdapter();

        try {
            JSONObject jObject = new JSONObject(str);
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
//                    System.out.println("tag["+j+"] : " + tags[j]);
//                        if(tagArray.getString(j).isEmpty())
//                            System.out.println("empty tags");
                }

                mMyAdapter.addItem(count, tags, link);
            }

                /* 그리드뷰에 어댑터 등록 */
            mGridView.setAdapter(mMyAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
