package net.eason.FileRestful.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.eason.FileRestful.entity.FileMetadata;
import net.eason.FileRestful.exception.FileUploadException;
import net.eason.FileRestful.repository.FileRepo;
import net.eason.FileRestful.repository.FileSaver;
import net.eason.FileRestful.vo.FileVo;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileRepo fileRepo;
    
    @Autowired
    private FileSaver fileSaver;

    @Override
    @Transactional
    public FileVo saveFile(MultipartFile file, FileVo metaData) {
        FileMetadata fileData = new FileMetadata(metaData);
        LOG.info(file + " " + file.getContentType());
        fileRepo.save(fileData);
        try {
			fileSaver.saveFile(file.getBytes(), fileData.getDocId());
		} catch (IOException e) {
			throw new FileUploadException(e);
		}
        return metaData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileVo> searchFileBy(String keyword) {
        List<FileMetadata> entityList = fileRepo.findByFileNameOrUserId(keyword);
        List<FileVo> resList = new ArrayList<>();
        for (FileMetadata data : entityList) {
            resList.add(new FileVo(data.getId(),data.getUserId(),data.getUploadTime(),data.getFileName(),data.getDocId()));
        }
        return resList;
    }
}

