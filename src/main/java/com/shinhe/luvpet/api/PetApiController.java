package com.shinhe.luvpet.api;

import com.shinhe.luvpet.dto.SidoResponseDto;
import com.shinhe.luvpet.service.SidoService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetApiController {

    private final SidoService sidoService;


    @Value("${API-URL}")
    private String API_URL;

    @Value("${API-KEY}")
    private String API_KEY;

    @GetMapping("/getSido")
    public String getSido() throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder(API_URL).append("/sido"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="+API_KEY); /*Service Key*/
        //urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*한 페이지 결과 수(1,000 이하)*/
       // urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        conn.disconnect();

        List<SidoResponseDto> srdList=parseJson(result.toString());
        for(SidoResponseDto srd : srdList){
            System.out.println("srd: " + srd.getOrgCd()+" "+srd.getOrgdownNm());
        }
        sidoService.saveSido(srdList);
        return result.toString();
    }

    private List<SidoResponseDto> parseJson(String result) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        // 가장 큰 JSON 객체 response 가져오기
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");

        // 그 다음 body 부분 파싱
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");

        // 그 다음 위치 정보를 배열로 담은 items 파싱
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");

        // items는 JSON임, 이제 그걸 또 배열로 가져온다
        JSONArray jsonItemList = (JSONArray) jsonItems.get("item");

        List<SidoResponseDto> sidoResponseList = new ArrayList<>();
        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            int orgCd = Integer.parseInt(String.valueOf(item.get("orgCd")));
            String orgdownNm = (String) item.get("orgdownNm");
            sidoResponseList.add(new SidoResponseDto(orgCd, orgdownNm));
            System.out.println("orgdownNm: " + orgdownNm);
        }
        return sidoResponseList;
    }
}