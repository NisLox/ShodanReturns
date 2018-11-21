import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main (String[] args) {
        Main main = new Main();
//        main.findAll("bbc.co.uk");
//        main.findIp("bbc.co.uk");
//        main.shodanReturnDomainAndIP("bbc.co.uk");
        main.shodanReturnMinifiedBanner("bbc.co.uk");
//        main.shodanHoneypotScore("151.101.0.81");
    }

    private void findIp(String domain){
        try {
            System.out.println("------------------");
            System.out.println("FindSingleIp");
            System.out.println("------------------");
            System.out.println(InetAddress.getByName(domain));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void findAll(String domain) {
        InetAddress[] machines = new InetAddress[0];
        try {
            machines = InetAddress.getAllByName(domain);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("------------------");
        System.out.println("FindIPv4&IPv6");
        System.out.println("------------------");
        for(InetAddress address : machines){
            System.out.println(address.getHostAddress());
        }
    }

    private void shodanReturnDomainAndIP(String domain){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.shodan.io/dns/resolve?hostnames=" + domain + "&key=CqF3WCuJSXGLO8qxK3fnpUwmiOBzICiC")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "fb10b4eb-1aaf-4753-8de2-a1abd1acce71")
                .build();

        try {
            System.out.println("------------------");
            System.out.println("Return Domain and IP");
            System.out.println("------------------");
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shodanReturnMinifiedBanner(String domain){
            InetAddress tempIpAddress;
            String ipAddress = null;
        try {
            tempIpAddress = InetAddress.getByName(domain);

            ipAddress = tempIpAddress.getHostAddress();
        } catch (UnknownHostException e) {

        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.shodan.io/shodan/host/" + ipAddress + "?key=CqF3WCuJSXGLO8qxK3fnpUwmiOBzICiC&minify=true")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "658f75e1-41c6-4738-b153-fd533ba56407")
                .build();

        try {
            System.out.println("------------------");
            System.out.println("Return Banner");
            System.out.println("------------------");
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shodanHoneypotScore(String ipAddress){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.shodan.io/labs/honeyscore/" + ipAddress + "?key=CqF3WCuJSXGLO8qxK3fnpUwmiOBzICiC")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "666d9968-ca86-4c7d-8920-a2b68bc205d3")
                .build();

        try {
            System.out.println("------------------");
            System.out.println("Honeypot rating");
            System.out.println("------------------");
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
