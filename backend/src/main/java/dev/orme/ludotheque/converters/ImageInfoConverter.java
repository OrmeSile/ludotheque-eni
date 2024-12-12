package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.ImageInfoDTO;
import dev.orme.ludotheque.entities.ImageInfo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
public class ImageInfoConverter implements DtoConvertable<ImageInfo, ImageInfoDTO> {
    @Override
    public ImageInfoDTO toDto(ImageInfo object) {
        if (object == null) return null;

        var imageInfoDto = ImageInfoDTO.newBuilder();
        if(object.getId() != null)
            imageInfoDto.setId(object.getId().toString());
        imageInfoDto.setPath(object.getPath());
        if(object.getAlt() != null)
            imageInfoDto.setAlt(object.getAlt());
        imageInfoDto.setOrder(object.getImageOrder());
        return imageInfoDto.build();
    }

    @Override
    public ImageInfo fromDto(ImageInfoDTO imageInfoDTO) {
        if (imageInfoDTO == null) return null;

        var imageInfo = new ImageInfo();
        if(!imageInfoDTO.getId().isBlank())
            imageInfo.setId(UUID.fromString(imageInfoDTO.getId()));
        imageInfo.setPath(imageInfoDTO.getPath());
        if(!imageInfoDTO.getAlt().isBlank())
            imageInfo.setAlt(imageInfoDTO.getAlt());
        imageInfo.setImageOrder(imageInfoDTO.getOrder());
        return imageInfo;
    }
}
