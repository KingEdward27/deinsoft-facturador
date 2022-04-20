package com.deinsoft.efacturador3commons.service.impl;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deinsoft.efacturador3commons.model.Document;
import com.deinsoft.efacturador3commons.repository.DocumentsRepository;
import com.deinsoft.efacturador3commons.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
    private DocumentsRepository documentsRepository;

    public String addFile(String title, byte[] bytes) throws IOException { 
        Document fileToSave = new Document(title); 
        fileToSave.setFile(
          new Binary(BsonBinarySubType.BINARY, bytes)); 
        fileToSave = documentsRepository.insert(fileToSave); 
        return fileToSave.getId(); 
    }

    public Document getFile(String id) { 
        return documentsRepository.findById(id).get(); 
    }
}
