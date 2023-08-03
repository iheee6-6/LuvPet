package com.shinhe.luvpet.api;

import com.shinhe.luvpet.domain.Shelter;
import com.shinhe.luvpet.dto.LocationResponseDto;
import com.shinhe.luvpet.dto.PetKindResponseDto;
import com.shinhe.luvpet.dto.PetNoticeDto;
import com.shinhe.luvpet.dto.ShelterResponseDto;
import com.shinhe.luvpet.service.LocationService;
import com.shinhe.luvpet.service.PetService;
import com.shinhe.luvpet.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    private final LocationService locationService;
    private final ShelterService shelterService;
    private final PetService petService;


    @Value("${API-URL}")
    private String API_URL;

    @Value("${API-KEY}")
    private String API_KEY;

    @GetMapping("/getSido")
    public String getSido() throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder(API_URL).append("/sido"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + API_KEY); /*Service Key*/
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

        List<LocationResponseDto> srdList = locationParseJson(result.toString());
        locationService.saveSido(srdList);
        return result.toString();
    }

    @GetMapping("/getSigungu")
    public String getShelter(@RequestParam("uprCd") int uprCd) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder(API_URL).append("/sigungu"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + API_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(uprCd), "UTF-8")); /*한 페이지 결과 수(1,000 이하)*/
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

        List<LocationResponseDto> srdList = locationParseJson(result.toString());
        locationService.saveSido(srdList);
        return result.toString();
    }

    @GetMapping("/getShelter")
    public String getShelter(@RequestParam("uprCd") int uprCd,
                             @RequestParam("orgCd") int orgCd) throws IOException, ParseException { // ?uprCd=6110000&orgCd=3220000

        StringBuilder urlBuilder = new StringBuilder(API_URL).append("/shelter"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + API_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(uprCd), "UTF-8")); /*한 페이지 결과 수(1,000 이하)*/
        urlBuilder.append("&" + URLEncoder.encode("org_cd", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(orgCd), "UTF-8")); /*한 페이지 결과 수(1,000 이하)*/
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

        List<ShelterResponseDto> srdList = shelterParseJson(result.toString());
        shelterService.saveShelter(srdList, uprCd);
        return result.toString();
    }

    private List<ShelterResponseDto> shelterParseJson(String result) throws ParseException {
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

        List<ShelterResponseDto> shelterResponseList = new ArrayList<>();
        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            long careRegNo = Long.parseLong(String.valueOf(item.get("careRegNo")));
            String careNm = (String) item.get("careNm");

            shelterResponseList.add(new ShelterResponseDto(careRegNo, careNm));

        }
        return shelterResponseList;
    }

    private List<LocationResponseDto> locationParseJson(String result) throws ParseException {
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

        List<LocationResponseDto> sidoResponseList = new ArrayList<>();
        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            int orgCd = Integer.parseInt(String.valueOf(item.get("orgCd")));
            String orgdownNm = (String) item.get("orgdownNm");
            if (item.containsKey("uprCd")) {
                int uprCd = Integer.parseInt(String.valueOf(item.get("uprCd")));
                sidoResponseList.add(new LocationResponseDto(uprCd, orgCd, orgdownNm));
            } else {
                sidoResponseList.add(new LocationResponseDto(orgCd, orgdownNm));
            }
        }
        return sidoResponseList;
    }

    @GetMapping("/kind")
    public String getPetKindAPI() throws IOException, ParseException { // ?uprCd=6110000&orgCd=3220000
        // - 개 : 417000
        // - 고양이 : 422400
        // - 기타 : 429900
        getNsetKind("417000");
        getNsetKind("422400");
        getNsetKind("429900");
        return "end";
    }

    private String getNsetKind(String num) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder(API_URL).append("/kind"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + API_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("up_kind_cd", "UTF-8") + "=" + URLEncoder.encode(num, "UTF-8")); /*한 페이지 결과 수(1,000 이하)*/
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

        System.out.println(result.toString());
        List<PetKindResponseDto> pkList = petKindParseJson(result.toString());
        petService.savePetKind(pkList, num);
        return result.toString();
    }

    private List<PetKindResponseDto> petKindParseJson(String result) throws ParseException {
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

        List<PetKindResponseDto> kindResponseList = new ArrayList<>();
        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            String kindCd = String.valueOf(item.get("kindCd"));
            String KNm = (String) item.get("knm");

            kindResponseList.add(new PetKindResponseDto(kindCd, KNm));
        }
        return kindResponseList;
    }

    @GetMapping("notices")
    private ModelAndView getNoticeList(ModelAndView mv,
                                 @RequestParam(value="pageNo",required=false) String pageNo,
                                 @RequestParam(value="upr_cd",required=false) String upr_cd,
                                 @RequestParam(value="org_cd",required=false) String org_cd,
                                 @RequestParam(value="bgnde",required=false) String bgnde,
                                 @RequestParam(value="endde",required=false) String endde) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder(API_URL).append("/abandonmentPublic"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + API_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/

        if (pageNo!=null) {
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*xml(기본값) 또는 json*/
        }
        if (upr_cd!=null) {
            urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "=" + URLEncoder.encode(upr_cd, "UTF-8")); /*xml(기본값) 또는 json*/
        }
        if (org_cd!=null) {
            urlBuilder.append("&" + URLEncoder.encode("org_cd", "UTF-8") + "=" + URLEncoder.encode(org_cd, "UTF-8")); /*xml(기본값) 또는 json*/
        }
        if (bgnde!=null) {
            urlBuilder.append("&" + URLEncoder.encode("bgnde", "UTF-8") + "=" + URLEncoder.encode(bgnde, "UTF-8")); /*xml(기본값) 또는 json*/
        }
        if (endde!=null) {
            urlBuilder.append("&" + URLEncoder.encode("endde", "UTF-8") + "=" + URLEncoder.encode(endde, "UTF-8")); /*xml(기본값) 또는 json*/
        }

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

        List<PetNoticeDto> noticeList = petNoticeParseJson(result.toString());
        // petService.savePetKind(pkList,num);
        mv.addObject("noticeList",noticeList);
        mv.setViewName("notices");
        return mv;
    }


    private List<PetNoticeDto> petNoticeParseJson(String result) throws ParseException {
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

        List<PetNoticeDto> NoticeResponseList = new ArrayList<>();
        for (Object o : jsonItemList) {
            JSONObject item = (JSONObject) o;
            String desertionNo = String.valueOf(item.get("desertionNo"));
            String filename = (String) item.get("filename");
            String happenDt = (String) item.get("happenDt");
            String happenPlace = (String) item.get("happenPlace");
            String kindCd = (String) item.get("kindCd");
            String colorCd = (String) item.get("colorCd");
            String age = (String) item.get("age");
            String weight = (String) item.get("weight");
            String noticeNo = (String) item.get("noticeNo");
            String noticeSdt = (String) item.get("noticeSdt");
            String noticeEdt = (String) item.get("noticeEdt");
            String popfile = (String) item.get("popfile");
            String processState = (String) item.get("processState");
            String sexCd = (String) item.get("sexCd");
            String neuterYn = (String) item.get("neuterYn");
            String specialMark = (String) item.get("specialMark");
            String careNm = (String) item.get("careNm");
            String careTel = (String) item.get("careTel");
            String careAddr = (String) item.get("careAddr");
            String orgNm = (String) item.get("orgNm");
            String chargeNm = (String) item.get("chargeNm");
            String officetel = (String) item.get("officetel");

            NoticeResponseList.add(new PetNoticeDto(desertionNo,
                    filename, happenDt, happenPlace,
                    kindCd,
                    colorCd,
                    age,
                    weight,
                    noticeNo,
                    noticeSdt,
                    noticeEdt,
                    popfile,
                    processState,
                    sexCd,
                    neuterYn,
                    specialMark,
                    careNm,
                    careTel,
                    careAddr,
                    orgNm,
                    chargeNm,
                    officetel));
        }
        return NoticeResponseList;
    }
}
