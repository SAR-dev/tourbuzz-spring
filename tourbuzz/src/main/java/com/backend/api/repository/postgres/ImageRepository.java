

package com.backend.api.repository.postgres;

import com.backend.api.entity.ImageEntity;
import com.backend.api.request.admin.ArticleCreateReq;
import com.backend.api.request.admin.ImageCreateReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageRepository {
    ImageEntity createImage(ImageCreateReq createReq);

    List<ImageEntity> getImages();

    int deleteImage(int id);
}
