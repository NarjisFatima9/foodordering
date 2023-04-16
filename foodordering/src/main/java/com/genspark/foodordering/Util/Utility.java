package com.genspark.foodordering.Util;

import com.genspark.foodordering.entity.Attachment;
import com.genspark.foodordering.entity.Restaurant;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

 public class Utility {

     public static Restaurant changeOrderAttachmentToURL(Restaurant restaurant){

         Attachment attachment = restaurant.getAttachment();
         String downloadURl = "";

         downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                 .path("/download/")
                 .path(attachment.getId())
                 .toUriString();
         restaurant.setIdDownloadURL(downloadURl);
         restaurant.setAttachment(null);  //set attachment to null for display in postman or as JSON
         return restaurant;

     }
}
