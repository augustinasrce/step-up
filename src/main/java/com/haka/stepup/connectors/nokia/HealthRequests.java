package com.haka.stepup.connectors.nokia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haka.stepup.connectors.nokia.domain.ActivityData;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.QueryStringSigningStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HealthRequests {

    private String consumer_key,consumer_secret, access_token, access_secret, uri,signed_uri  ;

    private OAuthConsumer consumer;

    private HttpClient httpclient;
    private HttpGet httpget;


    public HealthRequests(){
        this.consumer_key = "0752617bb681e415e89ec16f4fbfb6765e35ef342dbd1bde90f0b4b8b09";
        this.consumer_secret = "d410dc425d97dceee1c3624f25c2bbee55376ce1fdd2f15277b2f57609d09133";
        this.access_token = "";
        this.access_secret= "";

        this.uri = "https://api.health.nokia.com/v2/measure?";
        this.signed_uri = null;

        this.consumer = new CommonsHttpOAuthConsumer(consumer_key, consumer_secret);

//        consumer.set

        this.httpclient = HttpClientBuilder.create().build();
        this.httpget = new HttpGet(uri);

//        consumer.setTokenWithSecret(access_token, access_secret);
    }




    // String startDate,String endDate,String lastUpdate
    public ActivityData getBodyMeasures () throws IOException {
        this.uri = "https://api.health.nokia.com/v2/measure?";

        this.consumer.setTokenWithSecret(access_token, access_secret);
        this.uri+= "action=getactivity&startdateymd=2018-05-01&enddateymd=2018-05-03";

        signURL();

        String outJson =  request();

        Gson gson = new GsonBuilder().create();
        ActivityData activityData = gson.fromJson(outJson, ActivityData.class);

        System.out.println(activityData);
        return activityData;

    }

    public void getBodyMeasures (long lastUpdate) throws IOException {

        this.consumer.setTokenWithSecret(access_token, access_secret);

        this.uri+= "action=getactivity&lastupdate="+lastUpdate;

        this.signURL();

        this.request();
    }

    public void getBodyMeasures (long startDate, long endDate) throws IOException {

        this.consumer.setTokenWithSecret(access_token, access_secret);

        this.uri += "action=getactivity&startdate="+startDate+"&enddate="+endDate;

        this.signURL();

        this.request();
    }


    private void signURL(){

        try {
            this.signed_uri = consumer.sign(uri);
            this.httpget = new HttpGet(signed_uri);
        } catch (OAuthMessageSignerException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE,       null, ex);
        } catch (OAuthExpectationFailedException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthCommunicationException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String request () throws IOException{
        System.out.println(this.signed_uri);
        HttpResponse response = httpclient.execute(httpget);
        System.out.println(response.getStatusLine().toString());
        HttpEntity entity = response.getEntity();
        System.out.println();
        String outJson = EntityUtils.toString(entity);
        System.out.println(outJson);
        return outJson;
    }

    public String getAuthorizationToken(String userId) throws IOException {
        this.consumer = new CommonsHttpOAuthConsumer(consumer_key,consumer_secret);
        this.consumer.setTokenWithSecret(access_token, access_secret);
        this.uri = "https://developer.health.nokia.com/account/access_token";

        HttpParameters reqHttpParameters = new HttpParameters();




        consumer.setAdditionalParameters(reqHttpParameters);

        this.signURL();

        System.out.println(signed_uri);

        String out = this.request();

        Map<String, String> outMap = splitQuery(out);

        this.access_token = outMap.get("oauth_token");

        this.access_secret = outMap.get("oauth_token_secret");

        return out;

    }

    public String authorize() throws IOException {

        consumer.setSigningStrategy(new QueryStringSigningStrategy());

        this.uri = "https://developer.health.nokia.com/account/request_token";

        HttpParameters reqHttpParameters = new HttpParameters();

        reqHttpParameters.put("oauth_callback", URLEncoder.encode("http://localhost:8080/authentication", "UTF-8"));

        consumer.setAdditionalParameters(reqHttpParameters);



        this.signURL();
        System.out.println(signed_uri);


        String out = this.request();

        this.consumer = new CommonsHttpOAuthConsumer(consumer_key,consumer_secret);
//
        Map<String, String> outputString = splitQuery(out);
//
        this.uri = "https://developer.health.nokia.com/account/authorize";
//
        System.out.println( outputString.get("oauth_token_secret"));

        this.access_secret = outputString.get("oauth_token_secret");
        this.access_token = outputString.get("oauth_token");

        this.consumer.setTokenWithSecret(access_token, access_secret);

       signURL();

        System.out.println(signed_uri);

        return signed_uri;

    }

    private static Map<String, String> splitQuery(String url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url;
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
//    {"status":0,"message":"Invalid signature :\n iv1uLx3zq7izQk+tniTS\/T0sy2A= ..  \n
// {\"oauth_consumer_key\":\"0752617bb681e415e89ec16f4fbfb6765e35ef342dbd1bde90f0b4b8b09\",\
// "oauth_nonce\":\"1233220106472980863\",\
// "oauth_signature\":\"iv1uLx3zq7izQk+tniTS\\\/T0sy2A=\",\
// "oauth_signature_method\":\"HMAC-SHA1\",\
// "oauth_timestamp\":\"1525356457\",\
// "oauth_token\":\"74d0c2870d60e5ee57118455204fdeb70188f5d21350e9495f34ecf7020e\",\
// "oauth_version\":\"1.0\"}\n{\
// "base_string\":\"GET&https%3A%2F%2Fdeveloper.health.nokia.com%2Faccount%2Frequest_token&oauth_consumer_key%3D0752617bb681e415e89ec16f4fbfb6765e35ef342dbd1bde90f0b4b8b09%26oauth_nonce%3D1233220106472980863%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1525356457%26oauth_token%3D74d0c2870d60e5ee57118455204fdeb70188f5d21350e9495f34ecf7020e%26oauth_version%3D1.0\"}\n{\"key\":\"0752617bb681e415e89ec16f4fbfb6765e35ef342dbd1bde90f0b4b8b09\",\"secret\":\"d410dc425d97dceee1c3624f25c2bbee55376ce1fdd2f15277b2f57609d09133\",\"callback_url\":null}"}

}