package pl.cloudy.core.rest;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.joda.time.DateTime;
import pl.cloudy.constants.Global;
import pl.cloudy.core.models.ActionObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;


public class PostHelper
{
    public void postAction(ActionObject object)
    {
        try
        {
            URL url = new URL("http://localhost:8080/uploadAction");

            Map<String, Object> params = new LinkedHashMap<>();
            params.put("testRunId", object.getTestRunID());
            params.put("actionType", object.getActionType());
            params.put("className", object.getClassName());
            params.put("methodName", object.getMethodName());
            params.put("arguments", object.getArguments());
            params.put("startTimestamp", object.getStartTime());
            params.put("finishTimestamp", object.getFinishTime());
            params.put("returnValue", object.getReturnValue());
            params.put("screenshotBefore", object.getScreenshotBefore());

            byte[] postDataBytes = postContentFromParams(params);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            for (int c; (c = in.read()) >= 0; )
                System.out.print((char) c);


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void postImage(String fileName)
    {
        try
        {

            final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();


            final FileDataBodyPart filePart = new FileDataBodyPart("imageStream", new File(Global.SAVED_IMAGES_FOLDER + fileName));
            FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
            final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("imageFileName", fileName).bodyPart(filePart);

            final WebTarget target = client.target("http://localhost:8080/uploadImage");
            final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));

            final int responsedone = response.getStatus();

            formDataMultiPart.close();
            multipart.close();


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public int initializeTestRun(final String testName)
    {

        String testSuiteName = "all tests";

        int testRunId = 0;

        try
        {

            URL url = new URL("http://localhost:8080/initializeTestRun");

            DateTime dateTime = new DateTime();
            Timestamp startDate = new Timestamp(dateTime.getMillis());

            Map<String, Object> params = new LinkedHashMap<>();
            params.put("testName", testName);
            params.put("startTimestamp", startDate);
            //params.put("testSuite", testSuiteName);

            byte[] postDataBytes = postContentFromParams(params);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String response ="";
            for (int c; (c = in.read()) >= 0; )
                response += String.valueOf((char) c);

            testRunId = Integer.valueOf(response);

        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return testRunId;
    }

    private byte[] postContentFromParams(Map<String, Object> params) throws UnsupportedEncodingException
    {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet())
        {
            if (postData.length() != 0)
                postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return postData.toString().getBytes("UTF-8");
    }
}
