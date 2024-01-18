package com.backend.api.service.admin;

import com.backend.api.Constants;
import com.backend.api.ErrorMessages;
import com.backend.api.entity.ImageEntity;
import com.backend.api.repository.postgres.admin.ImageRepository;
import com.backend.api.request.admin.ImageCreateReq;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final RestTemplate restTemplate;
    private final ImageRepository imageRepository;

    public ImageEntity uploadImage(byte[] imageData, String title) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("key", Constants.IMGBB.API_KEY);
        body.add("image", Base64.getEncoder().encodeToString(imageData));
        body.add("name", title);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                Constants.IMGBB.BASE_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            String sourceUrl = jsonResponse.getJSONObject("data").getJSONObject("image").getString("url");
            String thumbnailUrl = jsonResponse.getJSONObject("data").getJSONObject("thumb").getString("url");
            String optimizedUrl;
            try{
                optimizedUrl = jsonResponse.getJSONObject("data").getJSONObject("medium").getString("url");
            } catch (Exception e){
                optimizedUrl = thumbnailUrl;
            }
            ImageCreateReq imageCreateReq = ImageCreateReq.builder()
                    .title(title)
                    .sourceUrl(sourceUrl)
                    .thumbnailUrl(thumbnailUrl)
                    .optimizedUrl(optimizedUrl)
                    .build();
            return imageRepository.createImage(imageCreateReq);
        } else {
            throw new Exception("Error occurred: " + response.getStatusCode());
        }
    }

    public List<ImageEntity> getImages(){
        return imageRepository.getImages();
    }

    public void deleteImage(int id) throws Exception {
        int count = imageRepository.deleteImage(id);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }
}