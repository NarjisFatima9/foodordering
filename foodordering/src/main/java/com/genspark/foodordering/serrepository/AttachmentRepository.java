package com.genspark.foodordering.serrepository;

import com.genspark.foodordering.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,String> {

}