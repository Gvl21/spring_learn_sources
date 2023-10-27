package com.busanit.jpashop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {


    public String uploadFile(String itemImgLocation, String oriImgName, MultipartFile itemImgFile) {

        // 파일의 이름이 중복되지 않도록
        // Universal Unique Identifier 중복될 가능성이 거의 없는 파일의 이름으로 중복문제해결
        UUID uuid = UUID.randomUUID();      // qqnjdwq-f32h832hf-ffwfwef
        // 파일 확장자 추출
        String extension = oriImgName.substring(oriImgName.lastIndexOf("."));   // .png
        // 파일 이름 만들기
        String savedFileName = uuid.toString() + extension;     // qqnjdwq-f32h832hf-ffwfwef.png
        String fileUploadPath = itemImgLocation + "/" + savedFileName;      // C:/shop/item/qqnjdwq-f32h832hf-ffwfwef.png

        // 파일 스트림으로 업로드 위치에 저장
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileUploadPath);
            fos.write(itemImgFile.getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       ;

        return savedFileName;
    }
}
