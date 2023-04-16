package com.genspark.foodordering.Service;

import com.genspark.foodordering.entity.Attachment;
import com.genspark.foodordering.serrepository.AttachmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AttachmentServiceImp implements AttachmentService{

    @Autowired
    AttachmentRepository repo;

    @Override
    public Attachment getAttachment(String id) {

        Optional<Attachment> op = repo.findById(id);
        Attachment attachment = null;
        if(op.isPresent()){
            attachment = op.get();
        }
        return attachment;
    }
}