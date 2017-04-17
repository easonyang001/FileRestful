package net.eason.FileRestful.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.eason.FileRestful.vo.FileVo;

public interface FileService {
    FileVo saveFile(MultipartFile file, FileVo metaData);
    List<FileVo> searchFileBy(String keyword);
}