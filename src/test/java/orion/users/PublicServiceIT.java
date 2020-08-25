/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package orion.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.apache.commons.httpclient.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import orion.users.service.PublicService;

/**
 * Integration test
 */
@ExtendWith({ DockerCompose.class })
public class PublicServiceIT {

    public static PublicService publicSvc;

    private static String API = "/orion-users-service/users/api/v1/";

    private String host;
    private Integer port;

    private CloseableHttpClient client;

    public PublicServiceIT() {
        this.client = HttpClients.createDefault();
        host = DockerCompose.users.getContainerIpAddress();
        port = DockerCompose.users.getFirstMappedPort();
    }

    //4 correct codes, 4 incorrect codes

    //SHOULD WORK
    @Test
    public void testCreate() {
        try {

            // Mounting URL, create
            String url = "http://" + host + ":" + port + API + "create";

            HttpPost post = new HttpPost(url);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", "1"));
            params.add(new BasicNameValuePair("name", "John"));
            params.add(new BasicNameValuePair("email", "emaillll"));
            params.add(new BasicNameValuePair("password", "pass"));
            post.setEntity(new UrlEncodedFormEntity(params));

            // execute and getting the response
            HttpResponse response = this.client.execute(post);

            // Get response body
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println("testCreate >>>>>>>>>>>>" + content);

            assertEquals(response.getStatusLine().getStatusCode(), 200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //SHOULD WORK
    @Test
    public void testUpdate() {
        try {

            // Mounting URL, create
            String url = "http://" + host + ":" + port + API + "update";

            HttpPost post = new HttpPost(url);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", "1"));
            params.add(new BasicNameValuePair("name", "marcus"));
            params.add(new BasicNameValuePair("email", "emaillll"));
            params.add(new BasicNameValuePair("password", "passe"));
            post.setEntity(new UrlEncodedFormEntity(params));

            // execute and getting the response
            HttpResponse response = this.client.execute(post);

            // Get response body
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println("testUpdate >>>>>>>>>>>>" + content);

            assertEquals(response.getStatusLine().getStatusCode(), 200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //SHOULD WORK
    @Test   
     public void testRead() {
        try {

            String id = "1";
            // Mounting URL, create
            String url = "http://" + host + ":" + port + API + "/list/" + 1;


           
            HttpGet get = new HttpGet(url);

            // execute and getting the response
            HttpResponse response = this.client.execute(get);


            //Get response body
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println("testRead >>>>>>>>>>>>" + content);

            assertEquals(response.getStatusLine().getStatusCode(), 200);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //SHOULD WORK
    @Test   
    public void testDelete() {
       try {

          
           // Mounting URL, create
           String url = "http://" + host + ":" + port + API + "/delete/";


          
           HttpPost post = new HttpPost(url);

           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair("id", "1"));
           post.setEntity(new UrlEncodedFormEntity(params));

           // execute and getting the response
           HttpResponse response = this.client.execute(post);


           //Get response body
           HttpEntity entity = response.getEntity();
           String content = EntityUtils.toString(entity);
           System.out.println("testDelete >>>>>>>>>>>>" + content);

           assertEquals(response.getStatusLine().getStatusCode(), 200);
           
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   //SHOULD SHOW ERROR
   @Test
   public void testNullCreate() {
       try {

           // Mounting URL, create
           String url = "http://" + host + ":" + port + API + "create";

           HttpPost post = new HttpPost(url);

           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair("id", ""));
           params.add(new BasicNameValuePair("name", ""));
           params.add(new BasicNameValuePair("email", ""));
           params.add(new BasicNameValuePair("password", ""));
           post.setEntity(new UrlEncodedFormEntity(params));

           // execute and getting the response
           HttpResponse response = this.client.execute(post);

           // Get response body
           HttpEntity entity = response.getEntity();
           String content = EntityUtils.toString(entity);
           System.out.println("testNullCreate >>>>>>>>>>>>" + content);

           assertEquals(response.getStatusLine().getStatusCode(), 200);

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   //SHOULD SHOW ERROR
   @Test
   public void testWithoutParamsCreate() {
       try {

           // Mounting URL, create
           String url = "http://" + host + ":" + port + API + "create";

           HttpPost post = new HttpPost(url);

           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair("id", "1"));
           params.add(new BasicNameValuePair("name", "philip"));

           post.setEntity(new UrlEncodedFormEntity(params));

           // execute and getting the response
           HttpResponse response = this.client.execute(post);

           // Get response body
           HttpEntity entity = response.getEntity();
           String content = EntityUtils.toString(entity);
           System.out.println("testWithoutParamsCreate >>>>>>>>>>>>" + content);

           assertEquals(response.getStatusLine().getStatusCode(), 200);

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   //SHOULD SHOW ERROR
   @Test
   public void testSameEmailCreate_A() {
       try {

           // Mounting URL, create
           String url = "http://" + host + ":" + port + API + "create";

           HttpPost post = new HttpPost(url);

           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair("id", "2"));
           params.add(new BasicNameValuePair("name", "mike"));
           params.add(new BasicNameValuePair("email", "sameemail"));
           params.add(new BasicNameValuePair("password", "pass"));
           post.setEntity(new UrlEncodedFormEntity(params));

           // execute and getting the response
           HttpResponse response = this.client.execute(post);

           // Get response body
           HttpEntity entity = response.getEntity();
           String content = EntityUtils.toString(entity);
           System.out.println("testSameEmailCreate_A >>>>>>>>>>>>" + content);

           assertEquals(response.getStatusLine().getStatusCode(), 200);

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   //SHOULD SHOW ERROR
   @Test
   public void testSameEmailCreate_B() {
       try {

           // Mounting URL, create
           String url = "http://" + host + ":" + port + API + "create";

           HttpPost post = new HttpPost(url);

           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair("id", "2"));
           params.add(new BasicNameValuePair("name", "tyson"));
           params.add(new BasicNameValuePair("email", "sameemail"));
           params.add(new BasicNameValuePair("password", "pass"));
           post.setEntity(new UrlEncodedFormEntity(params));

           // execute and getting the response
           HttpResponse response = this.client.execute(post);

           // Get response body
           HttpEntity entity = response.getEntity();
           String content = EntityUtils.toString(entity);
           System.out.println("testSameEmailCreate_B >>>>>>>>>>>>" + content);

           assertEquals(response.getStatusLine().getStatusCode(), 200);

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}